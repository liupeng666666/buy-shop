package com.whp.buyshop.user.Dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author : 张吉伟
 * @data : 2019/9/11 11:35
 * @descrpition :
 */
@Mapper
public interface SubUserDao {

    public List<Map<String, Object>> SubUserSelectXx(@Param("name") String name, @Param("buyid") String buyid);

    public int SubUserSelectByBuyid(@Param("buyid") String buyid);

    public List<Map<String, Object>> SubUserSelectBySearch(@Param("map") Map<String, Object> map);

    public int SubUserUpdateNumber(Map<String, Object> map);

    public int SubUserUpdateSurplus(Map<String, Object> map);

    public int SubUserSelectByCount(@Param("map") Map<String, Object> map);

    public List<Map<String, Object>> SubUserSelectByPage(@Param("map") Map<String, Object> map, @Param("page") int page, @Param("num") int num);

}
