package com.whp.buyshop.buy.Impl;

import com.alibaba.fastjson.JSONObject;
import com.whp.buyshop.buy.Dao.BuyGoodsDao;
import com.whp.buyshop.buy.Interface.BuyGoodsInterface;
import com.whp.buyshop.utils.Enum.BuyEnum;
import com.whp.buyshop.utils.util.SnowFlake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author : 张吉伟
 * @data : 2019/10/10 10:13
 * @descrpition :
 */
@Service
public class BuyGoodsImpl implements BuyGoodsInterface {

    @Autowired
    private BuyGoodsDao buyGoodsDao;

    @Override
    public JSONObject BuyGoodsSelect(Map<String, Object> map) {
        JSONObject json = new JSONObject();
        try {
            List<Map<String, Object>> list = buyGoodsDao.BuyGoodsSelect(map, Integer.parseInt(map.get("page").toString()), Integer.parseInt(map.get("num").toString()));
            int count = buyGoodsDao.BuyGoodsCount(map);
            json.put("code", 100);
            json.put("goods", list);
            json.put("count", count);
            json.put("enum", BuyEnum.CODE_ENUM_MAP);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
        }
        return json;
    }

    @Override
    public JSONObject BuyGoodsDan(String buyid, String name) {
        JSONObject json = new JSONObject();
        try {
            List<Map<String, Object>> list = buyGoodsDao.BuyGoodsDan(buyid, name);
            json.put("code", 100);
            json.put("goods", list);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
        }
        return json;
    }

    @Override
    public JSONObject BuyGoodsInsert(Map<String, Object> map) {
        JSONObject json = new JSONObject();
        try {
            SnowFlake snowFlake = new SnowFlake(1, 1);
            map.put("pid", snowFlake.nextId());
            buyGoodsDao.BuyGoodsInsert(map);
            json.put("code", 100);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
        }
        return json;
    }

    @Override
    public JSONObject BuyGoodsUpdate(Map<String, Object> map) {
        JSONObject json = new JSONObject();
        try {
            Map<String, Object> m = buyGoodsDao.BuyGoodsSelectById(map);
            if (!m.get("classid").toString().equals(map.get("classid").toString())) {
                map.put("classId", m.get("classid").toString());
            }
            buyGoodsDao.BuyGoodsUpdate(map);
            json.put("code", 100);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
        }
        return json;
    }

    @Override
    public JSONObject BuyGoodsUpdateState(Map<String, Object> map) {
        JSONObject json = new JSONObject();
        try {
            Map<String, Object> m = buyGoodsDao.BuyGoodsSelectById(map);
            map.put("classid", m.get("classid").toString());
            map.put("type", m.get("type").toString());
            map.put("opentime", m.get("opentime").toString());
            buyGoodsDao.BuyGoodsUpdateState(map);
            json.put("code", 100);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
        }
        return json;
    }
}
