package com.whp.buyshop.buy.Impl;

import com.alibaba.fastjson.JSONObject;
import com.whp.buyshop.buy.Dao.BuyAdDao;
import com.whp.buyshop.buy.Interface.BuyAdInterface;
import com.whp.buyshop.utils.util.RedisUtils;
import com.whp.buyshop.utils.util.SnowFlake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author : 张吉伟
 * @data : 2019/11/1 15:12
 * @descrpition :
 */
@Service
public class BuyAdImpl implements BuyAdInterface {
    @Autowired
    private BuyAdDao buyAdDao;

    @Override
    public JSONObject BuyAdSelect(String buyid, String state, int page, int num) {
        JSONObject json = new JSONObject();
        try {
            List<Map<String, Object>> list = buyAdDao.BuyAdSelect(buyid, state, page, num);
            int count = buyAdDao.BuyAdCount(buyid, state);
            json.put("code", 100);
            json.put("list", list);
            json.put("count", count);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
        }
        return json;
    }

    @Override
    public JSONObject BuyAdInsert(Map<String, Object> map) {
        JSONObject json = new JSONObject();
        try {
            SnowFlake snowFlake = new SnowFlake(1, 1);
            map.put("pid", snowFlake.nextId());
            buyAdDao.BuyAdInsert(map);
            RedisUtils.DEL("ad." + map.get("buyid"), 1);
            json.put("code", 100);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
        }
        return json;
    }

    @Override
    public JSONObject BuyAdUpdate(Map<String, Object> map) {
        JSONObject json = new JSONObject();
        try {
            buyAdDao.BuyAdUpdate(map);
            RedisUtils.DEL("ad." + map.get("buyid"), 1);
            json.put("code", 100);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
        }
        return json;
    }

    @Override
    public JSONObject BuyAdUpdateState(Map<String, Object> map) {
        JSONObject json = new JSONObject();
        try {
            buyAdDao.BuyAdUpdateState(map);
            RedisUtils.DEL("ad." + map.get("buyid"), 1);
            json.put("code", 100);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
        }
        return json;
    }
}
