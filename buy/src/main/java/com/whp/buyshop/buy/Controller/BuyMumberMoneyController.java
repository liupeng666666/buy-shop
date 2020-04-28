package com.whp.buyshop.buy.Controller;

import com.alibaba.fastjson.JSONObject;
import com.whp.buyshop.buy.Interface.BuyMumberMoneyInterface;
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
 * @data : 2019/9/11 9:45
 * @descrpition :
 */
@RestController
@RequestMapping("BuyMumberMoney")
public class BuyMumberMoneyController {

    @Autowired
    private BuyMumberMoneyInterface buyMumberMoneyInterface;

    @PostMapping("BuyMumberMoneySelect")
    public JSONObject BuyMumberMoneySelect(String name, HttpServletRequest request) {
        JSONObject json = new JSONObject();
        String buyid = JWTUtil.getUsername(request.getHeader("Authorization"), "buyid");
        json = buyMumberMoneyInterface.BuyMumberMoneySelect(buyid);
        return json;
    }

    @PostMapping("BuyMumberMoneyUpdate")
    public JSONObject BuyMumberMoneyUpdate(@RequestParam Map<String, Object> map, HttpServletRequest request) {
        JSONObject json = new JSONObject();
        String buyid = JWTUtil.getUsername(request.getHeader("Authorization"), "buyid");
        String userid = JWTUtil.getUsername(request.getHeader("Authorization"), "pid");
        map.put("buyid", buyid);
        map.put("userid", userid);

        json = buyMumberMoneyInterface.BuyMumberMoneyUpdate(map);
        return json;
    }
}
