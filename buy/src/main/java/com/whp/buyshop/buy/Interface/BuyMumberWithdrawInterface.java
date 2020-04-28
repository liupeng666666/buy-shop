package com.whp.buyshop.buy.Interface;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * @author : 张吉伟
 * @data : 2019/9/26 14:23
 * @descrpition :
 */
public interface BuyMumberWithdrawInterface {

    public JSONObject BuyNumberWithdrawSelect(String buyid, String mumberid, String state, int page, int num);

    public JSONObject BuyMumberWithdrawInsert(Map<String, Object> map);

    public JSONObject BuyMumberWithdrawUpdate(Map<String, Object> map);
}
