package com.whp.buyshop.wx.Interface;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface WxMediaInterface {
    public JSONObject WxMediaInsert(Map<String, Object> map, MultipartFile file);

    public JSONObject WxMediaSelect(Map<String, Object> map, int page, int num);

    public JSONObject WxMediaUpdateState(Map<String, Object> map);

    public JSONObject WxMediaSelectById(String buyid);

    public JSONObject WxMediaSelectByStyle(Map<String, Object> map);
}
