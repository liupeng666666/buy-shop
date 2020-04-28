package com.whp.buyshop.buy.Interface;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * @author : 张吉伟
 * @data : 2019/9/26 14:23
 * @descrpition :
 */
public interface BuyMumberLogInterface {

    public JSONObject BuyNumberLogSelect(String buyid, String mumberid, int page, int num);
}
