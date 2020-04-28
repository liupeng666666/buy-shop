package com.whp.buyshop.buy.Controller;

import com.alibaba.fastjson.JSONObject;
import com.whp.buyshop.buy.Interface.BuyUserInterface;
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

@RestController
@RequestMapping("/BuyUser")
public class BuyUserController {
    @Autowired
    private ImgUtil imgUtil;
    @Value("${fast.url}")
    private String url;
    @Autowired
    private BuyUserInterface buyUserInterface;

    @PostMapping("BuyUserUpdate")
    public JSONObject BuyUserUpdate(@RequestParam(value = "file", required = false) MultipartFile file, @RequestParam Map<String, Object> map, HttpServletRequest request) {
        JSONObject json = new JSONObject();
        String userid = JWTUtil.getUsername(request.getHeader("Authorization"), "pid");
        map.put("userid", userid);
        if (file != null) {
            String urlimg = imgUtil.FileImg(file);
            map.put("img", url + urlimg);
        } else {
            map.put("img", null);
        }
        json = buyUserInterface.BuyUserUpdate(map);
        return json;
    }

    @PostMapping("BuyUserCom")
    public JSONObject BuyUserCom(@RequestParam Map<String, Object> map, HttpServletRequest request) {
        JSONObject json = new JSONObject();
        String userid = JWTUtil.getUsername(request.getHeader("Authorization"), "pid");
        map.put("userid", userid);

        json = buyUserInterface.BuyUserCom(map);
        return json;
    }

}
