package com.whp.buyshop.buy.Impl;

import com.alibaba.fastjson.JSONObject;
import com.whp.buyshop.buy.Dao.SubTaskDao;
import com.whp.buyshop.buy.Interface.SubTaskInterface;
import com.whp.buyshop.utils.util.RedisUtils;
import com.whp.buyshop.utils.util.SnowFlake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SubTaskImpl implements SubTaskInterface {
    @Autowired
    private SubTaskDao subTaskDao;

    @Override
    public JSONObject SubTaskInsert(Map<String, Object> map) {
        JSONObject json = new JSONObject();
        try {
            subTaskDao.SubTaskInsert(map);
            json.put("code", 100);

        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
        }
        return json;
    }

}
