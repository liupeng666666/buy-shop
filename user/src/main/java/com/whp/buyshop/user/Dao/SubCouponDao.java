package com.whp.buyshop.user.Dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

@Mapper
public interface SubCouponDao {
    public int SubCouponInsert(Map<String, Object> map);

    public int SubCouponSelectById(Map<String, Object> map);

}
