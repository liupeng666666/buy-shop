package com.whp.buyshop.buy.Dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author : 张吉伟
 * @data : 2019/10/16 15:15
 * @descrpition :
 */
@Mapper
public interface BuyRechargeDao {

    public List<Map<String, Object>> BuyRechargeSelect(@Param("buyid") String buyid);

    public void BuyRechargeInsert(Map<String, Object> map);

    public void BuyRechargeUpdate(Map<String, Object> map);

    public void BuyRechargeUpdateState(Map<String, Object> map);

    public List<Map<String, Object>> BuyRechargePayLogSelect(@Param("map") Map<String, Object> map, @Param("page") int page, @Param("num") int num);

    public int BuyRechargePayLogCount(@Param("map") Map<String, Object> map);
}
