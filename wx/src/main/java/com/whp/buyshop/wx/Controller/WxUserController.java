package com.whp.buyshop.wx.Controller;

import com.alibaba.fastjson.JSONObject;
import com.whp.buyshop.utils.util.JWTUtil;
import com.whp.buyshop.wx.Interface.WxUserInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/WxUser")
public class WxUserController {
    @Autowired
    private WxUserInterface wxUserInterface;

    @PostMapping("WxUserSelect")
    public JSONObject WxUserSelect(@RequestParam Map<String, Object> map, int page, int num, HttpServletRequest request) {
        String buyid = JWTUtil.getUsername(request.getHeader("Authorization"), "buyid");
        map.put("buyid", buyid);
        JSONObject json = wxUserInterface.WxUserSelect(map, page, num);
        return json;
    }


    @PostMapping("WxUserSs")
    public JSONObject WxUserSs(String name, HttpServletRequest request) {
        String buyid = JWTUtil.getUsername(request.getHeader("Authorization"), "buyid");
        JSONObject json = wxUserInterface.WxUserSs(buyid, name);
        return json;
    }
}
