package com.whp.buyshop.user.Impl;

import com.alibaba.fastjson.JSONObject;
import com.whp.buyshop.user.Dao.BuyCouponbDao;
import com.whp.buyshop.user.Dao.SubCouponDao;
import com.whp.buyshop.user.Dao.SubUserDao;
import com.whp.buyshop.user.Interface.SubCouponInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SubCouponImpl implements SubCouponInterface {
    @Autowired
    private SubCouponDao subCouponDao;
    @Autowired
    private SubUserDao subUserDao;

    @Override
    public JSONObject SubCouponInsert(Map<String, Object> map) {
        JSONObject json = new JSONObject();
        try {
            subCouponDao.SubCouponInsert(map);
            int count = subCouponDao.SubCouponSelectById(map);
            map.put("coupon_num", count);
            subUserDao.SubUserUpdateNumber(map);
            json.put("code", 100);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
        }
        return json;
    }
}
