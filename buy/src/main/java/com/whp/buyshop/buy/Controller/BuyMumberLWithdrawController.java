package com.whp.buyshop.buy.Controller;

import com.alibaba.fastjson.JSONObject;
import com.whp.buyshop.buy.Interface.BuyMumberLogInterface;
import com.whp.buyshop.buy.Interface.BuyMumberWithdrawInterface;
import com.whp.buyshop.utils.util.JWTUtil;
import com.whp.buyshop.utils.util.SnowFlake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author : 张吉伟
 * @data : 2019/9/11 9:45
 * @descrpition :
 */
@RestController
@RequestMapping("BuyMumberWithdraw")
public class BuyMumberLWithdrawController {

    @Autowired
    private BuyMumberWithdrawInterface buyMumberWithdrawInterface;

    @PostMapping("BuyMumberWithdrawSelect")
    public JSONObject BuyMumberWithdrawSelect(String mumberid, String state, int page, int num, HttpServletRequest request) {
        JSONObject json = new JSONObject();
        String buyid = JWTUtil.getUsername(request.getHeader("Authorization"), "buyid");
        json = buyMumberWithdrawInterface.BuyNumberWithdrawSelect(buyid, mumberid, state, page, num);
        return json;
    }

    @PostMapping("BuyMumberWithdrawInsert")
    public JSONObject BuyMumberWithdrawInsert(@RequestParam Map<String, Object> map, HttpServletRequest request) {
        JSONObject json = new JSONObject();
        SnowFlake snowFlake = new SnowFlake(1, 1);
        map.put("pid", snowFlake.nextId());
        String buyid = JWTUtil.getUsername(request.getHeader("Authorization"), "buyid");
        String userid = JWTUtil.getUsername(request.getHeader("Authorization"), "pid");
        map.put("buyid", buyid);
        map.put("userid", userid);
        json = buyMumberWithdrawInterface.BuyMumberWithdrawInsert(map);
        return json;
    }

    @PostMapping("BuyMumberWithdrawUpdate")
    public JSONObject BuyMumberWithdrawUpdate(@RequestParam Map<String, Object> map, HttpServletRequest request) {
        JSONObject json = new JSONObject();
        String buyid = JWTUtil.getUsername(request.getHeader("Authorization"), "buyid");
        String userid = JWTUtil.getUsername(request.getHeader("Authorization"), "pid");
        map.put("buyid", buyid);
        map.put("userid", userid);
        json = buyMumberWithdrawInterface.BuyMumberWithdrawUpdate(map);
        return json;
    }
}
