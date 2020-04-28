package com.whp.buyshop.wx.Controller;

import com.alibaba.fastjson.JSONObject;
import com.whp.buyshop.utils.util.JWTUtil;
import com.whp.buyshop.wx.Interface.WxNewsInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/WxNews")
public class WxNewsController {
    @Autowired
    private WxNewsInterface wxNewsInterface;

    @PostMapping("WxNewsSelect")
    public JSONObject WxNewsSelect(@RequestParam Map<String, Object> map, int page, int num, HttpServletRequest request) {
        String buyid = JWTUtil.getUsername(request.getHeader("Authorization"), "buyid");
        map.put("buyid", buyid);
        JSONObject json = wxNewsInterface.WxNewsSelect(map, page, num);
        return json;
    }
}
