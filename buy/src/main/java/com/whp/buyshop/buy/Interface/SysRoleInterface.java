package com.whp.buyshop.buy.Interface;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * @author : 张吉伟
 * @data : 2019/9/9 16:01
 * @descrpition :
 */
public interface SysRoleInterface {

    public JSONObject SysRoleSelect(String buyid);

    public JSONObject SysRoleInsert(Map<String, Object> map, String[] pid);

    public JSONObject SysRoleModuleSelect(String roleid);

    public JSONObject SysRoleUpdate(String roleid, String name, String userid, String[] pid);

    public JSONObject SysRoleState(Map<String, Object> map);
}
