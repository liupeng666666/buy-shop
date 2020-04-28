package com.whp.buyshop.wx.Dao;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface WxMenuDao {
    public int WxMenuInsert(Map<String, Object> map);

    public List<Map<String, Object>> WxMenuSelectByPage(@Param("map") Map<String, Object> map, @Param("page") int page, @Param("num") int num);

    public int WxMenuSelectByCount(@Param("map") Map<String, Object> map);

    public int WxMenuUpdate(@Param("map") Map<String, Object> map);

    public int WxMenuUpdateState(@Param("map") Map<String, Object> map);

    public List<Map<String, Object>> WxMenuSelectmenu(@Param("map") Map<String, Object> map);

    public List<Map<String, Object>> getMenuById(String pid);

    public List<Map<String, Object>> getMenuByPid(String parentid);

    public List<Map<String, Object>> WxMenuSelectById(@Param("map") Map<String, Object> map);


    public Map<String, Object> getBuyById(@Param("map") Map<String, Object> map);
}
