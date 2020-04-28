package com.whp.buyshop.buy.Impl;

import com.alibaba.fastjson.JSONObject;
import com.whp.buyshop.buy.Dao.BuyCouponDao;
import com.whp.buyshop.buy.Dao.BuyUserDao;
import com.whp.buyshop.buy.Interface.BuyCouponInterface;
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
public class BuyCouponImpl implements BuyCouponInterface {
    @Autowired
    private BuyCouponDao buyCouponDao;
    @Autowired
    private BuyUserDao buyUserDao;

    @Override
    public JSONObject BuyCouponInsert(Map<String, Object> map) {
        JSONObject json = new JSONObject();
        try {
            SnowFlake snowFlake = new SnowFlake(1, 1);
            map.put("pid", snowFlake.nextId());
            buyCouponDao.BuyCouponInsert(map);
            json.put("code", 100);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
        }

        return json;
    }

    @Override
    public JSONObject BuyCouponUpdate(Map<String, Object> map) {
        JSONObject json = new JSONObject();
        try {
            buyCouponDao.BuyCouponUpdate(map);
            json.put("code", 100);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
        }

        return json;
    }

    @Override
    public JSONObject BuyCouponUpdateState(Map<String, Object> map) {
        JSONObject json = new JSONObject();
        try {

            buyCouponDao.BuyCouponUpdateState(map);
            int count = buyCouponDao.SubCouponSelectByCouponId(map);
            if (count > 0 && map.get("isdel").toString().equals("1")) {
                buyCouponDao.SubCouponUpdateState(map);
                map.put("num", 1);
                buyUserDao.SubUserUpdateNumber(map);
                System.out.println("map===" + map);
            } else {
                System.out.println("count===" + count);
            }
            json.put("code", 100);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
        }

        return json;
    }

    @Override
    public JSONObject BuyCouponSelect(Map<String, Object> map, int page, int num) {
        JSONObject json = new JSONObject();
        try {
            List<Map<String, Object>> list = buyCouponDao.BuyCouponSelect(map, page, num);
            int count = buyCouponDao.BuyCouponCount(map);
            json.put("coupon", list);
            json.put("count", count);
            json.put("code", 100);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
        }
        return json;
    }

    @Override
    public JSONObject BuyCouponLogSelect(Map<String, Object> map, int page, int num) {
        JSONObject json = new JSONObject();
        try {
            List<Map<String, Object>> list = buyCouponDao.BuySubCouponLogSelect(map, page, num);
            int count = buyCouponDao.BuySubCouponLogCount(map);
            json.put("coupon", list);
            json.put("count", count);
            json.put("code", 100);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
        }
        return json;
    }
}
