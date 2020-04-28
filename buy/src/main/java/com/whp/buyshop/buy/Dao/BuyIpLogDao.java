package com.whp.buyshop.buy.Dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @author : 张吉伟
 * @data : 2019/11/1 10:02
 * @descrpition :
 */
@Mapper
public interface BuyIpLogDao {

    public void BuyIpLogInsert(Map<String, Object> map);

    public Map<String, Object> BuyIpLogSelect(@Param("userid") String userid);
}
