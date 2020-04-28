package com.whp.buyshop.order.Impl;

import com.alibaba.fastjson.JSONObject;
import com.whp.buyshop.mq.Interface.MqOrderInterface;
import com.whp.buyshop.order.Dao.OrderSubOrderDao;
import com.whp.buyshop.order.Interface.OrderSubOrderInterface;
import com.whp.buyshop.utils.util.SnowFlake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : 张吉伟
 * @data : 2019/10/15 15:24
 * @descrpition :
 */
@Service
public class OrderSubOrderImpl implements OrderSubOrderInterface {
    @Autowired
    private OrderSubOrderDao orderSubOrderDao;
    @Autowired
    private MqOrderInterface mqOrderInterface;

    @Override
    public JSONObject OrderSubOrderSelect(Map<String, Object> map, int page, int num) {
        JSONObject json = new JSONObject();
        try {
            List<Map<String, Object>> list = orderSubOrderDao.OrderSubOrderSelect(map, page, num);
            int count = orderSubOrderDao.OrderSubOrderCount(map);
            json.put("code", 100);
            json.put("order", list);
            json.put("count", count);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
        }
        return json;
    }

    @Override
    public JSONObject OrderSubOrderSelectByBuyid(String buyid) {
        JSONObject json = new JSONObject();
        try {
            int total = orderSubOrderDao.OrderSubOrderSelectByBuyid(buyid);
            json.put("code", 100);
            json.put("total", total);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
        }
        return json;
    }

    @Override
    public JSONObject OrderSubOrderGoodsDanSelect(String buyid, String goodsid, int state, int page, int num) {
        JSONObject json = new JSONObject();
        try {
            List<Map<String, Object>> list = orderSubOrderDao.OrderSubOrderGoodsDanSelect(buyid, goodsid, state, page, num);
            int count = orderSubOrderDao.OrderSubOrderGoodsDanCount(buyid, goodsid);
            json.put("list", list);
            json.put("total", count);
            json.put("code", 100);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
        }
        return json;
    }

    @Override
    public JSONObject OrderSubOrderGoodsSelect(String buyid) {
        JSONObject json = new JSONObject();
        try {
            List<Map<String, Object>> list = orderSubOrderDao.OrderSubOrderGoodsSelect(buyid);
            json.put("goods", list);
            json.put("code", 100);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
        }
        return json;
    }

    @Override
    public JSONObject OrderSubOrderListDanCx(String pid, String buyid) {
        JSONObject json = new JSONObject();
        try {
            Map<String, Object> map = orderSubOrderDao.OrderSubOrderListDanCx(pid, buyid);
            if (map != null) {
                JSONObject cjson = new JSONObject(map);
                if (cjson.getInteger("count") <= 1) {
                    orderSubOrderDao.SubOrderUpdate(cjson.getString("orderid"));
                    JSONObject fjson = new JSONObject();
                    SnowFlake snowFlake = new SnowFlake(1, 1);
                    fjson.put("pid", snowFlake.nextId());
                    fjson.put("orderid", cjson.get("orderid"));
                    fjson.put("state", 6);
                    orderSubOrderDao.SubOrderLogInsert(fjson);
//                    JSONObject xjson = new JSONObject();
//                    xjson.put("buyid", buyid);
//                    xjson.put("userid", userid);
//                    xjson.put("type", 1);
//                    sjson.put("message", xjson);
//                    sjson.put("queue", "wx_send");
//                    mqOrderInterface.MqOrderInsert(sjson);
                }
                orderSubOrderDao.SubOrderListUpdate(pid);
                json.put("order", map);
                json.put("code", 100);
            } else {
                json.put("code", 101);
            }
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
        }
        return json;
    }

    @Override
    public JSONObject OrderSubOrderLogSelect(String orderid) {
        JSONObject json = new JSONObject();
        try {
            List<Map<String, Object>> list = orderSubOrderDao.OrderSubOrderLogSelect(orderid);
            json.put("log", list);
            json.put("code", 100);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
        }
        return json;
    }

    @Override
    public JSONObject OrderSubOrderLostSelect(String orderid, String buyid) {
        JSONObject json = new JSONObject();
        try {
            List<Map<String, Object>> list = orderSubOrderDao.OrderSubOrderLostSelect(orderid, buyid);
            json.put("list", list);
            json.put("code", 100);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
        }
        return json;
    }


    @Override
    public JSONObject OrderSubOrderByUser(Map<String, Object> map) {
        JSONObject json = new JSONObject();
        try {
            List<Map<String, Object>> list1 = new ArrayList<>();
            List<Map<String, Object>> list = orderSubOrderDao.OrderSubOrderByUser(map);
            for (Map order : list) {
                map.put("orderid", order.get("pid"));
                List<Map<String, Object>> map1 = orderSubOrderDao.OrderSubOrderByOrderId(map);
                order.put("list", map1);
                list1.add(order);
            }

            json.put("order", list1);
            json.put("code", 100);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
        }
        return json;
    }

}
