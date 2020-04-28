package com.whp.buyshop.order.Controller;

import com.alibaba.fastjson.JSONObject;
import com.whp.buyshop.order.Interface.OrderSubOrderInterface;
import com.whp.buyshop.utils.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author : 张吉伟
 * @data : 2019/10/15 15:32
 * @descrpition :
 */
@RestController
@RequestMapping("OrderSubOrder")
public class OrderSubOrderController {

    @Autowired
    private OrderSubOrderInterface orderSubOrderInterface;

    @PostMapping("OrderSubOrderSelect")
    public JSONObject OrderSubOrderSelect(@RequestParam Map<String, Object> map, int page, int num, HttpServletRequest request) {
        String buyid = JWTUtil.getUsername(request.getHeader("Authorization"), "buyid");
        map.put("buyid", buyid);
        JSONObject json = orderSubOrderInterface.OrderSubOrderSelect(map, page, num);
        return json;
    }

    @PostMapping("OrderSubOrderLogSelect")
    public JSONObject OrderSubOrderLogSelect(String orderid, HttpServletRequest request) {
        JSONObject json = orderSubOrderInterface.OrderSubOrderLogSelect(orderid);
        return json;
    }

    @PostMapping("OrderSubOrderSelectByBuyid")
    public JSONObject OrderSubOrderSelectByBuyid(HttpServletRequest request) {
        String buyid = JWTUtil.getUsername(request.getHeader("Authorization"), "buyid");
        JSONObject json = orderSubOrderInterface.OrderSubOrderSelectByBuyid(buyid);
        return json;
    }

    @PostMapping("OrderSubOrderListSelect")
    public JSONObject OrderSubOrderListSelect(String orderid, HttpServletRequest request) {
        String buyid = JWTUtil.getUsername(request.getHeader("Authorization"), "buyid");
        JSONObject json = orderSubOrderInterface.OrderSubOrderLostSelect(orderid, buyid);
        return json;
    }

    @PostMapping("OrderSubOrderGoodsDanSelect")
    public JSONObject OrderSubOrderGoodsDanSelect(String goodsid, int page, int num, int state, HttpServletRequest request) {
        String buyid = JWTUtil.getUsername(request.getHeader("Authorization"), "buyid");
        JSONObject json = orderSubOrderInterface.OrderSubOrderGoodsDanSelect(buyid, goodsid, state, page, num);
        return json;
    }

    @PostMapping("OrderSubOrderGoodsSelect")
    public JSONObject OrderSubOrderGoodsSelect(HttpServletRequest request) {
        String buyid = JWTUtil.getUsername(request.getHeader("Authorization"), "buyid");
        JSONObject json = orderSubOrderInterface.OrderSubOrderGoodsSelect(buyid);
        return json;
    }

    @PostMapping("OrderSubOrderListDanCx")
    public JSONObject OrderSubOrderListDanCx(String pid, HttpServletRequest request) {
        String buyid = JWTUtil.getUsername(request.getHeader("Authorization"), "buyid");
        JSONObject json = orderSubOrderInterface.OrderSubOrderListDanCx(pid, buyid);
        return json;
    }

    @PostMapping("OrderSubOrderByUser")
    public JSONObject OrderSubOrderByUser(@RequestParam Map<String, Object> map, HttpServletRequest request) {
        String buyid = JWTUtil.getUsername(request.getHeader("Authorization"), "buyid");
        map.put("buyid", buyid);
        JSONObject json = orderSubOrderInterface.OrderSubOrderByUser(map);
        return json;
    }

}
