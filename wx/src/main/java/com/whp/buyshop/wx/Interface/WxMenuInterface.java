package com.whp.buyshop.wx.Interface;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

public interface WxMenuInterface {
    public JSONObject WxMenuInsert(Map<String, Object> map);

    public JSONObject WxMenuSelect(Map<String, Object> map, int page, int num);

    public JSONObject WxMenuUpdate(Map<String, Object> map);

    public JSONObject WxMenuUpdateState(Map<String, Object> map);

    public JSONObject WxMenuSelectmenu(Map<String, Object> map);

    public JSONObject WxMenuSelectById(Map<String, Object> map);
}
