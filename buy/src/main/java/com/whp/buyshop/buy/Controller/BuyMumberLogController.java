package com.whp.buyshop.buy.Controller;

import com.alibaba.fastjson.JSONObject;
import com.whp.buyshop.buy.Interface.BuyMumberLogInterface;
import com.whp.buyshop.buy.Interface.BuyMumberMoneyInterface;
import com.whp.buyshop.utils.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author : 张吉伟
 * @data : 2019/9/11 9:45
 * @descrpition :
 */
@RestController
@RequestMapping("BuyMumberLog")
public class BuyMumberLogController {

    @Autowired
    private BuyMumberLogInterface buyMumberLogInterface;

    @PostMapping("BuyMumberLogSelect")
    public JSONObject BuyMumberLogSelect(String mumberid, int page, int num, HttpServletRequest request) {
        JSONObject json = new JSONObject();
        String buyid = JWTUtil.getUsername(request.getHeader("Authorization"), "buyid");
        json = buyMumberLogInterface.BuyNumberLogSelect(buyid, mumberid, page, num);
        return json;
    }
}
