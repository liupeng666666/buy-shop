package com.whp.buyshop.buy.Dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface BuyUserDao {
    public int BuyUserUpdate(Map<String, Object> map);

    public Map<String, Object> BuyUserCom(Map<String, Object> map);

    public int SubUserUpdateNumber(Map<String, Object> map);
}
