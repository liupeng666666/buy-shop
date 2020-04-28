package com.whp.buyshop.buy.Dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author : 张吉伟
 * @data : 2019/10/10 9:30
 * @descrpition :
 */
@Mapper
public interface BuyGoodsDao {

    public List<Map<String, Object>> BuyGoodsSelect(@Param("map") Map<String, Object> map, @Param("page") int page, @Param("num") int num);

    public int BuyGoodsCount(Map<String, Object> map);

    public List<Map<String, Object>> BuyGoodsDan(@Param("buyid") String buyid, @Param("name") String name);

    public void BuyGoodsInsert(Map<String, Object> map);

    public void BuyGoodsUpdate(Map<String, Object> map);

    public void BuyGoodsUpdateState(Map<String, Object> map);

    public Map<String, Object> BuyGoodsSelectById(Map<String, Object> map);
}
