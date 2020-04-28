package com.whp.buyshop.buy.Controller;

import com.alibaba.fastjson.JSONObject;
import com.whp.buyshop.buy.Interface.BuyRechargeInterface;
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
 * @data : 2019/10/10 12:55
 * @descrpition :
 */
@RestController
@RequestMapping("BuyRecharge")
public class BuyRechargeController {
    @Autowired
    private BuyRechargeInterface buyRechargeInterface;

    @PostMapping("BuyRechargeSelect")
    public JSONObject BuyRechargeSelect(HttpServletRequest request) {
        String buyid = JWTUtil.getUsername(request.getHeader("Authorization"), "buyid");
        JSONObject json = buyRechargeInterface.BuyRechargeSelect(buyid);
        return json;
    }


    @PostMapping("BuyRechargeInsert")
    public JSONObject BuyRechargeInsert(@RequestParam Map<String, Object> map, HttpServletRequest request) {
        String buyid = JWTUtil.getUsername(request.getHeader("Authorization"), "buyid");
        String sys_userid = JWTUtil.getUsername(request.getHeader("Authorization"), "pid");
        map.put("sys_userid", sys_userid);
        map.put("buyid", buyid);
        JSONObject json = buyRechargeInterface.BuyRechargeInsert(map);
        return json;
    }

    @PostMapping("BuyRechargeUpdate")
    public JSONObject BuyRechargeUpdate(@RequestParam Map<String, Object> map, HttpServletRequest request) {
        String buyid = JWTUtil.getUsername(request.getHeader("Authorization"), "buyid");
        String sys_userid = JWTUtil.getUsername(request.getHeader("Authorization"), "pid");
        map.put("sys_userid", sys_userid);
        map.put("buyid", buyid);

        JSONObject json = buyRechargeInterface.BuyRechargeUpdate(map);
        return json;
    }

    @PostMapping("BuyRechargeUpdateState")
    public JSONObject BuyRechargeUpdateState(@RequestParam("pid[]") String[] pid, @RequestParam Map<String, Object> map, HttpServletRequest request) {
        JSONObject json = new JSONObject();
        String buyid = JWTUtil.getUsername(request.getHeader("Authorization"), "buyid");
        String sys_userid = JWTUtil.getUsername(request.getHeader("Authorization"), "pid");
        map.put("sys_userid", sys_userid);
        map.put("buyid", buyid);
        for (String id : pid) {
            map.put("pid", id);
            json = buyRechargeInterface.BuyRechargeUpdateState(map);
        }

        return json;
    }


    @PostMapping("BuyRechargePayLogSelect")
    public JSONObject BuyRechargePayLogSelect(@RequestParam Map<String, Object> map, int page, int num, HttpServletRequest request) {
        String buyid = JWTUtil.getUsername(request.getHeader("Authorization"), "buyid");
        map.put("buyid", buyid);
        JSONObject json = buyRechargeInterface.BuyRechargePayLogSelect(map, page, num);
        return json;
    }
}
