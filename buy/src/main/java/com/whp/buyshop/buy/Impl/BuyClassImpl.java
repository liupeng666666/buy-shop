package com.whp.buyshop.buy.Impl;

import com.alibaba.fastjson.JSONObject;
import com.whp.buyshop.buy.Dao.BuyClassDao;
import com.whp.buyshop.buy.Interface.BuyClassInterface;
import com.whp.buyshop.utils.util.RedisUtils;
import com.whp.buyshop.utils.util.SnowFlake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author : 张吉伟
 * @data : 2019/10/10 12:50
 * @descrpition :
 */
@Service
public class BuyClassImpl implements BuyClassInterface {
    @Autowired
    private BuyClassDao buyClassDao;

    @Override
    public JSONObject BuyClassSelect(String buyid) {
        JSONObject json = new JSONObject();
        try {
            List<Map<String, Object>> list = buyClassDao.BuyClassSelect(buyid);
            json.put("list", list);
            json.put("code", 100);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
        }
        return json;
    }

    @Override
    public JSONObject BuyClassSelectState(String buyid) {
        JSONObject json = new JSONObject();
        try {
            List<Map<String, Object>> list = buyClassDao.BuyClassSelectState(buyid);
            json.put("list", list);
            json.put("code", 100);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
        }
        return json;
    }

    @Override
    public JSONObject BuyClassInsert(Map<String, Object> map) {
        JSONObject json = new JSONObject();
        try {
            SnowFlake snowFlake = new SnowFlake(1, 1);
            map.put("pid", snowFlake.nextId());
            buyClassDao.BuyClassInsert(map);
            RedisUtils.DEL("class." + map.get("buyid"), 1);
            json.put("code", 100);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
        }
        return json;
    }

    @Override
    public JSONObject BuyClassUpdate(Map<String, Object> map) {
        JSONObject json = new JSONObject();
        try {
            buyClassDao.BuyClassUpdate(map);
            RedisUtils.DEL("class." + map.get("buyid"), 1);
            json.put("code", 100);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
        }
        return json;
    }

    @Override
    public JSONObject BuyClassUpdateState(Map<String, Object> map) {
        JSONObject json = new JSONObject();
        try {
            buyClassDao.BuyClassUpdateState(map);
            RedisUtils.DEL("class." + map.get("buyid"), 1);
            json.put("code", 100);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
        }
        return json;
    }

    @Override
    public JSONObject BuyClassAdminSelect() {
        JSONObject json = new JSONObject();
        try {
            List<Map<String, Object>> list = buyClassDao.BuyClassAdminSelect();
            json.put("list", list);
            json.put("code", 100);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
        }
        return json;
    }
}
