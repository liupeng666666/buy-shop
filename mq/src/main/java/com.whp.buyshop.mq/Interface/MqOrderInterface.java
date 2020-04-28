package com.whp.buyshop.mq.Interface;

import com.alibaba.fastjson.JSONObject;

/**
 * @author : 张吉伟
 * @data : 2019/8/30 14:57
 * @descrpition :
 */
public interface MqOrderInterface {
    public JSONObject MqOrderInsert(JSONObject json);
}
