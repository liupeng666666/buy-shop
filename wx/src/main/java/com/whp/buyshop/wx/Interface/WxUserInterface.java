package com.whp.buyshop.wx.Interface;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

public interface WxUserInterface {
    public JSONObject WxUserSelect(Map<String, Object> map, int page, int num);

    public JSONObject WxUserSs(String buyid, String name);

}
