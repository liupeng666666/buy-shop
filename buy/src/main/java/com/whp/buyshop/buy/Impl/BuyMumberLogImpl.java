package com.whp.buyshop.buy.Impl;

import com.alibaba.fastjson.JSONObject;
import com.whp.buyshop.buy.Dao.BuyMumberLogDao;
import com.whp.buyshop.buy.Interface.BuyMumberLogInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author : 张吉伟
 * @data : 2019/9/26 14:24
 * @descrpition :
 */
@Service
public class BuyMumberLogImpl implements BuyMumberLogInterface {
    @Autowired
    private BuyMumberLogDao buyMumberLogDao;

    @Override
    public JSONObject BuyNumberLogSelect(String buyid, String mumberid, int page, int num) {
        JSONObject json = new JSONObject();
        try {
            List<Map<String, Object>> list = buyMumberLogDao.BuyMumberLogSelect(buyid, mumberid, page, num);
            int count = buyMumberLogDao.BuyMumberLogCount(buyid, mumberid);
            json.put("log", list);
            json.put("count", count);
            json.put("code", 100);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
        }
        return json;
    }
}
