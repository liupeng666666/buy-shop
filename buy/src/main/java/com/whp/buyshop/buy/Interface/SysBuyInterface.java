package com.whp.buyshop.buy.Interface;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpSession;

/**
 * @author : 张吉伟
 * @data : 2019/9/6 10:34
 * @descrpition :
 */
public interface SysBuyInterface {
    public JSONObject SysBuySelect(String pid);

    public JSONObject SysBuyLogin(String username, String pasword, String yzm, HttpSession session);
}
