package com.whp.buyshop.order.Dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author : 张吉伟
 * @data : 2019/10/15 15:21
 * @descrpition :
 */
@Mapper
public interface OrderSubOrderDao {

    public List<Map<String, Object>> OrderSubOrderSelect(@Param("map") Map<String, Object> map, @Param("page") int page, @Param("num") int num);

    public int OrderSubOrderCount(@Param("map") Map<String, Object> map);

    public List<Map<String, Object>> OrderSubOrderLogSelect(@Param("orderid") String orderid);

    public List<Map<String, Object>> OrderSubOrderLostSelect(@Param("orderid") String orderid, @Param("buyid") String buyid);

    public int OrderSubOrderSelectByBuyid(@Param("buyid") String buyid);

    public List<Map<String, Object>> OrderSubOrderGoodsSelect(@Param("buyid") String buyid);

    public List<Map<String, Object>> OrderSubOrderGoodsDanSelect(@Param("buyid") String buyid, @Param("goodsid") String goodsid, @Param("state") int state, @Param("page") int page, @Param("num") int num);

    public int OrderSubOrderGoodsDanCount(@Param("buyid") String buyid, @Param("goodsid") String goodsid);

    public Map<String, Object> OrderSubOrderListDanCx(@Param("pid") String pid, @Param("buyid") String buyid);

    public void SubOrderListUpdate(@Param("pid") String pid);

    public void SubOrderUpdate(@Param("orderid") String orderid);

    public void SubOrderLogInsert(Map<String, Object> map);

    public List<Map<String, Object>> OrderSubOrderByUser(@Param("map") Map<String, Object> map);

    public List<Map<String, Object>> OrderSubOrderByOrderId(@Param("map") Map<String, Object> map);

}
