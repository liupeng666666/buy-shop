package com.whp.buyshop.buy.Controller;

import com.alibaba.fastjson.JSONObject;
import com.whp.buyshop.buy.Interface.SysRoleInterface;
import com.whp.buyshop.utils.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : 张吉伟
 * @data : 2019/9/9 16:07
 * @descrpition :
 */
@RestController
@RequestMapping("SysRole")
public class SysRoleController {

    @Autowired
    private SysRoleInterface sysRoleInterface;

    @PostMapping("SysRoleSelect")
    public JSONObject SysRoleSelect(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        String buyid = JWTUtil.getUsername(request.getHeader("Authorization"), "buyid");
        json = sysRoleInterface.SysRoleSelect(buyid);
        return json;
    }

    @PostMapping("SysRoleInsert")
    public JSONObject SysRoleInsert(@RequestParam("pid[]") String[] pid, String name, HttpServletRequest request) {
        JSONObject json = new JSONObject();
        String buyid = JWTUtil.getUsername(request.getHeader("Authorization"), "buyid");
        String userid = JWTUtil.getUsername(request.getHeader("Authorization"), "pid");
        Map<String, Object> map = new HashMap<>();
        map.put("buyid", buyid);
        map.put("userid", userid);
        map.put("name", name);
        json = sysRoleInterface.SysRoleInsert(map, pid);
        return json;
    }

    @PostMapping("SysRoleModuleSelect")
    public JSONObject SysRoleModuleSelect(String roleid) {
        JSONObject json = new JSONObject();
        json = sysRoleInterface.SysRoleModuleSelect(roleid);
        return json;
    }

    @PostMapping("SysRoleUpdate")
    public JSONObject SysRoleUpdate(@RequestParam("pid[]") String[] pid, String name, String roleid, HttpServletRequest request) {
        JSONObject json = new JSONObject();
        String userid = JWTUtil.getUsername(request.getHeader("Authorization"), "pid");
        Map<String, Object> map = new HashMap<>();
        json = sysRoleInterface.SysRoleUpdate(roleid, name, userid, pid);
        return json;
    }

    @PostMapping("SysRoleState")
    public JSONObject SysRoleState(@RequestParam("pid[]") String[] pid, String state, String isdel, HttpServletRequest request) {
        JSONObject json = new JSONObject();
        String userid = JWTUtil.getUsername(request.getHeader("Authorization"), "pid");
        Map<String, Object> map = new HashMap<>();
        map.put("userid", userid);
        map.put("state", state);
        map.put("isdel", isdel);
        for (String id : pid) {
            map.put("roleid", id);
            json = sysRoleInterface.SysRoleState(map);
        }
        return json;
    }
}
