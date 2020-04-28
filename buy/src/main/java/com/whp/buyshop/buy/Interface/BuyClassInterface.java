package com.whp.buyshop.buy.Interface;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * @author : 张吉伟
 * @data : 2019/10/10 12:49
 * @descrpition :
 */
public interface BuyClassInterface {

    public JSONObject BuyClassSelect(String buyid);

    public JSONObject BuyClassSelectState(String buyid);

    public JSONObject BuyClassInsert(Map<String, Object> map);

    public JSONObject BuyClassUpdate(Map<String, Object> map);

    public JSONObject BuyClassUpdateState(Map<String, Object> map);

    public JSONObject BuyClassAdminSelect();
}
