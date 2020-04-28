package com.whp.buyshop.buy.Impl;

import com.alibaba.fastjson.JSONObject;
import com.whp.buyshop.buy.Dao.BuyMumberMoneyDao;
import com.whp.buyshop.buy.Dao.BuyMumberWithdrawDao;
import com.whp.buyshop.buy.Interface.BuyMumberMoneyInterface;
import com.whp.buyshop.utils.util.SnowFlake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author : 张吉伟
 * @data : 2019/9/12 11:57
 * @descrpition :
 */
@Service
public class BuyMumberMoneyImpl implements BuyMumberMoneyInterface {
    @Autowired
    private BuyMumberMoneyDao buyMumberMoneyDao;
    @Autowired
    private BuyMumberWithdrawDao buyMumberWithdrawDao;

    @Override
    public JSONObject BuyMumberMoneySelect(String buyid) {
        JSONObject json = new JSONObject();
        try {
            List<Map<String, Object>> list = buyMumberMoneyDao.BuyMumberMoneySelect(buyid);
            json.put("code", 100);
            json.put("money", list);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
        }
        return json;
    }

    @Override
    public JSONObject BuyMumberMoneyUpdate(Map<String, Object> map) {
        JSONObject json = new JSONObject();
        try {
            Map<String, Object> map1 = buyMumberMoneyDao.BuyMumberMoneySelectByMumberId(map.get("mumberid").toString());
            map.put("before_money", map1.get("surplus").toString());//得到余额
            SnowFlake snowFlake = new SnowFlake(1, 1);
            map.put("pid", snowFlake.nextId());
            JSONObject json1 = new JSONObject(map);
            if (json1.getBigDecimal("before_money").compareTo(json1.getBigDecimal("money")) == -1) {
                json.put("message", "输入金额不能大于剩余金额!");
            } else {
                buyMumberMoneyDao.BuyMumberMoneyUpdate(map);//更新money表
                buyMumberWithdrawDao.BuyMumberWithdrawInsert(map);
                json.put("code", 100);
            }


        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
        }
        return json;
    }
}
