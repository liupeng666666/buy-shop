package com.whp.buyshop.user.Impl;

import com.alibaba.fastjson.JSONObject;
import com.whp.buyshop.user.Dao.SubUserDao;
import com.whp.buyshop.user.Interface.SubUserInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author : 张吉伟
 * @data : 2019/9/11 11:38
 * @descrpition :
 */
@Service
public class SubUserImpl implements SubUserInterface {
    @Autowired
    private SubUserDao subUserDao;

    @Override
    public JSONObject SubUserSelectXx(String name, String buyid) {
        JSONObject json = new JSONObject();
        try {
            List<Map<String, Object>> list = subUserDao.SubUserSelectXx(name, buyid);
            json.put("user", list);
            json.put("code", 100);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
        }
        return json;
    }

    @Override
    public JSONObject SubUserSelectByBuyid(String buyid) {
        JSONObject json = new JSONObject();
        try {
            int count = subUserDao.SubUserSelectByBuyid(buyid);
            json.put("count", count);
            json.put("code", 100);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
        }
        return json;
    }

    @Override
    public JSONObject SubUserSelectBySearch(Map<String, Object> map) {
        JSONObject json = new JSONObject();
        try {
            List<Map<String, Object>> list = subUserDao.SubUserSelectBySearch(map);
            json.put("user", list);
            json.put("code", 100);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
        }
        return json;
    }

    @Override
    public JSONObject SubUserSelect(Map<String, Object> map, int page, int num) {
        JSONObject json = new JSONObject();
        try {
            List<Map<String, Object>> list = subUserDao.SubUserSelectByPage(map, page, num);
            int count = subUserDao.SubUserSelectByCount(map);
            json.put("user", list);
            json.put("count", count);
            json.put("code", 100);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
        }
        return json;
    }
}
