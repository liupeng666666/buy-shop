package com.whp.buyshop.user.Dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface SubPayLogDao {
    public int SubPayLogInsert(Map<String, Object> map);

    public List<Map<String, Object>> SubPayLogSelectByPage(@Param("map") Map<String, Object> map, @Param("start") int start, @Param("end") int end);

    public int SubPayLogSelectByCount(@Param("map") Map<String, Object> map);
}
