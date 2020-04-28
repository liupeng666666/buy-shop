package com.whp.buyshop.buy.Interface;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * @author : 张吉伟
 * @data : 2019/10/10 10:12
 * @descrpition :
 */
public interface BuyGoodsInterface {

    public JSONObject BuyGoodsSelect(Map<String, Object> map);

    public JSONObject BuyGoodsDan(String buyid, String name);

    public JSONObject BuyGoodsInsert(Map<String, Object> map);

    public JSONObject BuyGoodsUpdate(Map<String, Object> map);

    public JSONObject BuyGoodsUpdateState(Map<String, Object> map);
}
