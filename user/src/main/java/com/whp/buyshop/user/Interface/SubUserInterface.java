package com.whp.buyshop.user.Interface;

import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * @author : 张吉伟
 * @data : 2019/9/11 11:36
 * @descrpition :
 */
public interface SubUserInterface {

    public JSONObject SubUserSelectXx(String name, String buyid);

    public JSONObject SubUserSelectByBuyid(String buyid);

    public JSONObject SubUserSelectBySearch(Map<String, Object> map);

    public JSONObject SubUserSelect(Map<String, Object> map, int page, int num);
}
