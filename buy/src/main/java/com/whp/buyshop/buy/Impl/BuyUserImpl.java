package com.whp.buyshop.buy.Impl;

import com.alibaba.fastjson.JSONObject;
import com.whp.buyshop.buy.Dao.BuyUserDao;
import com.whp.buyshop.buy.Interface.BuyUserInterface;
import com.whp.buyshop.utils.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class BuyUserImpl implements BuyUserInterface {
    @Autowired
    private BuyUserDao buyUserDao;


    @Override
    public JSONObject BuyUserUpdate(Map<String, Object> map) {
        JSONObject json = new JSONObject();
        try {
            if (map.containsKey("password")) {
                map.put("password", MD5Util.MD5(map.get("password").toString()));
            }
            buyUserDao.BuyUserUpdate(map);
            json.put("code", 100);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
        }
        return json;
    }

    @Override
    public JSONObject BuyUserCom(Map<String, Object> map) {
        JSONObject json = new JSONObject();
        try {
            Map<String, Object> map1 = buyUserDao.BuyUserCom(map);
            if (map1.get("password").equals(MD5Util.MD5(map.get("password").toString()))) {
                json.put("message", "");
            } else {
                json.put("message", "原密码错误");
            }
            json.put("code", 100);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
        }
        return json;
    }
}
