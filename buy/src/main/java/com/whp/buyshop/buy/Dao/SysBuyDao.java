package com.whp.buyshop.buy.Dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author : 张吉伟
 * @data : 2019/9/6 10:33
 * @descrpition :
 */
@Mapper
public interface SysBuyDao {

    public Map<String, Object> getSysBuySelect(@Param("pid") String pid);

    public Map<String, Object> SysBuyLogin(@Param("username") String username, @Param("password") String password);

    public void SysBuyUpdate(@Param("buyid") String buyid, @Param("num") int num);
}
