package com.whp.buyshop.buy.Interface;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * @author : 张吉伟
 * @data : 2019/10/16 19:05
 * @descrpition :
 */
public interface BuyIntegralInterface {
    public JSONObject BuyIntegralInsert(Map<String, Object> map);

    public JSONObject BuyIntegralUpdate(Map<String, Object> map);

    public JSONObject BuyIntegralUpdateState(Map<String, Object> map);

    public JSONObject BuyIntegralSelect(Map<String, Object> map, int page, int num);

    public JSONObject BuyIntegralLogSelect(Map<String, Object> map, int page, int num);

}
