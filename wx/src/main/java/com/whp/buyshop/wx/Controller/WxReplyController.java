package com.whp.buyshop.wx.Controller;

import com.alibaba.fastjson.JSONObject;
import com.whp.buyshop.utils.util.JWTUtil;
import com.whp.buyshop.wx.Interface.WxReplyInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author : 张吉伟
 * @data : 2019/12/15 17:37
 * @descrpition :
 */
@RestController
@RequestMapping("WxReply")
public class WxReplyController {
    @Autowired
    private WxReplyInterface wxReplyInterface;

    @PostMapping("WxReplySelect")
    public JSONObject WxReplySelect(@RequestParam Map<String, Object> map, int page, int num, HttpServletRequest request) {
        String buyid = JWTUtil.getUsername(request.getHeader("Authorization"), "buyid");
        JSONObject json = wxReplyInterface.WxReplySelect(buyid, map, page, num);
        return json;
    }

    @PostMapping("WxReplyInsert")
    public JSONObject WxReplyInsert(@RequestParam(value = "newsid[]", required = false) String[] newsid, @RequestParam Map<String, Object> map, HttpServletRequest request) {
        String buyid = JWTUtil.getUsername(request.getHeader("Authorization"), "buyid");
        String sys_userid = JWTUtil.getUsername(request.getHeader("Authorization"), "pid");
        map.put("sys_userid", sys_userid);
        map.put("buyid", buyid);

        if (map.get("event").equals("click")) {
            map.put("key", map.get("menu"));
        }

        JSONObject json = wxReplyInterface.WxReplyInsert(newsid, map);
        return json;
    }

    @PostMapping("WxReplyUpdate")
    public JSONObject WxReplyUpdate(@RequestParam(value = "newsid[]", required = false) String[] newsid, @RequestParam Map<String, Object> map, HttpServletRequest request) {
        String buyid = JWTUtil.getUsername(request.getHeader("Authorization"), "buyid");
        String sys_userid = JWTUtil.getUsername(request.getHeader("Authorization"), "pid");
        map.put("sys_userid", sys_userid);
        map.put("buyid", buyid);
        if (map.containsKey("type")) {
            System.out.println("map----rep-up--" + map.get("type").toString());
            if (map.get("type").toString().equals("news")) {
                map.put("mediaid", "");
                map.put("text", "");
            } else if (map.get("type").toString().equals("text")) {
                map.put("mediaid", "");
                newsid = null;
            } else {
                map.put("text", "");
                newsid = null;
            }
            map.put("ittype", 0);
        }
        if (map.get("event").equals("click")) {
            map.put("key", map.get("menu"));
        } else if (map.get("event").equals("subscribe")) {
            map.put("key", "");
        }
        JSONObject json = wxReplyInterface.WxReplyUpdate(newsid, map);
        return json;
    }

    @PostMapping("WxReplyUpdateState")
    public JSONObject WxReplyUpdateState(@RequestParam("pid[]") String[] pid, @RequestParam Map<String, Object> map, HttpServletRequest request) {
        JSONObject json = new JSONObject();
        String buyid = JWTUtil.getUsername(request.getHeader("Authorization"), "buyid");
        String sys_userid = JWTUtil.getUsername(request.getHeader("Authorization"), "pid");
        map.put("sys_userid", sys_userid);
        map.put("buyid", buyid);

        for (String id : pid) {
            map.put("pid", id);
            System.out.println("map-------" + map);
            json = wxReplyInterface.WxReplyUpdateState(map);
        }

        return json;
    }
}
