package com.whp.buyshop.buy.Controller;

import com.alibaba.fastjson.JSONObject;
import com.whp.buyshop.buy.Interface.BuyAdInterface;
import com.whp.buyshop.utils.util.ImgUtil;
import com.whp.buyshop.utils.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author : 张吉伟
 * @data : 2019/10/10 12:55
 * @descrpition :
 */
@RestController
@RequestMapping("BuyAd")
public class BuyAdController {
    @Autowired
    private BuyAdInterface buyAdInterface;
    @Autowired
    private ImgUtil imgUtil;
    @Value("${fast.url}")
    private String url;

    @PostMapping("BuyAdSelect")
    public JSONObject BuyAdSelect(String state, int page, int num, HttpServletRequest request) {
        String buyid = JWTUtil.getUsername(request.getHeader("Authorization"), "buyid");
        JSONObject json = buyAdInterface.BuyAdSelect(buyid, state, page, num);
        return json;
    }

    @PostMapping("BuyAdInsert")
    public JSONObject BuyAdInsert(@RequestParam(value = "file", required = false) MultipartFile file, @RequestParam Map<String, Object> map, HttpServletRequest request) {
        String buyid = JWTUtil.getUsername(request.getHeader("Authorization"), "buyid");
        String sys_userid = JWTUtil.getUsername(request.getHeader("Authorization"), "pid");
        map.put("sys_userid", sys_userid);
        map.put("buyid", buyid);
        if (file != null) {
            String ul = imgUtil.AdImg(file);
            map.put("img", url + ul);
        }
        JSONObject json = buyAdInterface.BuyAdInsert(map);
        return json;
    }

    @PostMapping("BuyAdUpdate")
    public JSONObject BuyAdUpdate(@RequestParam(value = "file", required = false) MultipartFile file, @RequestParam Map<String, Object> map, HttpServletRequest request) {
        String buyid = JWTUtil.getUsername(request.getHeader("Authorization"), "buyid");
        String sys_userid = JWTUtil.getUsername(request.getHeader("Authorization"), "pid");
        map.put("sys_userid", sys_userid);
        map.put("buyid", buyid);
        if (file != null) {
            String ul = imgUtil.AdImg(file);
            map.put("img", url + ul);
        }
        JSONObject json = buyAdInterface.BuyAdUpdate(map);
        return json;
    }

    @PostMapping("BuyAdUpdateState")
    public JSONObject BuyAdUpdateState(@RequestParam("pid[]") String[] pid, @RequestParam Map<String, Object> map, HttpServletRequest request) {
        JSONObject json = new JSONObject();
        String sys_userid = JWTUtil.getUsername(request.getHeader("Authorization"), "pid");
        map.put("sys_userid", sys_userid);
        for (String id : pid) {
            map.put("pid", id);
            json = buyAdInterface.BuyAdUpdateState(map);
        }

        return json;
    }
}
