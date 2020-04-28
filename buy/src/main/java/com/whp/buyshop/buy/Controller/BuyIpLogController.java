package com.whp.buyshop.buy.Controller;

import com.alibaba.fastjson.JSONObject;
import com.whp.buyshop.buy.Interface.BuyIpLogInteface;
import com.whp.buyshop.utils.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author : 张吉伟
 * @data : 2019/10/10 12:55
 * @descrpition :
 */
@RestController
@RequestMapping("BuyIpLog")
public class BuyIpLogController {
    @Autowired
    private BuyIpLogInteface buyIpLogInteface;

    @PostMapping("BuyIpLogSelect")
    public JSONObject BuyIpLogSelect(HttpServletRequest request) {
        String userid = JWTUtil.getUsername(request.getHeader("Authorization"), "pid");
        JSONObject json = buyIpLogInteface.BuyIpLogSelect(userid);
        return json;
    }

}
