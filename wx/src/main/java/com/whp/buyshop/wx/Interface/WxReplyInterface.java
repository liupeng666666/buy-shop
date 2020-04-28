package com.whp.buyshop.wx.Interface;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * @author : 张吉伟
 * @data : 2019/12/15 16:33
 * @descrpition :
 */
public interface WxReplyInterface {

    public JSONObject WxReplySelect(String buyid, Map<String, Object> map, int page, int num);

    public JSONObject WxReplyInsert(String[] newsid, Map<String, Object> map);

    public JSONObject WxReplyUpdate(String[] newsid, Map<String, Object> map);

    public JSONObject WxReplyUpdateState(Map<String, Object> map);
}
