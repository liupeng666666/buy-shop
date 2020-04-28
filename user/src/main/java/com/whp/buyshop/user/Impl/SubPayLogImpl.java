package com.whp.buyshop.user.Impl;

import com.alibaba.fastjson.JSONObject;
import com.whp.buyshop.user.Dao.SubOrderDao;
import com.whp.buyshop.user.Dao.SubPayLogDao;
import com.whp.buyshop.user.Dao.SubUserDao;
import com.whp.buyshop.user.Interface.SubPayLogInterface;
import com.whp.buyshop.utils.util.SnowFlake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SubPayLogImpl implements SubPayLogInterface {
    @Autowired
    private SubPayLogDao subPayLogDao;
    @Autowired
    private SubUserDao subUserDao;
    @Autowired
    private SubOrderDao subOrderDao;

    @Override
    public JSONObject SubPayLogUpdate(String[] goodsid, Map<String, Object> map) {
        JSONObject json = new JSONObject();
        try {
            SnowFlake snowFlake = new SnowFlake(1, 1);
            map.put("pid", snowFlake.nextId());
            map.put("id", snowFlake.nextId());
            Map<String, Object> map2 = subOrderDao.SubOrderSelectById(map.get("orderid").toString());
            JSONObject object = new JSONObject(map2);
            if (object.getInteger("paystate") == 0) {
                subUserDao.SubUserUpdateSurplus(map);
            } else {//微信支付

            }

            for (String pid : goodsid) {
                map.put("goodsid", pid);
                subOrderDao.SubOrderListUpdate(map);
            }
            System.out.println("map------------" + map);
            subPayLogDao.SubPayLogInsert(map);
            subOrderDao.SubOrderUpdate(map);
            subOrderDao.SubOrderLogInsert(map);
            json.put("code", 100);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
        }
        return json;
    }

    @Override
    public JSONObject SubPayLogSelect(Map<String, Object> map) {
        JSONObject json = new JSONObject();
        try {
            List<Map<String, Object>> list1 = new ArrayList<>();
            List<Map<String, Object>> list = subPayLogDao.SubPayLogSelectByPage(map, Integer.parseInt(map.get("start").toString()), Integer.parseInt(map.get("end").toString()));
            int count = subPayLogDao.SubPayLogSelectByCount(map);
            for (Map payLog : list) {
                map.put("orderid", payLog.get("orderid"));
                List<Map<String, Object>> map1 = subOrderDao.SubOrderListSelectById(map);
                payLog.put("list", map1);
                list1.add(payLog);
            }
            json.put("count", count);
            json.put("payLog", list1);
            json.put("code", 100);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
        }
        return json;
    }
}
