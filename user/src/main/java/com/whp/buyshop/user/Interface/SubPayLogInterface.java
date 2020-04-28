package com.whp.buyshop.user.Interface;

import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Map;

public interface SubPayLogInterface {
    public JSONObject SubPayLogUpdate(String[] goodsid, Map<String, Object> map);

    public JSONObject SubPayLogSelect(Map<String, Object> map);
}
