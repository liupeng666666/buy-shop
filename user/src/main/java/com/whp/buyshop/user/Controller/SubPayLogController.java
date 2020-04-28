package com.whp.buyshop.user.Controller;

import com.alibaba.fastjson.JSONObject;
import com.whp.buyshop.user.Dao.SubUserDao;
import com.whp.buyshop.user.Interface.SubPayLogInterface;
import com.whp.buyshop.utils.util.JWTUtil;
import com.whp.buyshop.utils.util.SnowFlake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/SubPayLog")
public class SubPayLogController {
    @Autowired
    private SubPayLogInterface subPayLogInterface;
    @Autowired
    private SubUserDao subUserDao;

    @PostMapping("/SubPayLogUpdate")
    public JSONObject SubPayLogUpdate(@RequestParam("goodsid[]") String[] goodsid, @RequestParam Map<String, Object> map, HttpServletRequest request) {
        String buyid = JWTUtil.getUsername(request.getHeader("Authorization"), "buyid");
        JSONObject json = new JSONObject();
        map.put("buyid", buyid);
        json = subPayLogInterface.SubPayLogUpdate(goodsid, map);
        return json;
    }

    @PostMapping("/SubPayLogSelect")
    public JSONObject SubPayLogSelect(@RequestParam Map<String, Object> map, HttpServletRequest request) {
        String buyid = JWTUtil.getUsername(request.getHeader("Authorization"), "buyid");
        JSONObject json = new JSONObject();
        map.put("buyid", buyid);
        System.out.println("--map--" + map);
        json = subPayLogInterface.SubPayLogSelect(map);
        return json;
    }

}
