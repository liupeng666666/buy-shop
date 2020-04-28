package com.whp.buyshop.wx.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.whp.buyshop.mq.Interface.MqOrderInterface;
import com.whp.buyshop.utils.util.JWTUtil;
import com.whp.buyshop.utils.util.SnowFlake;
import com.whp.buyshop.wx.Interface.WxMenuInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/WxMenu")
public class WxMenuController {
    @Autowired
    private WxMenuInterface wxMenuInterface;
    @Autowired
    private MqOrderInterface mqOrderInterface;

    @PostMapping("WxMenuInsert")
    public JSONObject WxMenuInsert(@RequestParam Map<String, Object> map, HttpServletRequest request) {
        JSONObject json = new JSONObject();
        String buyid = JWTUtil.getUsername(request.getHeader("Authorization"), "buyid");
        SnowFlake snowFlake = new SnowFlake(1, 1);
        map.put("pid", snowFlake.nextId());
        map.put("buyid", buyid);
        if (map.get("type").toString().equals("2")) {
            map.put("url", "www.whpck.com/");
            map.put("pagepath", "/pages/home/home");
        }
        json = wxMenuInterface.WxMenuInsert(map);
        if (json.getInteger("code") == 100) {
            JSONObject xjson = new JSONObject();
            xjson.put("buyid", buyid);
            xjson.put("type", 1);
            JSONObject cjson = new JSONObject();
            cjson.put("queue", "wx_menu");
            cjson.put("message", xjson);
            mqOrderInterface.MqOrderInsert(cjson);
        }
        return json;
    }

    @PostMapping("WxMenuSelect")
    public JSONObject WxMenuSelect(@RequestParam Map<String, Object> map, int page, int num, HttpServletRequest request) {
        String buyid = JWTUtil.getUsername(request.getHeader("Authorization"), "buyid");
        map.put("buyid", buyid);
        JSONObject json = wxMenuInterface.WxMenuSelect(map, page, num);
        return json;
    }

    @PostMapping("WxMenuUpdate")
    public JSONObject WxMenuUpdate(@RequestParam Map<String, Object> map, HttpServletRequest request) {
        JSONObject json = new JSONObject();
        String buyid = JWTUtil.getUsername(request.getHeader("Authorization"), "buyid");
        map.put("buyid", buyid);
        json = wxMenuInterface.WxMenuUpdate(map);
        if (json.getInteger("code") == 100) {
            JSONObject xjson = new JSONObject();
            xjson.put("buyid", buyid);
            xjson.put("type", 2);
            JSONObject cjson = new JSONObject();
            cjson.put("queue", "wx_menu");
            cjson.put("message", xjson);
            mqOrderInterface.MqOrderInsert(cjson);
        }
        return json;
    }

    @PostMapping("WxMenuUpdateState")
    public JSONObject WxMenuUpdateState(@RequestParam("pid[]") String[] pid, @RequestParam Map<String, Object> map, HttpServletRequest request) {
        JSONObject json = new JSONObject();
        String sys_userid = JWTUtil.getUsername(request.getHeader("Authorization"), "pid");
        String buyid = JWTUtil.getUsername(request.getHeader("Authorization"), "buyid");
        map.put("sys_userid", sys_userid);
        for (String id : pid) {
            map.put("pid", id);
            json = wxMenuInterface.WxMenuUpdateState(map);
        }
        if (json.getInteger("code") == 100) {
            JSONObject xjson = new JSONObject();
            xjson.put("buyid", buyid);
            xjson.put("type", 2);
            JSONObject cjson = new JSONObject();
            cjson.put("queue", "wx_menu");
            cjson.put("message", xjson);
            mqOrderInterface.MqOrderInsert(cjson);
        }
        return json;
    }

    @PostMapping("WxMenuSelectmenu")
    public JSONObject WxMenuSelectmenu(@RequestParam Map<String, Object> map, HttpServletRequest request) {
        String buyid = JWTUtil.getUsername(request.getHeader("Authorization"), "buyid");
        map.put("buyid", buyid);
        JSONObject json = wxMenuInterface.WxMenuSelectmenu(map);
        return json;
    }

    @PostMapping("WxMenuSelectById")
    public JSONObject WxMenuSelectById(@RequestParam Map<String, Object> map, HttpServletRequest request) {
        String buyid = JWTUtil.getUsername(request.getHeader("Authorization"), "buyid");
        map.put("buyid", buyid);
        JSONObject json = wxMenuInterface.WxMenuSelectById(map);
        return json;
    }
}
