package com.whp.buyshop.user.Controller;

import com.alibaba.fastjson.JSONObject;
import com.whp.buyshop.user.Interface.SubUserInterface;
import com.whp.buyshop.utils.util.JWTUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author : 张吉伟
 * @data : 2019/9/11 11:39
 * @descrpition :
 */
@RestController
@RequestMapping("SubUser")
public class SubUserController {

    @Autowired
    private SubUserInterface subUserInterface;

    @PostMapping("SubUserSelectXx")
    public JSONObject SubUserSelectXx(String name, HttpServletRequest request) {
        String buyid = JWTUtil.getUsername(request.getHeader("Authorization"), "buyid");

        JSONObject json = subUserInterface.SubUserSelectXx(name, buyid);
        return json;
    }

    @PostMapping("SubUserSelectByBuyid")
    public JSONObject SubUserSelectByBuyid(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        String buyid = JWTUtil.getUsername(request.getHeader("Authorization"), "buyid");
        json = subUserInterface.SubUserSelectByBuyid(buyid);
        return json;
    }

    @PostMapping("SubUserSelectBySearch")
    public JSONObject SubUserSelectBySearch(@RequestParam Map<String, Object> map, HttpServletRequest request) {
        JSONObject json = new JSONObject();
        String buyid = JWTUtil.getUsername(request.getHeader("Authorization"), "buyid");
        map.put("buyid", buyid);
        json = subUserInterface.SubUserSelectBySearch(map);
        return json;
    }

    @PostMapping("SubUserSelect")
    public JSONObject SubUserSelect(@RequestParam Map<String, Object> map, int page, int num, HttpServletRequest request) {
        JSONObject json = new JSONObject();
        String buyid = JWTUtil.getUsername(request.getHeader("Authorization"), "buyid");
        map.put("buyid", buyid);
        json = subUserInterface.SubUserSelect(map, page, num);
        return json;
    }
}
