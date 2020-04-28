package com.whp.buyshop.wx.Interface;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * @author : 张吉伟
 * @data : 2019/12/15 16:33
 * @descrpition :
 */
public interface WxGroupInterface {

    public JSONObject WxGroupSelect(String buyid, Map<String, Object> map, int page, int num);

    public JSONObject WxGroupInsert(String[] newsid, Map<String, Object> map);

    public JSONObject WxGroupUpdate(String[] newsid, Map<String, Object> map);

    public JSONObject WxGroupUpdateState(Map<String, Object> map);

    public JSONObject WxGroupUpdateType(String buyid, String pid);
}
