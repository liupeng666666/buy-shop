package com.whp.buyshop.buy.Controller;

import com.alibaba.fastjson.JSONObject;
import com.whp.buyshop.buy.Interface.SysUserInterface;
import com.whp.buyshop.utils.util.ImgUtil;
import com.whp.buyshop.utils.util.JWTUtil;
import com.whp.buyshop.utils.util.SnowFlake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author : 张吉伟
 * @data : 2019/9/9 16:07
 * @descrpition :
 */
@RestController
@RequestMapping("SysUser")
public class SysUserController {

    @Autowired
    private SysUserInterface sysUserInterface;
    @Autowired
    private ImgUtil imgUtil;
    @Value("${fast.url}")
    private String url;

    @PostMapping("SysUserSelect")
    public JSONObject SysRoleSelect(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        String buyid = JWTUtil.getUsername(request.getHeader("Authorization"), "buyid");
        json = sysUserInterface.SysUserSelect(buyid);
        return json;
    }

    @PostMapping("SysUserInsert")
    public JSONObject SysRoleInsert(@RequestParam("file") MultipartFile file, @RequestParam Map<String, String> map, HttpServletRequest request) {
        JSONObject json = new JSONObject();
        String buyid = JWTUtil.getUsername(request.getHeader("Authorization"), "buyid");
        String userid = JWTUtil.getUsername(request.getHeader("Authorization"), "pid");
        String urlimg = imgUtil.FileImg(file);
        System.out.println("file:" + url + urlimg);
        map.put("img", url + urlimg);
        map.put("userid", userid);
        map.put("buyid", buyid);
        SnowFlake snowFlake = new SnowFlake(1, 1);
        map.put("pid", snowFlake.nextId() + "");
        json = sysUserInterface.SysUserInsert(map);
        return json;
    }

    @PostMapping("SysUserUpdate")
    public JSONObject SysUserUpdate(@RequestParam(value = "file", required = false) MultipartFile file, @RequestParam Map<String, Object> map, HttpServletRequest request) {
        JSONObject json = new JSONObject();
        String userid = JWTUtil.getUsername(request.getHeader("Authorization"), "pid");
        map.put("userid", userid);
        if (file != null) {
            String urlimg = imgUtil.FileImg(file);
            map.put("img", url + urlimg);
        }
        json = sysUserInterface.SysUserUpdate(map);
        return json;
    }

    @PostMapping("SysUserState")
    public JSONObject SysUserState(@RequestParam("pid[]") String[] pid, @RequestParam Map<String, Object> map, HttpServletRequest request) {
        JSONObject json = new JSONObject();
        String userid = JWTUtil.getUsername(request.getHeader("Authorization"), "pid");
        map.put("userid", userid);
        for (String id : pid) {
            map.put("pid", id);
            json = sysUserInterface.SysUserUpdate(map);
        }

        return json;
    }
}
