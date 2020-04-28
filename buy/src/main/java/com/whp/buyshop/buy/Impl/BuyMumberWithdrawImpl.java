package com.whp.buyshop.buy.Impl;

import com.alibaba.fastjson.JSONObject;
import com.whp.buyshop.buy.Dao.BuyMumberMoneyDao;
import com.whp.buyshop.buy.Dao.BuyMumberWithdrawDao;
import com.whp.buyshop.buy.Interface.BuyMumberWithdrawInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author : 张吉伟
 * @data : 2019/9/26 17:20
 * @descrpition :
 */
@Service
public class BuyMumberWithdrawImpl implements BuyMumberWithdrawInterface {

    @Autowired
    private BuyMumberWithdrawDao buyMumberWithdrawDao;
    @Autowired
    private BuyMumberMoneyDao buyMumberMoneyDao;

    @Override
    public JSONObject BuyNumberWithdrawSelect(String buyid, String mumberid, String state, int page, int num) {
        JSONObject json = new JSONObject();
        try {
            List<Map<String, Object>> list = buyMumberWithdrawDao.BuyMumberWithdrawSelect(buyid, mumberid, state, page, num);
            int count = buyMumberWithdrawDao.BuyMumberWithdrawCount(buyid, mumberid, state);
            json.put("withdraw", list);
            json.put("count", count);
            json.put("code", 100);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
        }
        return json;
    }

    @Override
    public JSONObject BuyMumberWithdrawInsert(Map<String, Object> map) {
        JSONObject json = new JSONObject();
        try {
            buyMumberMoneyDao.BuyMumberMoneyUpdate(map);
            buyMumberWithdrawDao.BuyMumberWithdrawInsert(map);
            json.put("code", 100);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
        }
        return json;
    }

    @Override
    public JSONObject BuyMumberWithdrawUpdate(Map<String, Object> map) {
        JSONObject json = new JSONObject();
        try {
//            Map<String, Object> map1 = buyMumberMoneyDao.BuyMumberMoneySelectByMumberId(map.get("mumberid").toString());
//            map.put("before_money", map1.get("surplus").toString());
//            JSONObject json1=new JSONObject(map);
//            map.put("after_money", json1.getBigDecimal("before_money").subtract(json1.getBigDecimal("money")));

            buyMumberWithdrawDao.BuyMumberWithdrawUpdate(map);
            if (map.get("state").toString().equals("2")) {
                buyMumberMoneyDao.BuyMumberMoneyUpdateFail(map);
            }
            json.put("code", 100);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
        }
        return json;
    }
}
