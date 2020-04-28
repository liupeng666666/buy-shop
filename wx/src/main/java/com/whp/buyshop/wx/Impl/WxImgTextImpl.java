package com.whp.buyshop.wx.Impl;


import com.alibaba.fastjson.JSONObject;
import com.whp.buyshop.wx.Dao.WxImgTextDao;
import com.whp.buyshop.wx.Dao.WxMediaDao;
import com.whp.buyshop.wx.Dao.WxMenuDao;
import com.whp.buyshop.wx.Dao.WxNewsDao;
import com.whp.buyshop.wx.Interface.WxImgTextInterface;
import com.whp.buyshop.wx.Interface.WxMediaInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class WxImgTextImpl implements WxImgTextInterface {
    @Autowired
    private WxImgTextDao wxImgTextDao;
    @Autowired
    private WxMenuDao wxMenuDao;

    @Override
    public JSONObject WxImgTextInsert(Map<String, Object> map) {
        JSONObject json = new JSONObject();
        try {
            Map<String, Object> map1 = wxMenuDao.getBuyById(map);
            if (map1.size() > 0) {
                map.put("wechat_appid", map1.get("wechat_appid"));
                wxImgTextDao.WxImgTextInsert(map);
                json.put("code", 100);
            } else {
                json.put("code", 103);
            }
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
        }
        return json;
    }

    @Override
    public JSONObject WxImgTextSelect(Map<String, Object> map, int page, int num) {
        JSONObject json = new JSONObject();
        try {
            List<Map<String, Object>> list = wxImgTextDao.WxImgTextSelectByPage(map, page, num);
            int count = wxImgTextDao.WxImgTextSelectByCount(map);
            json.put("code", 100);
            json.put("list", list);
            json.put("count", count);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
        }
        return json;
    }

    @Override
    public JSONObject WxImgTextUpdateState(Map<String, Object> map) {
        JSONObject json = new JSONObject();
        try {

            if (map.containsKey("isdel")) {
                wxImgTextDao.WxImgTextUpdateState(map);
                json.put("code", 100);
            }
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
        }
        return json;
    }

    @Override
    public JSONObject WxImgTextUpdate(Map<String, Object> map) {
        JSONObject json = new JSONObject();
        try {

            wxImgTextDao.WxImgTextUpdate(map);
            json.put("code", 100);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
        }
        return json;
    }

    @Override
    public JSONObject WxImgTextSelectById(Map<String, Object> map) {
        JSONObject json = new JSONObject();
        try {

            List<Map<String, Object>> list = wxImgTextDao.WxImgTextSelectById(map);
            json.put("code", 100);
            json.put("imgText", list);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
        }
        return json;
    }

    @Override
    public JSONObject WxImgTextSelectByReplyId(Map<String, Object> map) {
        JSONObject json = new JSONObject();
        try {

            List<Map<String, Object>> list = wxImgTextDao.WxImgTextSelectByReplyId(map);
            json.put("code", 100);
            json.put("imgText", list);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
        }
        return json;
    }
}
