package com.whp.buyshop.buy.Dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author : 张吉伟
 * @data : 2019/9/26 17:18
 * @descrpition :
 */
@Mapper
public interface BuyMumberWithdrawDao {

    public List<Map<String, Object>> BuyMumberWithdrawSelect(@Param("buyid") String buyid, @Param("mumberid") String mumberid, @Param("state") String state, @Param("page") int page, @Param("num") int num);

    public int BuyMumberWithdrawCount(@Param("buyid") String buyid, @Param("mumberid") String mumberid, @Param("state") String state);

    public void BuyMumberWithdrawInsert(Map<String, Object> map);

    public void BuyMumberWithdrawUpdate(Map<String, Object> map);
}
