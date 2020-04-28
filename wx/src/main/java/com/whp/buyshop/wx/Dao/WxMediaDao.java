package com.whp.buyshop.wx.Dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface WxMediaDao {
    public int WxMediaInsert(Map<String, Object> map);

    public List<Map<String, Object>> WxMediaSelectByPage(@Param("map") Map<String, Object> map, @Param("page") int page, @Param("num") int num);

    public int WxMediaSelectByCount(@Param("map") Map<String, Object> map);

    public int WxMediaUpdateState(@Param("map") Map<String, Object> map);

    public List<Map<String, Object>> WxMediaSelectById(@Param("buyid") String buyid);

    public List<Map<String, Object>> WxMediaSelectByStyle(@Param("map") Map<String, Object> map);


}
