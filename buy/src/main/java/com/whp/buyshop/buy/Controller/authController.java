package com.whp.buyshop.buy.Controller;

import com.alibaba.fastjson.JSONObject;
import com.google.zxing.WriterException;
import com.whp.buyshop.buy.Interface.BuyIpLogInteface;
import com.whp.buyshop.buy.Interface.SysBuyInterface;
import com.whp.buyshop.utils.util.CaptchaUtil;
import com.whp.buyshop.utils.util.JWTUtil;
import com.whp.buyshop.utils.util.QrCodeCreateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
 * @author : 张吉伟
 * @data : 2019/9/6 13:22
 * @descrpition :
 */
@RestController
@RequestMapping("Auth")
public class authController {
    @Autowired
    private SysBuyInterface sysBuyInterface;
    @Autowired
    private BuyIpLogInteface buyIpLogInteface;

    @PostMapping("SysBuyLogin")
    public JSONObject SysBuyLogin(String username, String password, String yzm, HttpSession session, HttpServletRequest request) {
        JSONObject json = sysBuyInterface.SysBuyLogin(username, password, yzm, session);
        if (json.getInteger("code") == 100) {
            String token = JWTUtil.sign(username, password, json.getJSONObject("buy").getString("userid"), request.getSession().getId(), json.getJSONObject("buy").getString("pid"));
            json.put("token", token);
            buyIpLogInteface.BuyIpLogInsert(request, json.getJSONObject("buy").getString("userid"));
        }
        return json;
    }

    @GetMapping("SysBuyYzm")
    public void SysBuyYzm(HttpServletRequest request, HttpServletResponse response) {
        try {
            CaptchaUtil.outputCaptcha(request, response);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @GetMapping("createQrCode")
    public void createQrCode(String content, HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession();
            int width = 180;
            int height = 40;
            String imgType = "jpeg";
            OutputStream output = response.getOutputStream();
            boolean flag = QrCodeCreateUtil.createQrCode(output, content, 900, imgType);
        } catch (WriterException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
