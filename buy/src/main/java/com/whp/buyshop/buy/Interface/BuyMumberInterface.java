package com.whp.buyshop.buy.Interface;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * @author : 张吉伟
 * @data : 2019/9/11 9:42
 * @descrpition :
 */
public interface BuyMumberInterface {

    public JSONObject BuyMumberSelect(String buyid, String name);

    public JSONObject BuyMumberInsert(Map<String, Object> map);

    public JSONObject BuyMumberUpdate(Map<String, Object> map);

    public JSONObject BuyMumberSelectByBuyid(String buyid);

    public JSONObject BuyMumberSelectByBuy(String buyid);

    public JSONObject BuyMumberSelectByTotal(String buyid);
}
