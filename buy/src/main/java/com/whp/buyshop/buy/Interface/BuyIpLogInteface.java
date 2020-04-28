package com.whp.buyshop.buy.Interface;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;

/**
 * @author : 张吉伟
 * @data : 2019/11/1 9:51
 * @descrpition :
 */
public interface BuyIpLogInteface {
    public void BuyIpLogInsert(HttpServletRequest request, String userid);

    public JSONObject BuyIpLogSelect(String userid);
}
