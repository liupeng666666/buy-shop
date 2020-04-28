package com.whp.buyshop.buy.Dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author : 张吉伟
 * @data : 2019/10/10 12:48
 * @descrpition :
 */
@Mapper
public interface BuyAdDao {

    public List<Map<String, Object>> BuyAdSelect(@Param("buyid") String buyid, @Param("state") String state, @Param("page") int page, @Param("num") int num);

    public int BuyAdCount(@Param("buyid") String buyid, @Param("state") String state);

    public void BuyAdInsert(Map<String, Object> map);

    public void BuyAdUpdate(Map<String, Object> map);

    public void BuyAdUpdateState(Map<String, Object> map);
}
