package com.whp.buyshop.wx.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.whp.buyshop.wx.Interface.WechatAuthInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 * @author : 张吉伟
 * @data : 2019/11/25 11:50
 * @descrpition :
 */
@RestController
@RequestMapping("wx")
public class WxAuthController {
    @Autowired
    private WechatAuthInterface wechatAuthInterface;

    @GetMapping("{appid}/{mumberid}")
    public RedirectView code(@PathVariable("appid") String appid, @PathVariable("mumberid") String mumberid, String code) {
        JSONObject json = wechatAuthInterface.WechatAuth(appid, mumberid, code);
        RedirectView redirectTarget = new RedirectView();
        redirectTarget.setContextRelative(false);
        if (json.getInteger("code") == 100) {
            redirectTarget.setUrl("../../../recruit/success.html");
        } else {
            redirectTarget.setUrl("../../../recruit/fail.html?code=" + json.getInteger("code"));
        }

        return redirectTarget;
    }
}
