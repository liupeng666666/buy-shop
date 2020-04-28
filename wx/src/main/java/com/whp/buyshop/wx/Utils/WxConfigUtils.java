package com.whp.buyshop.wx.Utils;

import com.whp.buyshop.wx.Dao.WxSubBuyDao;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : 张吉伟
 * @data : 2019/11/27 9:58
 * @descrpition :
 */
@Component
public class WxConfigUtils {


    @Autowired
    private WxSubBuyDao wxSubBuyDao;

    private Map<String, WxMpService> wx_cofig = new HashMap<>();

    public WxMpService WxConfig(String buyid) {
        if (wx_cofig.containsKey(buyid)) {
            WxMpService configStorage = wx_cofig.get(buyid);
            return configStorage;
        } else {
            init(buyid);
            if (wx_cofig.containsKey(buyid)) {
                WxMpService configStorage = wx_cofig.get(buyid);
                return configStorage;
            } else {
                return null;
            }
        }
    }

    public void init(String buyid) {
        Map<String, Object> map = wxSubBuyDao.WxSubBuyPidDan(buyid);
        if (map != null && map.containsKey("wechat_appid") && map.containsKey("wechat_secret")) {
            WxMpService wxMpService = new WxMpServiceImpl();
            WxMpInMemoryConfigStorage config = new WxMpInMemoryConfigStorage();

            config.setAppId(map.get("wechat_appid").toString());// 设置微信公众号的appid
            config.setSecret(map.get("wechat_secret").toString());// 设置微信公众号的app corpSecret
            config.setToken("zxcvbnm123456789");// 设置微信公众号的token
            config.setAesKey("wWjnHnoJ24bTw2xkCpmHjpFM4T8Es3oJ6grMWD1a7HE");// 设置消息加解密密钥
            wxMpService.setWxMpConfigStorage(config);
            wx_cofig.put(buyid, wxMpService);
        }
    }
}
