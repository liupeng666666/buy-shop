package com.whp.buyshop.buy.Dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author : 张吉伟
 * @data : 2019/9/11 9:40
 * @descrpition :
 */
@Mapper
public interface BuyMumberDao {

    public List<Map<String, Object>> BuyMumberSelect(@Param("buyid") String buyid, @Param("name") String name);

    public void BuyMumberInsert(Map<String, Object> map);

    public int SysMumberOpenid(@Param("openid") String openid);

    public Map<String, Object> SysMumberCount(@Param("buyid") String buyid);

    public void SysMumberUserUpdate(Map<String, Object> map);

    public void SysMumberUpdate(Map<String, Object> map);

    public int BuyMumberSelectByBuyid(@Param("buyid") String buyid);

    public Map<String, Object> BuyMumberSelectByBuy(@Param("buyid") String buyid);

    public List<Map<String, Object>> BuyMumberSelectByTotal(@Param("buyid") String buyid);

}
