package com.whp.buyshop.buy.Impl;

import com.alibaba.fastjson.JSONObject;
import com.whp.buyshop.buy.Dao.BuyMumberDao;
import com.whp.buyshop.buy.Dao.BuyMumberMoneyDao;
import com.whp.buyshop.buy.Dao.SysBuyDao;
import com.whp.buyshop.buy.Interface.BuyMumberInterface;
import com.whp.buyshop.utils.util.RedisUtils;
import com.whp.buyshop.utils.util.SnowFlake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author : 张吉伟
 * @data : 2019/9/11 9:43
 * @descrpition :
 */
@Service
public class BuyMumberImpl implements BuyMumberInterface {
    @Autowired
    private BuyMumberDao buyMumberDao;
    @Autowired
    private SysBuyDao sysBuyDao;
    @Autowired
    private BuyMumberMoneyDao buyMumberMoneyDao;

    @Override
    public JSONObject BuyMumberSelect(String buyid, String name) {
        JSONObject json = new JSONObject();
        try {
            List<Map<String, Object>> list = buyMumberDao.BuyMumberSelect(buyid, name);
            json.put("code", 100);
            json.put("mumber", list);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
        }
        return json;
    }

    @Override
    public JSONObject BuyMumberSelectByBuy(String buyid) {
        JSONObject json = new JSONObject();
        try {
            Map<String, Object> map = buyMumberDao.BuyMumberSelectByBuy(buyid);
            json.put("code", 100);
            json.put("map", map);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
        }
        return json;
    }

    @Override
    public JSONObject BuyMumberSelectByTotal(String buyid) {
        JSONObject json = new JSONObject();
        try {
            List<Map<String, Object>> list = buyMumberDao.BuyMumberSelectByTotal(buyid);
            json.put("code", 100);
            json.put("mumber", list);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
        }
        return json;
    }

    @Override
    public JSONObject BuyMumberSelectByBuyid(String buyid) {
        JSONObject json = new JSONObject();
        try {
            int count = buyMumberDao.BuyMumberSelectByBuyid(buyid);
            json.put("code", 100);
            json.put("count", count);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
        }
        return json;
    }

    @Override
    public JSONObject BuyMumberInsert(Map<String, Object> map) {
        JSONObject json = new JSONObject();
        try {
            int count = buyMumberDao.SysMumberOpenid(map.get("openid").toString());
            if (count == 0) {
                Map<String, Object> fmap = buyMumberDao.SysMumberCount(map.get("buyid").toString());
                if (fmap != null) {
                    JSONObject fjson = new JSONObject(fmap);
                    if (fjson.getInteger("member") > 0 && fjson.getInteger("member") > fjson.getInteger("num")) {
                        SnowFlake snowFlake = new SnowFlake(1, 1);
                        long mumberid = snowFlake.nextId();
                        map.put("pid", mumberid);
                        buyMumberDao.BuyMumberInsert(map);
                        buyMumberMoneyDao.BuyMumberMoneyInsert(map);
                        buyMumberDao.SysMumberUserUpdate(map);
                        sysBuyDao.SysBuyUpdate(map.get("buyid").toString(), fjson.getInteger("num") + 1);
                        RedisUtils.DEL("mumber:" + map.get("buyid").toString(), 5);
                        json.put("code", 100);
                    } else {
                        json.put("code", 102);
                    }
                } else {
                    json.put("code", 104);
                }
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
    public JSONObject BuyMumberUpdate(Map<String, Object> map) {
        JSONObject json = new JSONObject();
        try {
            if (map.containsKey("openid") && map.get("openid") != null && map.get("openid") != "") {
                int count = buyMumberDao.SysMumberOpenid(map.get("openid").toString());
                if (count > 0) {
                    json.put("code", 101);
                    return json;
                }
            }
            buyMumberDao.SysMumberUpdate(map);
            RedisUtils.DEL("mumber:" + map.get("buyid").toString(), 5);
            json.put("code", 100);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
        }
        return json;
    }
}
