package com.whp.buyshop.user.Impl;

import com.alibaba.fastjson.JSONObject;
import com.whp.buyshop.user.Dao.SubMumberRecruitDao;
import com.whp.buyshop.user.Interface.SubMumberRecruitInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author : 张吉伟
 * @data : 2019/9/11 19:21
 * @descrpition :
 */
@Service
public class SubMumberRecruitImpl implements SubMumberRecruitInterface {
    @Autowired
    private SubMumberRecruitDao subMumberRecruitDao;

    @Override
    public JSONObject SubMumberRecruitSelect(String buyid) {
        JSONObject json = new JSONObject();
        try {
            List<Map<String, Object>> list = subMumberRecruitDao.SubMumberRecruitSelect(buyid);
            json.put("recruit", list);
            json.put("code", 100);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
        }
        return json;
    }
}
