package com.whp.buyshop.buy.Interface;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * @author : 张吉伟
 * @data : 2019/10/10 12:49
 * @descrpition :
 */
public interface BuyAdInterface {

    public JSONObject BuyAdSelect(String buyid, String state, int page, int num);

    public JSONObject BuyAdInsert(Map<String, Object> map);

    public JSONObject BuyAdUpdate(Map<String, Object> map);

    public JSONObject BuyAdUpdateState(Map<String, Object> map);

}
