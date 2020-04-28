package com.whp.buyshop.buy.Interface;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

public interface BuyUserInterface {
    public JSONObject BuyUserUpdate(Map<String, Object> map);

    public JSONObject BuyUserCom(Map<String, Object> map);
}
