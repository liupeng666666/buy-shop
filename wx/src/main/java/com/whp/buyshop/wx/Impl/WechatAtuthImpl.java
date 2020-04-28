package com.whp.buyshop.wx.Impl;

import com.alibaba.fastjson.JSONObject;
import com.whp.buyshop.utils.util.HttpClientUtils;
import com.whp.buyshop.wx.Dao.WxBuyMumberDao;
import com.whp.buyshop.wx.Dao.WxSubBuyDao;
import com.whp.buyshop.wx.Interface.WechatAuthInterface;
import org.json.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author : 张吉伟
 * @data : 2019/11/25 11:56
 * @descrpition :
 */
@Service
public class WechatAtuthImpl implements WechatAuthInterface {
    @Autowired
    private WxSubBuyDao wxSubBuyDao;
    @Autowired
    private WxBuyMumberDao wxBuyMumberDao;

    @Override
    public JSONObject WechatAuth(String appid, String mumberid, String code) {
        JSONObject json = new JSONObject();
        try {
            Map<String, Object> map = wxSubBuyDao.WxSubBuyDan(appid);
            if (map != null && map.containsKey("wechat_secret")) {
                String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + appid + "&secret=" + map.get("wechat_secret") + "&code=" + code + "&grant_type=authorization_code";
                String value = HttpClientUtils.doGet(url, "utf-8");
                if (value != null && value != "") {
                    JSONObject fjson = JSONObject.parseObject(value);
                    if (fjson.containsKey("openid")) {
                        wxBuyMumberDao.WxBuyMumberUpdate(fjson.getString("openid"), mumberid);
                        json.put("code", 100);
                    } else {
                        json.put("code", fjson.get("errcode"));
                    }
                } else {
                    json.put("code", 101);
                }
            } else {
                json.put("code", 102);
            }
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
        }
        return json;
    }
}
