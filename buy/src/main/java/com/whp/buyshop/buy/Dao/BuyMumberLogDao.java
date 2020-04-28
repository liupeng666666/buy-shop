package com.whp.buyshop.buy.Dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author : 张吉伟
 * @data : 2019/9/26 14:21
 * @descrpition :
 */
@Mapper
public interface BuyMumberLogDao {

    public List<Map<String, Object>> BuyMumberLogSelect(@Param("buyid") String buyid, @Param("mumberid") String mumberid, @Param("page") int page, @Param("num") int num);

    public int BuyMumberLogCount(@Param("buyid") String buyid, @Param("mumberid") String mumberid);
}
