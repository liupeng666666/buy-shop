package com.whp.buyshop.buy.Impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.whp.buyshop.buy.Dao.SysBuyDao;
import com.whp.buyshop.buy.Dao.SysModuleDao;
import com.whp.buyshop.buy.Interface.SysBuyInterface;
import com.whp.buyshop.utils.util.MD5Util;
import com.whp.buyshop.utils.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * @author : 张吉伟
 * @data : 2019/9/6 10:34
 * @descrpition :
 */
@Service
public class SysBuyImpl implements SysBuyInterface {
    @Autowired
    private SysBuyDao sysBuyDao;
    @Autowired
    private SysModuleDao sysModuleDao;

    @Override
    public JSONObject SysBuySelect(String pid) {
        System.out.println("pid:" + pid);
        JSONObject json = new JSONObject();
        try {
            String value = RedisUtils.get("buy:shop:" + pid, 3);
            if (value == null || value == "") {
                Map<String, Object> map = sysBuyDao.getSysBuySelect(pid);
                RedisUtils.set_time("buy:shop:" + pid, new JSONObject(map).toString(), 3, 60 * 60);
                json.put("buy", map);
            } else {
                json.put("buy", JSONObject.parse(value));
            }

            json.put("code", 100);

        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
        }
        return json;
    }

    @Override
    public JSONObject SysBuyLogin(String username, String pasword, String yzm, HttpSession session) {
        JSONObject json = new JSONObject();
        try {
            String yangzhengma = (String) session.getAttribute("randomString");
            if (!yangzhengma.equals(yzm.toUpperCase())) {
                json.put("code", 104);
            } else {
                if (username != null && !"".equals(username) && pasword != null && !"".equals(pasword)) {
                    pasword = MD5Util.MD5(pasword);
                    Map<String, Object> map = sysBuyDao.SysBuyLogin(username, pasword);
                    if (map == null) {
                        json.put("code", 102);
                    } else {
                        if (map.containsKey("roleid")) {
                            List<Map<String, Object>> list = sysModuleDao.SysModuleSelect(map.get("roleid").toString());
                            if (list.size() == 0) {
                                json.put("code", 105);
                            } else {
                                json.put("code", 100);
                                json.put("buy", map);
                                json.put("role", list);
                            }
                        } else {
                            json.put("code", 105);
                        }

                    }

                } else {
                    json.put("code", 101);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
        }
        return json;
    }
}
