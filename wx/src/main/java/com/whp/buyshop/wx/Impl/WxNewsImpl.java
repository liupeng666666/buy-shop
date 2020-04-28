package com.whp.buyshop.wx.Impl;


import com.alibaba.fastjson.JSONObject;
import com.whp.buyshop.wx.Dao.WxMenuDao;
import com.whp.buyshop.wx.Dao.WxNewsDao;
import com.whp.buyshop.wx.Interface.WxMenuInterface;
import com.whp.buyshop.wx.Interface.WxNewsInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WxNewsImpl implements WxNewsInterface {
    @Autowired
    private WxNewsDao wxNewsDao;
    @Autowired
    private WxMenuDao wxMenuDao;

    @Override
    public JSONObject WxNewsSelect(Map<String, Object> map, int page, int num) {
        JSONObject json = new JSONObject();
        try {
            Map<String, Object> map1 = wxMenuDao.getBuyById(map);
            if (map1.size() > 0) {
                map.put("wechat_appid", map1.get("wechat_appid"));
                List<Map<String, Object>> list = wxNewsDao.WxNewsSelectByPage(map, page, num);
                int count = wxNewsDao.WxNewsSelectByCount(map);
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

}
