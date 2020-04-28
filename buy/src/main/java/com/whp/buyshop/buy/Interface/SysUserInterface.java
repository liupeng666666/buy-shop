package com.whp.buyshop.buy.Interface;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * @author : 张吉伟
 * @data : 2019/9/9 16:01
 * @descrpition :
 */
public interface SysUserInterface {

    public JSONObject SysUserSelect(String buyid);

    public JSONObject SysUserInsert(Map<String, String> map);

    public JSONObject SysUserUpdate(Map<String, Object> map);

}
