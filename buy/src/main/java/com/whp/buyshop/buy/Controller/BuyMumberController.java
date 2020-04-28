package com.whp.buyshop.buy.Controller;

import com.alibaba.fastjson.JSONObject;
import com.google.zxing.WriterException;
import com.whp.buyshop.buy.Interface.BuyMumberInterface;
import com.whp.buyshop.utils.util.JWTUtil;
import com.whp.buyshop.utils.util.QrCodeCreateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

/**
 * @author : 张吉伟
 * @data : 2019/9/11 9:45
 * @descrpition :
 */
@RestController
@RequestMapping("BuyMumber")
public class BuyMumberController {

    @Autowired
    private BuyMumberInterface buyMumberInterface;

    @PostMapping("BuyMumberSelect")
    public JSONObject BuyMumberSelect(String name, HttpServletRequest request) {
        JSONObject json = new JSONObject();
        String buyid = JWTUtil.getUsername(request.getHeader("Authorization"), "buyid");
        json = buyMumberInterface.BuyMumberSelect(buyid, name);
        return json;
    }

    @PostMapping("BuyMumberSelectByBuyid")
    public JSONObject BuyMumberSelectByBuyid(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        String buyid = JWTUtil.getUsername(request.getHeader("Authorization"), "buyid");
        json = buyMumberInterface.BuyMumberSelectByBuyid(buyid);
        return json;
    }

    @PostMapping("BuyMumberSelectByBuy")
    public JSONObject BuyMumberSelectByBuy(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        String buyid = JWTUtil.getUsername(request.getHeader("Authorization"), "buyid");
        json = buyMumberInterface.BuyMumberSelectByBuy(buyid);
        return json;
    }

    @PostMapping("BuyMumberSelectByTotal")
    public JSONObject BuyMumberSelectByTotal(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        String buyid = JWTUtil.getUsername(request.getHeader("Authorization"), "buyid");
        json = buyMumberInterface.BuyMumberSelectByTotal(buyid);
        return json;
    }

    @PostMapping("BuyMumberInsert")
    public JSONObject BuyMumberInsert(@RequestParam Map<String, Object> map, HttpServletRequest request) {
        JSONObject json = new JSONObject();
        String buyid = JWTUtil.getUsername(request.getHeader("Authorization"), "buyid");
        String userid = JWTUtil.getUsername(request.getHeader("Authorization"), "pid");
        map.put("buyid", buyid);
        map.put("userid", userid);
        json = buyMumberInterface.BuyMumberInsert(map);
        return json;
    }

    @PostMapping("BuyMumberUpdate")
    public JSONObject BuyMumberUpdate(@RequestParam Map<String, Object> map, HttpServletRequest request) {
        JSONObject json = new JSONObject();
        String buyid = JWTUtil.getUsername(request.getHeader("Authorization"), "buyid");
        String userid = JWTUtil.getUsername(request.getHeader("Authorization"), "pid");
        map.put("buyid", buyid);
        map.put("userid", userid);
        json = buyMumberInterface.BuyMumberUpdate(map);
        return json;
    }

    @PostMapping("BuyMumberState")
    public JSONObject BuyMumberState(@RequestParam("pid[]") String[] pid, @RequestParam Map<String, Object> map, HttpServletRequest request) {
        JSONObject json = new JSONObject();
        String buyid = JWTUtil.getUsername(request.getHeader("Authorization"), "buyid");
        String userid = JWTUtil.getUsername(request.getHeader("Authorization"), "pid");
        map.put("buyid", buyid);
        map.put("userid", userid);
        for (String id : pid) {
            map.put("pid", id);
            json = buyMumberInterface.BuyMumberUpdate(map);
        }
        return json;
    }


}
