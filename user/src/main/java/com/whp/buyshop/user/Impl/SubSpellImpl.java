package com.whp.buyshop.user.Impl;

import com.alibaba.fastjson.JSONObject;
import com.whp.buyshop.user.Dao.SubOrderDao;
import com.whp.buyshop.user.Dao.SubSpellDao;
import com.whp.buyshop.user.Dao.SubUserDao;
import com.whp.buyshop.user.Interface.SubSpellInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SubSpellImpl implements SubSpellInterface {
    @Autowired
    private SubSpellDao spellDao;
    @Autowired
    private SubUserDao subUserDao;
    @Autowired
    private SubOrderDao subOrderDao;

    @Override
    public JSONObject SubSpellSelect(Map<String, Object> map) {
        JSONObject json = new JSONObject();
        try {
            System.out.println(map);
            List<Map<String, Object>> list = spellDao.SubSpellSelectByPage(map, Integer.parseInt(map.get("start").toString()), Integer.parseInt(map.get("end").toString()));
            int count = spellDao.SubSpellSelectByCount(map);
            json.put("spell", list);
            json.put("count", count);
            json.put("code", 100);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
        }
        return json;
    }

    @Override
    public JSONObject SubSpellUpdate(String[] spellid, Map<String, Object> map) {
        JSONObject json = new JSONObject();
        try {
            Map<String, Object> map1 = spellDao.SubSpellSelectById(map);
            if (map1.get("refund").toString().equals("0")) {
                map.put("orderid", map1.get("orderid").toString());
                map.put("userid", map1.get("userid").toString());
                Map<String, Object> map2 = subOrderDao.SubOrderSelectById(map.get("orderid").toString());
                JSONObject object = new JSONObject(map2);
                if (object.getInteger("paystate") == 0) {
                    map.put("payment", object.getBigDecimal("payment"));
                    subUserDao.SubUserUpdateSurplus(map);
                    for (String pid : spellid) {
                        map.put("spellid", pid);
                        spellDao.SubSpellUpdate(map);
                    }
                    subOrderDao.SubOrderLogInsert(map);
                    json.put("code", 100);
                } else {//微信支付

                }

            } else {
                json.put("message", "已退款");
                System.out.println("已退款");
            }

        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
        }
        return json;
    }
}
