package com.whp.buyshop.user.Dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


@Mapper
public interface BuyCouponbDao {

    public Map<String, Object> BuyCouponSelectById(@Param("map") Map<String, Object> map);

    public int BuyCouponUpdateNumber(@Param("map") Map<String, Object> map);

}
