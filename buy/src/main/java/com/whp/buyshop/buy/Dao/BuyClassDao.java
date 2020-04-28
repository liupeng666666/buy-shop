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
public interface BuyClassDao {

    public List<Map<String, Object>> BuyClassSelect(@Param("buyid") String buyid);

    public List<Map<String, Object>> BuyClassSelectState(@Param("buyid") String buyid);

    public void BuyClassInsert(Map<String, Object> map);

    public void BuyClassUpdate(Map<String, Object> map);

    public void BuyClassUpdateState(Map<String, Object> map);

    public List<Map<String, Object>> BuyClassAdminSelect();

}
