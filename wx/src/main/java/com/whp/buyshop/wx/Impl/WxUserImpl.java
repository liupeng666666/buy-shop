package com.whp.buyshop.wx.Impl;


import com.alibaba.fastjson.JSONObject;
import com.whp.buyshop.wx.Dao.WxMenuDao;
import com.whp.buyshop.wx.Dao.WxSubBuyDao;
import com.whp.buyshop.wx.Dao.WxUserDao;
import com.whp.buyshop.wx.Interface.WxMenuInterface;
import com.whp.buyshop.wx.Interface.WxUserInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WxUserImpl implements WxUserInterface {
    @Autowired
    private WxUserDao wxUserDao;
    @Autowired
    private WxMenuDao wxMenuDao;
    @Autowired
    private WxSubBuyDao wxSubBuyDao;

    @Override
    public JSONObject WxUserSelect(Map<String, Object> map, int page, int num) {
        JSONObject json = new JSONObject();
        try {
            Map<String, Object> map1 = wxMenuDao.getBuyById(map);
            if (map1.size() > 0) {
                map.put("wechat_appid", map1.get("wechat_appid"));
                List<Map<String, Object>> list = wxUserDao.WxUserSelectByPage(map, page, num);
                int count = wxUserDao.WxUserSelectByCount(map);
                json.put("code", 100);
                json.put("list", list);
                json.put("count", count);
            } else {
                json.put("code", 103);
            }

        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
        }
        return json;
    }

    @Override
    public JSONObject WxUserSs(String buyid, String name) {
        JSONObject json = new JSONObject();
        try {
            Map<String, Object> map = wxSubBuyDao.WxSubBuyPidDan(buyid);
            List<Map<String, Object>> list = wxUserDao.WxUserSelect(map.get("wechat_appid").toString(), name);
            json.put("code", 100);
            json.put("user", list);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
        }
        return json;
    }


}
