package com.whp.buyshop.wx.Interface;

import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Map;

public interface WxImgTextInterface {
    public JSONObject WxImgTextInsert(Map<String, Object> map);

    public JSONObject WxImgTextSelect(Map<String, Object> map, int page, int num);

    public JSONObject WxImgTextUpdateState(Map<String, Object> map);

    public JSONObject WxImgTextUpdate(Map<String, Object> map);

    public JSONObject WxImgTextSelectById(Map<String, Object> map);

    public JSONObject WxImgTextSelectByReplyId(Map<String, Object> map);
}
