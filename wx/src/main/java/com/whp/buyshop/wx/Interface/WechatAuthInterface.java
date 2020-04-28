package com.whp.buyshop.wx.Interface;

import com.alibaba.fastjson.JSONObject;

/**
 * @author : 张吉伟
 * @data : 2019/11/25 11:56
 * @descrpition :
 */
public interface WechatAuthInterface {

    public JSONObject WechatAuth(String appid, String mumberid, String code);
}
