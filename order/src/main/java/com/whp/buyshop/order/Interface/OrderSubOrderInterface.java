package com.whp.buyshop.order.Interface;

import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * @author : 张吉伟
 * @data : 2019/10/15 15:24
 * @descrpition :
 */
public interface OrderSubOrderInterface {

    public JSONObject OrderSubOrderSelect(Map<String, Object> map, int page, int num);

    public JSONObject OrderSubOrderLogSelect(String orderid);

    public JSONObject OrderSubOrderLostSelect(String orderid, String buyid);

    public JSONObject OrderSubOrderSelectByBuyid(String buyid);

    public JSONObject OrderSubOrderGoodsDanSelect(String buyid, String goodsid, int state, int page, int num);

    public JSONObject OrderSubOrderGoodsSelect(String buyid);

    public JSONObject OrderSubOrderListDanCx(String pid, String buyid);

    public JSONObject OrderSubOrderByUser(Map<String, Object> map);

}
