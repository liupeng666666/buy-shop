package com.whp.buyshop.buy.Interface;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * @author : 张吉伟
 * @data : 2019/10/16 19:05
 * @descrpition :
 */
public interface BuyCouponInterface {
    public JSONObject BuyCouponInsert(Map<String, Object> map);

    public JSONObject BuyCouponUpdate(Map<String, Object> map);

    public JSONObject BuyCouponUpdateState(Map<String, Object> map);

    public JSONObject BuyCouponSelect(Map<String, Object> map, int page, int num);

    public JSONObject BuyCouponLogSelect(Map<String, Object> map, int page, int num);

}
