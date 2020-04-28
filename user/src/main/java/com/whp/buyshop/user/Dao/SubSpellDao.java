package com.whp.buyshop.user.Dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface SubSpellDao {
    List<Map<String, Object>> SubSpellSelectByPage(@Param("map") Map<String, Object> map, @Param("start") int start, @Param("end") int end);

    int SubSpellSelectByCount(@Param("map") Map<String, Object> map);

    Map<String, Object> SubSpellSelectById(Map<String, Object> map);

    int SubSpellUpdate(Map<String, Object> map);
}
