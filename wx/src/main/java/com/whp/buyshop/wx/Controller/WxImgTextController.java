package com.whp.buyshop.wx.Controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.whp.buyshop.utils.util.ImgUtil;
import com.whp.buyshop.utils.util.JWTUtil;
import com.whp.buyshop.utils.util.SnowFlake;
import com.whp.buyshop.wx.Interface.WxImgTextInterface;
import com.whp.buyshop.wx.Interface.WxMediaInterface;
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
@RequestMapping("/WxImgText")
public class WxImgTextController {
    @Autowired
    private WxImgTextInterface wxImgTextInterface;
    @Autowired
    private ImgUtil imgUtil;
    @Value("${fast.url}")
    private String url;

    @PostMapping("WxImgTextSelectById")
    public JSONObject WxImgTextSelectById(@RequestParam Map<String, Object> map, HttpServletRequest request) {
        String buyid = JWTUtil.getUsername(request.getHeader("Authorization"), "buyid");
        map.put("buyid", buyid);
        JSONObject json = wxImgTextInterface.WxImgTextSelectById(map);
        return json;
    }

    @PostMapping("WxImgTextSelectByReplyId")
    public JSONObject WxImgTextSelectByReplyId(@RequestParam Map<String, Object> map, HttpServletRequest request) {
        String buyid = JWTUtil.getUsername(request.getHeader("Authorization"), "buyid");
        map.put("buyid", buyid);
        JSONObject json = wxImgTextInterface.WxImgTextSelectByReplyId(map);
        return json;
    }

    @PostMapping("WxImgTextInsert")
    public JSONObject WxImgTextInsert(@RequestParam(value = "file", required = false) MultipartFile file, @RequestParam Map<String, Object> map, HttpServletRequest request) {
        JSONObject json = new JSONObject();
        SnowFlake snowFlake = new SnowFlake(1, 1);
        String buyid = JWTUtil.getUsername(request.getHeader("Authorization"), "buyid");
        String sys_userid = JWTUtil.getUsername(request.getHeader("Authorization"), "pid");
        map.put("pid", snowFlake.nextId());
        map.put("buyid", buyid);
        map.put("sys_userid", sys_userid);
        if (file != null) {
            JSONObject fjson = imgUtil.ImgText(url, file);
            map.put("picurl", fjson.toString());
        } else {
            JSONObject json1 = new JSONObject();
            json1.put("thumbnail", map.get("picurlmedia"));
            map.put("picurl", json1.toString());
        }
        json = wxImgTextInterface.WxImgTextInsert(map);

        return json;
    }

    @PostMapping("WxImgTextSelect")
    public JSONObject WxImgTextSelect(@RequestParam Map<String, Object> map, int page, int num, HttpServletRequest request) {
        String buyid = JWTUtil.getUsername(request.getHeader("Authorization"), "buyid");
        map.put("buyid", buyid);
        JSONObject json = wxImgTextInterface.WxImgTextSelect(map, page, num);
        return json;
    }

    @PostMapping("WxImgTextUpdateState")
    public JSONObject WxImgTextUpdateState(@RequestParam("pid[]") String[] pid, @RequestParam Map<String, Object> map, HttpServletRequest request) {
        JSONObject json = new JSONObject();
        String sys_userid = JWTUtil.getUsername(request.getHeader("Authorization"), "pid");
        String buyid = JWTUtil.getUsername(request.getHeader("Authorization"), "buyid");
        map.put("sys_userid", sys_userid);
        for (String id : pid) {
            map.put("pid", id);
            json = wxImgTextInterface.WxImgTextUpdateState(map);
        }

        return json;
    }

    @PostMapping("WxImgTextUpdate")
    public JSONObject WxImgTextUpdate(@RequestParam(value = "file", required = false) MultipartFile file, @RequestParam Map<String, Object> map, HttpServletRequest request) {
        JSONObject json = new JSONObject();

        String buyid = JWTUtil.getUsername(request.getHeader("Authorization"), "buyid");
        String sys_userid = JWTUtil.getUsername(request.getHeader("Authorization"), "pid");

        map.put("buyid", buyid);
        map.put("sys_userid", sys_userid);
        if (file != null) {
            JSONObject fjson = imgUtil.ImgText(url, file);
            map.put("picurl", fjson.toString());
        } else {
            JSONObject json1 = new JSONObject();
            json1.put("thumbnail", map.get("picurlmedia"));
            map.put("picurl", json1.toString());
        }

        json = wxImgTextInterface.WxImgTextUpdate(map);

        return json;
    }
}
