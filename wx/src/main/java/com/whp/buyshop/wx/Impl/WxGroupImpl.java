package com.whp.buyshop.wx.Impl;

import com.alibaba.fastjson.JSONObject;
import com.whp.buyshop.mq.Interface.MqOrderInterface;
import com.whp.buyshop.utils.util.SnowFlake;
import com.whp.buyshop.wx.Dao.WxGroupDao;
import com.whp.buyshop.wx.Dao.WxImgTextDao;
import com.whp.buyshop.wx.Dao.WxSubBuyDao;
import com.whp.buyshop.wx.Dao.WxUserDao;
import com.whp.buyshop.wx.Interface.WxGroupInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author : 张吉伟
 * @data : 2019/12/15 16:39
 * @descrpition :
 */
@Service
public class WxGroupImpl implements WxGroupInterface {
    @Autowired
    private WxGroupDao wxGroupDao;
    @Autowired
    private WxImgTextDao wxImgTextDao;
    @Autowired
    private WxUserDao wxUserDao;
    @Autowired
    private MqOrderInterface mqOrderInterface;

    @Override
    public JSONObject WxGroupSelect(String buyid, Map<String, Object> map, int page, int num) {
        JSONObject json = new JSONObject();
        try {
            List<Map<String, Object>> arraylist = new ArrayList<>();
            map.put("buyid", buyid);
            List<Map<String, Object>> list = wxGroupDao.WxGroupSelect(map, page, num);
            int count = wxGroupDao.WxGroupCount(map);

            for (Map<String, Object> m : list) {
                if (m.get("openid") != null || !m.get("openid").toString().equals("")) {
                    Map<String, Object> userMap = wxUserDao.WxUserSelectByOpenId(m.get("openid").toString());
                    arraylist.add(userMap);
                }
            }
            json.put("user", arraylist);
            json.put("group", list);
            json.put("count", count);
            json.put("code", 100);

        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
        }
        return json;
    }

    @Override
    public JSONObject WxGroupInsert(String[] newsid, Map<String, Object> map) {
        JSONObject json = new JSONObject();
        try {
            SnowFlake snowFlake = new SnowFlake(1, 1);
            long pid = snowFlake.nextId();
            map.put("pid", pid);
            if (newsid != null) {
                for (int i = 0; i < newsid.length; i++) {
                    map.put("replyid", pid);
                    map.put("newsid", newsid[i]);
                    map.put("sort", (i + 1));
                    wxImgTextDao.wxImgTextUpdateById(map);
                }
            }
            wxGroupDao.WxGroupInsert(map);
            json.put("code", 100);


        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
        }
        return json;
    }

    @Override
    public JSONObject WxGroupUpdate(String[] newsid, Map<String, Object> map) {
        JSONObject json = new JSONObject();
        try {
            map.put("ittype", 1);
            wxImgTextDao.wxImgTextUpdateByReplyId(map);
            if (newsid != null) {
                for (int i = 0; i < newsid.length; i++) {
                    map.put("replyid", map.get("pid"));
                    map.put("newsid", newsid[i]);
                    map.put("sort", (i + 1));
                    wxImgTextDao.wxImgTextUpdateById(map);
                }
            }
            wxGroupDao.WxGroupUpdate(map);
            json.put("code", 100);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
        }
        return json;
    }

    @Override
    public JSONObject WxGroupUpdateState(Map<String, Object> map) {
        JSONObject json = new JSONObject();
        try {
            wxGroupDao.WxGroupModifyState(map);
            if (map.get("state").toString().equals("0")) {
                map.put("ittype", 1);
                int frequency = wxGroupDao.WxGroupByTime(map);
                if (frequency < 4) {
                    wxGroupDao.WxGroupUpdateState(map);
                    json.put("code", 100);
                    JSONObject xjson = new JSONObject();
                    xjson.put("groupid", map.get("pid"));
                    xjson.put("type", 0);
                    JSONObject cjson = new JSONObject();
                    cjson.put("queue", "wx_group");
                    cjson.put("message", xjson);
                    mqOrderInterface.MqOrderInsert(cjson);
                } else {
                    json.put("code", 106);
                    json.put("message", "已达到每月群发上限");
                }
            } else {
                wxGroupDao.WxGroupUpdateState(map);
                json.put("code", 100);
            }

        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
        }
        return json;
    }

    @Override
    public JSONObject WxGroupUpdateType(String buyid, String pid) {
        JSONObject json = new JSONObject();
        try {
            JSONObject cjson = new JSONObject();
            cjson.put("buyid", buyid);
            int frequency = wxGroupDao.WxGroupByTime(cjson);
            if (frequency < 4) {
                cjson.put("freqyency", frequency + 1);
                cjson.put("type", 1);
                wxGroupDao.WxGroupUpdateType(cjson);
                json.put("code", 100);
                JSONObject xjson = new JSONObject();
                xjson.put("groupid", pid);
                xjson.put("type", 1);
                JSONObject sjson = new JSONObject();
                sjson.put("queue", "wx_group");
                sjson.put("message", xjson);
                mqOrderInterface.MqOrderInsert(sjson);
            } else {
                json.put("code", 106);
                json.put("message", "已达到每月群发上限");
            }
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
        }
        return json;
    }

}
