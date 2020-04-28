package com.whp.buyshop.buy.Impl;

import com.alibaba.fastjson.JSONObject;
import com.whp.buyshop.buy.Dao.BuyIntegralDao;
import com.whp.buyshop.buy.Interface.BuyIntegralInterface;
import com.whp.buyshop.utils.util.SnowFlake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author : 张吉伟
 * @data : 2019/10/16 19:06
 * @descrpition :
 */
@Service
public class BuyIntegralImpl implements BuyIntegralInterface {
    @Autowired
    private BuyIntegralDao buyIntegralDao;

    @Override
    public JSONObject BuyIntegralInsert(Map<String, Object> map) {
        JSONObject json = new JSONObject();
        try {
            SnowFlake snowFlake = new SnowFlake(1, 1);
            map.put("pid", snowFlake.nextId());
            buyIntegralDao.BuyIntegralInsert(map);
            json.put("code", 100);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
        }

        return json;
    }

    @Override
    public JSONObject BuyIntegralUpdate(Map<String, Object> map) {
        JSONObject json = new JSONObject();
        try {
            buyIntegralDao.BuyIntegralUpdate(map);
            json.put("code", 100);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
        }

        return json;
    }

    @Override
    public JSONObject BuyIntegralUpdateState(Map<String, Object> map) {
        JSONObject json = new JSONObject();
        try {
            buyIntegralDao.BuyIntegralUpdateState(map);
            json.put("code", 100);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
        }

        return json;
    }

    @Override
    public JSONObject BuyIntegralSelect(Map<String, Object> map, int page, int num) {
        JSONObject json = new JSONObject();
        try {
            List<Map<String, Object>> list = buyIntegralDao.BuyIntegralSelect(map, page, num);
            int count = buyIntegralDao.BuyIntegralCount(map);
            json.put("integral", list);
            json.put("count", count);
            json.put("code", 100);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
        }
        return json;
    }

    @Override
    public JSONObject BuyIntegralLogSelect(Map<String, Object> map, int page, int num) {
        JSONObject json = new JSONObject();
        try {
            List<Map<String, Object>> list = buyIntegralDao.BuyIntegralLogSelect(map, page, num);
            int count = buyIntegralDao.BuyIntegralLogCount(map);
            json.put("integral", list);
            json.put("count", count);
            json.put("code", 100);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
        }
        return json;
    }
}
