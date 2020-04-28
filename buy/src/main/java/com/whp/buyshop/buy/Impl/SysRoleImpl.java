package com.whp.buyshop.buy.Impl;

import com.alibaba.fastjson.JSONObject;
import com.whp.buyshop.buy.Dao.SysModuleDao;
import com.whp.buyshop.buy.Dao.SysRoleDao;
import com.whp.buyshop.buy.Interface.SysRoleInterface;
import com.whp.buyshop.utils.util.SnowFlake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author : 张吉伟
 * @data : 2019/9/9 16:02
 * @descrpition :
 */
@Service
public class SysRoleImpl implements SysRoleInterface {
    @Autowired
    private SysRoleDao sysRoleDao;
    @Autowired
    private SysModuleDao sysModuleDao;

    @Override
    public JSONObject SysRoleSelect(String buyid) {
        JSONObject json = new JSONObject();
        try {
            List<Map<String, Object>> list = sysRoleDao.SysRoleSelect(buyid);
            json.put("role", list);
            json.put("code", 100);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
        }
        return json;
    }

    @Override
    public JSONObject SysRoleInsert(Map<String, Object> map, String[] pid) {
        JSONObject json = new JSONObject();
        try {
            SnowFlake snowFlake = new SnowFlake(1, 1);
            long roleid = snowFlake.nextId();
            map.put("pid", roleid);
            sysRoleDao.SysRoleInsert(map);
            JSONObject cjson = new JSONObject();
            cjson.put("roleid", roleid);
            for (String id : pid) {
                cjson.put("pid", snowFlake.nextId());
                cjson.put("moduleid", id);
                sysRoleDao.SysRoleModuleInsert(cjson);
            }
            json.put("code", 100);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
        }
        return json;
    }

    @Override
    public JSONObject SysRoleModuleSelect(String roleid) {
        JSONObject json = new JSONObject();
        try {
            List<Map<String, Object>> list = sysModuleDao.SysModuleSelect(roleid);
            json.put("module", list);
            json.put("code", 100);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
        }
        return json;
    }

    @Override
    public JSONObject SysRoleUpdate(String roleid, String name, String userid, String[] pid) {
        JSONObject json = new JSONObject();
        try {
            SnowFlake snowFlake = new SnowFlake(1, 1);

            JSONObject cjson = new JSONObject();
            cjson.put("roleid", roleid);
            cjson.put("name", name);
            cjson.put("userid", userid);
            sysRoleDao.SysRoleUpdate(cjson);
            sysRoleDao.SysRoleModuleDel(roleid);
            for (String id : pid) {
                cjson.put("pid", snowFlake.nextId());
                cjson.put("moduleid", id);
                sysRoleDao.SysRoleModuleInsert(cjson);
            }
            json.put("code", 100);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
        }
        return json;
    }

    @Override
    public JSONObject SysRoleState(Map<String, Object> map) {
        JSONObject json = new JSONObject();
        try {
            sysRoleDao.SysRoleUpdate(map);
            json.put("code", 100);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
        }
        return json;
    }
}
