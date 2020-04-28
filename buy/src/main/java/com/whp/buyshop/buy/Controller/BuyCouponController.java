package com.whp.buyshop.buy.Controller;

import com.alibaba.fastjson.JSONObject;
import com.whp.buyshop.buy.Interface.BuyCouponInterface;
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
@RequestMapping("BuyCoupon")
public class BuyCouponController {
    @Autowired
    private BuyCouponInterface buyCouponInterface;


    @PostMapping("BuyCouponInsert")
    public JSONObject BuyCouponInsert(@RequestParam Map<String, Object> map, HttpServletRequest request) {
        String buyid = JWTUtil.getUsername(request.getHeader("Authorization"), "buyid");
        String sys_userid = JWTUtil.getUsername(request.getHeader("Authorization"), "pid");
        map.put("sys_userid", sys_userid);
        map.put("buyid", buyid);
        JSONObject json = buyCouponInterface.BuyCouponInsert(map);
        return json;
    }

    @PostMapping("BuyCouponUpdate")
    public JSONObject BuyCouponUpdate(@RequestParam Map<String, Object> map, HttpServletRequest request) {
        String buyid = JWTUtil.getUsername(request.getHeader("Authorization"), "buyid");
        String sys_userid = JWTUtil.getUsername(request.getHeader("Authorization"), "pid");
        map.put("sys_userid", sys_userid);
        map.put("buyid", buyid);

        JSONObject json = buyCouponInterface.BuyCouponUpdate(map);
        return json;
    }

    @PostMapping("BuyCouponUpdateState")
    public JSONObject BuyCouponUpdateState(@RequestParam("pid[]") String[] pid, @RequestParam Map<String, Object> map, HttpServletRequest request) {
        JSONObject json = new JSONObject();
        String buyid = JWTUtil.getUsername(request.getHeader("Authorization"), "buyid");
        String sys_userid = JWTUtil.getUsername(request.getHeader("Authorization"), "pid");
        map.put("sys_userid", sys_userid);
        map.put("buyid", buyid);
        for (String id : pid) {
            map.put("pid", id);
            json = buyCouponInterface.BuyCouponUpdateState(map);
        }

        return json;
    }


    @PostMapping("BuyCouponSelect")
    public JSONObject BuyCouponSelect(@RequestParam Map<String, Object> map, int page, int num, HttpServletRequest request) {
        String buyid = JWTUtil.getUsername(request.getHeader("Authorization"), "buyid");
        map.put("buyid", buyid);
        JSONObject json = buyCouponInterface.BuyCouponSelect(map, page, num);
        return json;
    }

    @PostMapping("BuyCouponLogSelect")
    public JSONObject BuyCouponLogSelect(@RequestParam Map<String, Object> map, int page, int num, HttpServletRequest request) {
        String buyid = JWTUtil.getUsername(request.getHeader("Authorization"), "buyid");
        map.put("buyid", buyid);
        JSONObject json = buyCouponInterface.BuyCouponLogSelect(map, page, num);
        return json;
    }
}
