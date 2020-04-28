package com.whp.buyshop.buy.Impl;

import com.alibaba.fastjson.JSONObject;
import com.whp.buyshop.buy.Dao.BuyIpLogDao;
import com.whp.buyshop.buy.Interface.BuyIpLogInteface;
import com.whp.buyshop.utils.util.HttpClientUtils;
import com.whp.buyshop.utils.util.IpUtils;
import com.whp.buyshop.utils.util.SnowFlake;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author : 张吉伟
 * @data : 2019/11/1 9:52
 * @descrpition :
 */
@Service
public class BuyIpLogImpl implements BuyIpLogInteface {

    @Autowired
    private BuyIpLogDao buyIpLogDao;

    @Async
    @Override
    public void BuyIpLogInsert(HttpServletRequest request, String userid) {

        try {
            String ip = IpUtils.getRemoteAddr(request);
            String url = "http://ip.taobao.com/service/getIpInfo.php?ip=" + ip;
            String value = HttpClientUtils.doGet(url, "UTF-8");
            JSONObject cjson = new JSONObject();
            cjson.put("ip", ip);
            cjson.put("userid", userid);
            if (value != null) {
                JSONObject json = JSONObject.parseObject(value);
                if (json.getInteger("code") == 0 && json.containsKey("data")) {
                    cjson.put("country", json.getJSONObject("data").getString("country"));
                    cjson.put("region", json.getJSONObject("data").getString("region"));
                    cjson.put("city", json.getJSONObject("data").getString("city"));
                    cjson.put("area", json.getJSONObject("data").getString("area"));
                    cjson.put("isp", json.getJSONObject("data").getString("isp"));
                    cjson.put("pid", new SnowFlake(1, 1).nextId());
                }
            }
            buyIpLogDao.BuyIpLogInsert(cjson);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public JSONObject BuyIpLogSelect(String userid) {
        JSONObject json = new JSONObject();
        try {
            Map<String, Object> map = buyIpLogDao.BuyIpLogSelect(userid);
            json.put("log", map);
            json.put("code", 100);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
        }
        return json;
    }
}
