package com.whp.buyshop.buy.Interface;

import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * @author : 张吉伟
 * @data : 2019/10/16 15:18
 * @descrpition :
 */
public interface BuyRechargeInterface {

    public JSONObject BuyRechargeSelect(String buyid);

    public JSONObject BuyRechargeInsert(Map<String, Object> map);

    public JSONObject BuyRechargeUpdate(Map<String, Object> map);

    public JSONObject BuyRechargeUpdateState(Map<String, Object> map);

    public JSONObject BuyRechargePayLogSelect(Map<String, Object> map, int page, int num);
}
