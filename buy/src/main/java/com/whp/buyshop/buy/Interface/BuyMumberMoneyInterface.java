package com.whp.buyshop.buy.Interface;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * @author : 张吉伟
 * @data : 2019/9/12 11:56
 * @descrpition :
 */
public interface BuyMumberMoneyInterface {

    public JSONObject BuyMumberMoneySelect(String buyid);

    public JSONObject BuyMumberMoneyUpdate(Map<String, Object> map);
}
