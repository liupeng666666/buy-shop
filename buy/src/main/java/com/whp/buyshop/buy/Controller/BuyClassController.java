package com.whp.buyshop.buy.Controller;

import com.alibaba.fastjson.JSONObject;
import com.whp.buyshop.buy.Interface.BuyClassInterface;
import com.whp.buyshop.utils.util.ImgUtil;
import com.whp.buyshop.utils.util.JWTUtil;
import com.whp.buyshop.utils.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * @author : 张吉伟
 * @data : 2019/10/10 12:55
 * @descrpition :
 */
@RestController
@RequestMapping("BuyClass")
public class BuyClassController {
    @Autowired
    private BuyClassInterface buyClassInterface;
    @Autowired
    private ImgUtil imgUtil;
    @Value("${fast.url}")
    private String url;

    @PostMapping("BuyClassSelect")
    public JSONObject BuyClassSelect(HttpServletRequest request) {
        String buyid = JWTUtil.getUsername(request.getHeader("Authorization"), "buyid");
        JSONObject json = buyClassInterface.BuyClassSelect(buyid);
        return json;
    }

    @PostMapping("BuyClassSelectState")
    public JSONObject BuyClassSelectState(HttpServletRequest request) {
        String buyid = JWTUtil.getUsername(request.getHeader("Authorization"), "buyid");
        JSONObject json = buyClassInterface.BuyClassSelectState(buyid);
        return json;
    }

    @PostMapping("BuyClassAdminSelect")
    public JSONObject BuyClassAdminSelect() {
        JSONObject json = buyClassInterface.BuyClassAdminSelect();
        return json;
    }

    @PostMapping("BuyClassInsert")
    public JSONObject BuyClassInsert(@RequestParam(value = "file", required = false) MultipartFile file, @RequestParam(value = "filepic", required = false) MultipartFile filepic, @RequestParam Map<String, Object> map, HttpServletRequest request) {
        String buyid = JWTUtil.getUsername(request.getHeader("Authorization"), "buyid");
        String sys_userid = JWTUtil.getUsername(request.getHeader("Authorization"), "pid");
        map.put("sys_userid", sys_userid);
        map.put("buyid", buyid);
        if (file != null) {
            String ul = imgUtil.ClassImg(file);
            map.put("img", url + ul);
        }
        if (filepic != null) {
            String ul = imgUtil.ClassImg(filepic);
            map.put("holiday_img", url + ul);
        }
        JSONObject json = buyClassInterface.BuyClassInsert(map);
        return json;
    }

    @PostMapping("BuyClassUpdate")
    public JSONObject BuyClassUpdate(@RequestParam(value = "file", required = false) MultipartFile file, @RequestParam(value = "filepic", required = false) MultipartFile filepic, @RequestParam Map<String, Object> map, HttpServletRequest request) {
        String buyid = JWTUtil.getUsername(request.getHeader("Authorization"), "buyid");
        String sys_userid = JWTUtil.getUsername(request.getHeader("Authorization"), "pid");
        map.put("sys_userid", sys_userid);
        map.put("buyid", buyid);
        if (file != null) {
            String ul = imgUtil.ClassImg(file);
            map.put("img", url + ul);
        }
        if (filepic != null) {
            String ul = imgUtil.ClassImg(filepic);
            map.put("holiday_img", url + ul);
        }
        JSONObject json = buyClassInterface.BuyClassUpdate(map);
        return json;
    }

    @PostMapping("BuyClassUpdateState")
    public JSONObject BuyClassUpdateState(@RequestParam("pid[]") String[] pid, @RequestParam Map<String, Object> map, HttpServletRequest request) {
        JSONObject json = new JSONObject();
        String sys_userid = JWTUtil.getUsername(request.getHeader("Authorization"), "pid");
        String buyid = JWTUtil.getUsername(request.getHeader("Authorization"), "buyid");
        map.put("sys_userid", sys_userid);
        map.put("buyid", buyid);
        for (String id : pid) {
            map.put("pid", id);
            json = buyClassInterface.BuyClassUpdateState(map);
        }
        if (json.getInteger("code") == 100) {
            System.out.println(map);
            RedisUpdate(map);
        }
        return json;
    }

    public void RedisUpdate(Map<String, Object> map) {
        RedisUtils.DEL("class:" + map.get("buyid").toString() + ":" + map.get("pid").toString(), 6);
        RedisUtils.DEL("map:class:" + map.get("buyid").toString() + ":" + map.get("pid").toString(), 6);
        RedisUtils.DEL("now:" + map.get("buyid").toString(), 6);
        RedisUtils.DEL("map:now:" + map.get("buyid").toString(), 6);

    }

}
