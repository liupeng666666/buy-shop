package com.whp.buyshop.wx.Impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.whp.buyshop.utils.util.RedisUtils;
import com.whp.buyshop.utils.util.SnowFlake;
import com.whp.buyshop.wx.Dao.WxImgTextDao;
import com.whp.buyshop.wx.Dao.WxMenuDao;
import com.whp.buyshop.wx.Dao.WxReplyDao;
import com.whp.buyshop.wx.Dao.WxSubBuyDao;
import com.whp.buyshop.wx.Interface.WxReplyInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : 张吉伟
 * @data : 2019/12/15 16:39
 * @descrpition :
 */
@Service
public class WxReplyImpl implements WxReplyInterface {
    @Autowired
    private WxReplyDao wxReplyDao;
    @Autowired
    private WxImgTextDao wxImgTextDao;
    @Autowired
    private WxSubBuyDao wxSubBuyDao;

    @Override
    public JSONObject WxReplySelect(String buyid, Map<String, Object> map, int page, int num) {
        JSONObject json = new JSONObject();
        try {
            map.put("buyid", buyid);
            List<Map<String, Object>> list = wxReplyDao.WxReplySelect(map, page, num);
            int count = wxReplyDao.WxReplyCount(map);
            json.put("reply", list);
            json.put("count", count);
            json.put("code", 100);

        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
        }
        return json;
    }

    @Override
    public JSONObject WxReplyInsert(String[] newsid, Map<String, Object> map) {
        JSONObject json = new JSONObject();
        try {
            wxReplyDao.WxReplyStateUpdate(map);
            SnowFlake snowFlake = new SnowFlake(1, 1);
            long pid = snowFlake.nextId();
            map.put("pid", pid);
            Map<String, Object> fmap = wxSubBuyDao.WxSubBuyPidDan(map.get("buyid").toString());

            if (newsid != null) {
                for (int i = 0; i < newsid.length; i++) {
                    map.put("replyid", pid);
                    map.put("newsid", newsid[i]);
                    map.put("sort", (i + 1));
                    wxImgTextDao.wxImgTextUpdateById(map);
                }
            }

            if (fmap != null && fmap.containsKey("wechat_appid")) {
                map.put("appid", fmap.get("wechat_appid"));

                wxReplyDao.WxReplyInsert(map);
                WxRedis(pid + "", map.get("buyid").toString());
                json.put("code", 100);
            } else {
                json.put("code", 101);
            }

        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
        }
        return json;
    }

    @Override
    public JSONObject WxReplyUpdate(String[] newsid, Map<String, Object> map) {
        JSONObject json = new JSONObject();
        try {
            wxImgTextDao.wxImgTextUpdateByReplyId(map);
            if (newsid != null) {
                for (int i = 0; i < newsid.length; i++) {
                    map.put("replyid", map.get("pid"));
                    map.put("newsid", newsid[i]);
                    map.put("sort", (i + 1));
                    wxImgTextDao.wxImgTextUpdateById(map);
                }
            }
            wxReplyDao.WxReplyUpdate(map);
            WxRedis(map.get("pid").toString(), map.get("buyid").toString());
            json.put("code", 100);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
        }
        return json;
    }

    @Override
    public JSONObject WxReplyUpdateState(Map<String, Object> map) {
        JSONObject json = new JSONObject();
        try {
            if (map.get("state").toString().equals("0")) {
                //根据id查询对应的event，key
                Map<String, Object> map1 = wxReplyDao.WxReplyDanSelect(map.get("pid").toString());
                map.put("style", map1.get("style"));
                map.put("event", map1.get("event"));
                map.put("key", map1.get("key"));
                wxReplyDao.WxReplyStateUpdate(map);
            }
            wxReplyDao.WxReplyUpdateState(map);
            WxRedis(map.get("pid").toString(), map.get("buyid").toString());
            json.put("code", 100);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
        }
        return json;
    }

    public void WxRedis(String pid, String buyid) {
        try {
            Map<String, Object> map = wxReplyDao.WxReplyDanSelect(pid);
            if (map != null) {
                JSONObject json = new JSONObject(map);
                int state = 0;
                if (json.getInteger("state") == 1 || json.getInteger("isdel") == 1) {
                    state = 1;
                }
                if (state == 0 && json.getString("type").equals("news")) {
                    List<Map<String, Object>> list = wxImgTextDao.WxImgTextReplySelect(buyid, pid, 0);
                    json.put("imgtext", list);
                    if (list.size() == 0) {
                        return;
                    }
                }
                if (json.getString("style").equals("event")) {
                    if (json.getString("event").equals("subscribe")) {
                        WxRedisSet(state, json, 0);
                    } else {
                        WxRedisSet(state, json, 1);
                    }
                } else {
                    WxRedisSet(state, json, 2);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void WxRedisSet(int state, JSONObject json, int type) {
        System.out.println("state:" + state);
        System.out.println("type:" + type);
        System.out.println("json:" + json);
        if (type == 0) {
            if (state == 0) {
                RedisUtils.set("wx:reply:event:" + json.getString("appid") + ":subscribe", json.toString(), 7);
            } else {
                RedisUtils.DEL("wx:reply:event:" + json.getString("appid") + ":subscribe", 7);
            }
        } else if (type == 1) {
            if (state == 0) {
                Map<String, String> map = new HashMap<>();
                map.put(json.getString("key"), json.toString());
                RedisUtils.HMSET("wx:reply:event:" + json.getString("appid") + ":click", map, 7);
            } else {
                RedisUtils.HMDEL("wx:reply:event:" + json.getString("appid") + ":click", json.getString("key"), 7);
            }
        } else {
            if (state == 0) {
                Map<String, String> map = new HashMap<>();
                map.put(json.getString("key"), json.toString());
                RedisUtils.HMSET("wx:reply:event:" + json.getString("appid") + ":text", map, 7);
            } else {
                RedisUtils.HMDEL("wx:reply:event:" + json.getString("appid") + ":text", json.getString("key"), 7);
            }
        }
    }
}
