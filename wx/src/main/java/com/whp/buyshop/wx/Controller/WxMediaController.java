package com.whp.buyshop.wx.Controller;

import com.alibaba.fastjson.JSONObject;
import com.whp.buyshop.mq.Interface.MqOrderInterface;
import com.whp.buyshop.utils.util.ImgUtil;
import com.whp.buyshop.utils.util.JWTUtil;
import com.whp.buyshop.utils.util.MVUtil;
import com.whp.buyshop.utils.util.SnowFlake;
import com.whp.buyshop.wx.Interface.WxMediaInterface;
import com.whp.buyshop.wx.Interface.WxMenuInterface;
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
@RequestMapping("/WxMedia")
public class WxMediaController {
    @Autowired
    private WxMediaInterface wxMediaInterface;
    @Autowired
    private ImgUtil imgUtil;
    @Autowired
    private MVUtil mvUtil;
    @Value("${fast.url}")
    private String url;

    @PostMapping("WxMediaInsert")
    public JSONObject WxMenuInsert(@RequestParam(value = "file", required = false) MultipartFile file, @RequestParam Map<String, Object> map, HttpServletRequest request) {
        JSONObject json = new JSONObject();
        String buyid = JWTUtil.getUsername(request.getHeader("Authorization"), "buyid");
        String sys_userid = JWTUtil.getUsername(request.getHeader("Authorization"), "pid");
        map.put("buyid", buyid);
        map.put("sys_userid", sys_userid);

        if (file != null) {
            JSONObject fjson = new JSONObject();
            if (map.get("style").equals("0") || map.get("style").toString().equals("3")) {
                if (map.get("size").toString().equals("1")) {
                    fjson = imgUtil.ImgMediaSuo(url, file);
                } else {
                    fjson = imgUtil.ImgMediaPic(url, file);
                }

                map.put("url", fjson.toString());
            } else {
                fjson = mvUtil.MVMedia(url, file);
                System.out.println("file----" + fjson.toString());
                map.put("url", fjson.toString());
            }
        }
        json = wxMediaInterface.WxMediaInsert(map, file);

        return json;
    }

    @PostMapping("WxMediaSelect")
    public JSONObject WxMenuSelect(@RequestParam Map<String, Object> map, int page, int num, HttpServletRequest request) {
        String buyid = JWTUtil.getUsername(request.getHeader("Authorization"), "buyid");
        map.put("buyid", buyid);
        JSONObject json = wxMediaInterface.WxMediaSelect(map, page, num);
        return json;
    }

    @PostMapping("WxMediaSelectById")
    public JSONObject WxMediaSelectById(HttpServletRequest request) {
        String buyid = JWTUtil.getUsername(request.getHeader("Authorization"), "buyid");
        JSONObject json = wxMediaInterface.WxMediaSelectById(buyid);
        return json;
    }

    @PostMapping("WxMediaSelectByStyle")
    public JSONObject WxMediaSelectByStyle(@RequestParam Map<String, Object> map, HttpServletRequest request) {
        String buyid = JWTUtil.getUsername(request.getHeader("Authorization"), "buyid");
        map.put("style", 3);
        map.put("buyid", buyid);
        JSONObject json = wxMediaInterface.WxMediaSelectByStyle(map);
        return json;
    }

    @PostMapping("WxMediaUpdateState")
    public JSONObject WxMediaUpdateState(@RequestParam("pid[]") String[] pid, @RequestParam Map<String, Object> map, HttpServletRequest request) {
        JSONObject json = new JSONObject();
        String sys_userid = JWTUtil.getUsername(request.getHeader("Authorization"), "pid");
        String buyid = JWTUtil.getUsername(request.getHeader("Authorization"), "buyid");
        map.put("sys_userid", sys_userid);
        for (String id : pid) {
            map.put("pid", id);
            json = wxMediaInterface.WxMediaUpdateState(map);
        }

        return json;
    }

}
