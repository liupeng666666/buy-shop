package com.whp.buyshop.wx.Dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface WxNewsDao {
    public List<Map<String, Object>> WxNewsSelectByPage(@Param("map") Map<String, Object> map, @Param("page") int page, @Param("num") int num);

    public int WxNewsSelectByCount(@Param("map") Map<String, Object> map);

}
