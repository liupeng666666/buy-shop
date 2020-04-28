package com.whp.buyshop.buy.Dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


@Mapper
public interface SubTaskDao {

    public void SubTaskInsert(Map<String, Object> map);

}
