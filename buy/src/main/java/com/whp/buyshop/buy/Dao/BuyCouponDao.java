package com.whp.buyshop.buy.Dao;

import ch.qos.logback.core.status.OnPrintStreamStatusListenerBase;
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
public interface BuyCouponDao {

    public void BuyCouponInsert(Map<String, Object> map);

    public void BuyCouponUpdate(Map<String, Object> map);

    public void BuyCouponUpdateState(Map<String, Object> map);

    public List<Map<String, Object>> BuyCouponSelect(@Param("map") Map<String, Object> map, @Param("page") int page, @Param("num") int num);

    public int BuyCouponCount(@Param("map") Map<String, Object> map);

    public List<Map<String, Object>> BuySubCouponLogSelect(@Param("map") Map<String, Object> map, @Param("page") int page, @Param("num") int num);

    public int BuySubCouponLogCount(@Param("map") Map<String, Object> map);

    public int SubCouponSelectByCouponId(Map<String, Object> map);

    public int SubCouponUpdateState(@Param("map") Map<String, Object> map);
}
