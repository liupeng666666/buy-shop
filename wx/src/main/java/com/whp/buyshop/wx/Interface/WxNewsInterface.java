package com.whp.buyshop.wx.Interface;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

public interface WxNewsInterface {
    public JSONObject WxNewsSelect(Map<String, Object> map, int page, int num);

}
