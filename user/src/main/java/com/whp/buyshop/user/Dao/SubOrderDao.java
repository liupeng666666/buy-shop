package com.whp.buyshop.user.Dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface SubOrderDao {
    public Map<String, Object> SubOrderSelectById(@Param("orderid") String orderid);

    public int SubOrderListUpdate(Map<String, Object> map);

    public int SubOrderUpdate(Map<String, Object> map);

    public int SubOrderLogInsert(Map<String, Object> map);

    public List<Map<String, Object>> SubOrderListSelectById(@Param("map") Map<String, Object> map);

}
