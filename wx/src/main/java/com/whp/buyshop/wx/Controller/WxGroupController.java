package com.whp.buyshop.wx.Controller;

import com.alibaba.fastjson.JSONObject;
import com.whp.buyshop.utils.util.JWTUtil;
import com.whp.buyshop.wx.Interface.WxGroupInterface;
import com.whp.buyshop.wx.Interface.WxReplyInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


@RestController
@RequestMapping("WxGroup")
public class WxGroupController {
    @Autowired
    private WxGroupInterface wxGroupInterface;

    @PostMapping("WxGroupSelect")
    public JSONObject WxGroupSelect(@RequestParam Map<String, Object> map, int page, int num, HttpServletRequest request) {
        String buyid = JWTUtil.getUsername(request.getHeader("Authorization"), "buyid");
        JSONObject json = wxGroupInterface.WxGroupSelect(buyid, map, page, num);
        return json;
    }

    @PostMapping("WxGroupInsert")
    public JSONObject WxGroupInsert(@RequestParam(value = "newsid[]", required = false) String[] newsid, @RequestParam Map<String, Object> map, HttpServletRequest request) {
        String buyid = JWTUtil.getUsername(request.getHeader("Authorization"), "buyid");
        String sys_userid = JWTUtil.getUsername(request.getHeader("Authorization"), "pid");
        map.put("sys_userid", sys_userid);
        map.put("buyid", buyid);
        JSONObject json = wxGroupInterface.WxGroupInsert(newsid, map);
        return json;
    }

    @PostMapping("WxGroupUpdate")
    public JSONObject WxGroupUpdate(@RequestParam(value = "newsid[]", required = false) String[] newsid, @RequestParam Map<String, Object> map, HttpServletRequest request) {
        String buyid = JWTUtil.getUsername(request.getHeader("Authorization"), "buyid");
        String sys_userid = JWTUtil.getUsername(request.getHeader("Authorization"), "pid");
        map.put("sys_userid", sys_userid);
        map.put("buyid", buyid);

        JSONObject json = wxGroupInterface.WxGroupUpdate(newsid, map);
        return json;
    }

    @PostMapping("WxGroupUpdateState")
    public JSONObject WxReplyUpdateState(@RequestParam("pid[]") String[] pid, @RequestParam Map<String, Object> map, HttpServletRequest request) {
        JSONObject json = new JSONObject();
        String buyid = JWTUtil.getUsername(request.getHeader("Authorization"), "buyid");
        String sys_userid = JWTUtil.getUsername(request.getHeader("Authorization"), "pid");
        map.put("sys_userid", sys_userid);
        map.put("buyid", buyid);

        for (String id : pid) {
            map.put("pid", id);
            json = wxGroupInterface.WxGroupUpdateState(map);
        }

        return json;
    }


    @PostMapping("WxGroupUpdateType")
    public JSONObject WxGroupUpdateType(String pid, HttpServletRequest request) {
        String buyid = JWTUtil.getUsername(request.getHeader("Authorization"), "buyid");
        JSONObject json = wxGroupInterface.WxGroupUpdateType(buyid, pid);
        return json;
    }
}
