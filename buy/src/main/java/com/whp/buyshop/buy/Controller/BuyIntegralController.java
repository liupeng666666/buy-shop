package com.whp.buyshop.buy.Controller;

import com.alibaba.fastjson.JSONObject;
import com.whp.buyshop.buy.Interface.BuyIntegralInterface;
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
@RequestMapping("BuyIntegral")
public class BuyIntegralController {
    @Autowired
    private BuyIntegralInterface buyIntegralInterface;


    @PostMapping("BuyIntegralInsert")
    public JSONObject BuyIntegralInsert(@RequestParam Map<String, Object> map, HttpServletRequest request) {
        String buyid = JWTUtil.getUsername(request.getHeader("Authorization"), "buyid");
        String sys_userid = JWTUtil.getUsername(request.getHeader("Authorization"), "pid");
        map.put("sys_userid", sys_userid);
        map.put("buyid", buyid);
        JSONObject json = buyIntegralInterface.BuyIntegralInsert(map);
        return json;
    }

    @PostMapping("BuyIntegralUpdate")
    public JSONObject BuyIntegralUpdate(@RequestParam Map<String, Object> map, HttpServletRequest request) {
        String buyid = JWTUtil.getUsername(request.getHeader("Authorization"), "buyid");
        String sys_userid = JWTUtil.getUsername(request.getHeader("Authorization"), "pid");
        map.put("sys_userid", sys_userid);
        map.put("buyid", buyid);

        JSONObject json = buyIntegralInterface.BuyIntegralUpdate(map);
        return json;
    }

    @PostMapping("BuyIntegralUpdateState")
    public JSONObject BuyIntegralUpdateState(@RequestParam("pid[]") String[] pid, @RequestParam Map<String, Object> map, HttpServletRequest request) {
        JSONObject json = new JSONObject();
        String buyid = JWTUtil.getUsername(request.getHeader("Authorization"), "buyid");
        String sys_userid = JWTUtil.getUsername(request.getHeader("Authorization"), "pid");
        map.put("sys_userid", sys_userid);
        map.put("buyid", buyid);
        for (String id : pid) {
            map.put("pid", id);
            json = buyIntegralInterface.BuyIntegralUpdateState(map);
        }

        return json;
    }


    @PostMapping("BuyIntegralSelect")
    public JSONObject BuyIntegralSelect(@RequestParam Map<String, Object> map, int page, int num, HttpServletRequest request) {
        String buyid = JWTUtil.getUsername(request.getHeader("Authorization"), "buyid");
        map.put("buyid", buyid);
        JSONObject json = buyIntegralInterface.BuyIntegralSelect(map, page, num);
        return json;
    }

    @PostMapping("BuyIntegralLogSelect")
    public JSONObject BuyIntegralLogSelect(@RequestParam Map<String, Object> map, int page, int num, HttpServletRequest request) {
        String buyid = JWTUtil.getUsername(request.getHeader("Authorization"), "buyid");
        map.put("buyid", buyid);
        JSONObject json = buyIntegralInterface.BuyIntegralLogSelect(map, page, num);
        return json;
    }
}
