package com.whp.buyshop.buy.Impl;

import com.alibaba.fastjson.JSONObject;
import com.whp.buyshop.buy.Dao.BuyRechargeDao;
import com.whp.buyshop.buy.Interface.BuyRechargeInterface;
import com.whp.buyshop.utils.util.SnowFlake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author : 张吉伟
 * @data : 2019/10/16 15:19
 * @descrpition :
 */
@Service
public class BuyRechargeImpl implements BuyRechargeInterface {

    @Autowired
    private BuyRechargeDao buyRechargeDao;

    @Override
    public JSONObject BuyRechargeSelect(String buyid) {
        JSONObject json = new JSONObject();
        try {
            List<Map<String, Object>> list = buyRechargeDao.BuyRechargeSelect(buyid);
            json.put("recharge", list);
            json.put("code", 100);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
        }

        return json;
    }

    @Override
    public JSONObject BuyRechargeInsert(Map<String, Object> map) {
        JSONObject json = new JSONObject();
        try {
            SnowFlake snowFlake = new SnowFlake(1, 1);
            map.put("pid", snowFlake.nextId());
            buyRechargeDao.BuyRechargeInsert(map);
            json.put("code", 100);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
        }
        return json;
    }

    @Override
    public JSONObject BuyRechargeUpdate(Map<String, Object> map) {
        JSONObject json = new JSONObject();
        try {
            buyRechargeDao.BuyRechargeUpdate(map);
            json.put("code", 100);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
        }
        return json;
    }

    @Override
    public JSONObject BuyRechargeUpdateState(Map<String, Object> map) {
        JSONObject json = new JSONObject();
        try {
            buyRechargeDao.BuyRechargeUpdateState(map);
            json.put("code", 100);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
        }
        return json;
    }

    @Override
    public JSONObject BuyRechargePayLogSelect(Map<String, Object> map, int page, int num) {
        JSONObject json = new JSONObject();
        try {
            List<Map<String, Object>> list = buyRechargeDao.BuyRechargePayLogSelect(map, page, num);
            int count = buyRechargeDao.BuyRechargePayLogCount(map);
            json.put("paylog", list);
            json.put("count", count);
            json.put("code", 100);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
        }
        return json;
    }
}
