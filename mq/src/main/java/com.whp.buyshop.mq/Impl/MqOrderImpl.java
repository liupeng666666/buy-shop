package com.whp.buyshop.mq.Impl;

import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.whp.buyshop.mq.Interface.MqOrderInterface;
import com.whp.buyshop.mq.utils.ConnectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author : 张吉伟
 * @data : 2019/8/30 14:58
 * @descrpition :
 */
@Service
public class MqOrderImpl implements MqOrderInterface {

    @Override
    public JSONObject MqOrderInsert(JSONObject json) {
        System.out.println("json:" + json);
        JSONObject fjson = new JSONObject();
        Connection con = null;
        Channel channel = null;
        try {
            // 获取连接
            con = ConnectionUtils.getConnection();
            // 从连接中创建通道
            channel = con.createChannel();
            // 声明一个队列
            channel.queueDeclare(json.getString("queue"), true, false, false, null);
            channel.exchangeDeclare("buy", BuiltinExchangeType.TOPIC,
                    true, false, false, null);
            channel.queueBind(json.getString("queue"), "buy", json.getString("queue"));
            // 发送消息
            channel.basicPublish("buy", json.getString("queue"), null, json.getString("message").getBytes());
            System.out.println("send success");
            fjson.put("code", 100);
        } catch (IOException e) {
            e.printStackTrace();
            fjson.put("code", 103);
        } catch (TimeoutException e) {
            e.printStackTrace();
            fjson.put("code", 103);
        } finally {
            // 关闭连接
            System.out.println("关闭连接");
            ConnectionUtils.close(channel, con);
        }
        return fjson;
    }
}
