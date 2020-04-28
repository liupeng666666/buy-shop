package com.whp.buyshop.buy.Impl;

import com.alibaba.fastjson.JSONObject;
import com.whp.buyshop.buy.Dao.SysUserDao;
import com.whp.buyshop.buy.Interface.SysUserInterface;
import com.whp.buyshop.utils.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author : 张吉伟
 * @data : 2019/9/10 13:39
 * @descrpition :
 */
@Service
public class SysUserImpl implements SysUserInterface {
    @Autowired
    private SysUserDao sysUserDao;

    @Override
    public JSONObject SysUserSelect(String buyid) {
        JSONObject json = new JSONObject();
        try {
            List<Map<String, Object>> list = sysUserDao.SysUserSelect(buyid);
            json.put("user", list);
            json.put("code", 100);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
        }
        return json;
    }

    @Override
    public JSONObject SysUserInsert(Map<String, String> map) {
        JSONObject json = new JSONObject();
        try {
            System.out.println("phone:" + map.get("phone").toString());
            int count = sysUserDao.SysUserCx(map.get("phone").toString());
            if (count == 0) {
                String password = MD5Util.MD5(map.get("password").toString());
                map.put("password", password);
                sysUserDao.SysUserInsert(map);
                json.put("code", 100);
            } else {
                json.put("code", 101);
            }

        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
        }
        return json;
    }

    @Override
    public JSONObject SysUserUpdate(Map<String, Object> map) {
        JSONObject json = new JSONObject();
        try {
            if (map.containsKey("password")) {
                map.put("password", MD5Util.MD5(map.get("password").toString()));
            }
            sysUserDao.SysUserUpdate(map);
            json.put("code", 100);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
        }
        return json;
    }
}
