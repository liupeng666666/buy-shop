package com.whp.buyshop.buy.Dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author : 张吉伟
 * @data : 2019/10/16 19:02
 * @descrpition :
 */
@Mapper
public interface BuyIntegralDao {

    public void BuyIntegralInsert(Map<String, Object> map);

    public void BuyIntegralUpdate(Map<String, Object> map);

    public void BuyIntegralUpdateState(Map<String, Object> map);

    public List<Map<String, Object>> BuyIntegralSelect(@Param("map") Map<String, Object> map, @Param("page") int page, @Param("num") int num);

    public int BuyIntegralCount(@Param("map") Map<String, Object> map);

    public List<Map<String, Object>> BuyIntegralLogSelect(@Param("map") Map<String, Object> map, @Param("page") int page, @Param("num") int num);

    public int BuyIntegralLogCount(@Param("map") Map<String, Object> map);
}
