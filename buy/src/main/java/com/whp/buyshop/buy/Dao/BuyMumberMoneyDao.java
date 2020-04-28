package com.whp.buyshop.buy.Dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author : 张吉伟
 * @data : 2019/9/12 11:55
 * @descrpition :
 */
@Mapper
public interface BuyMumberMoneyDao {

    public List<Map<String, Object>> BuyMumberMoneySelect(@Param("buyid") String buyid);

    public void BuyMumberMoneyInsert(Map<String, Object> map);

    public void BuyMumberMoneyUpdate(Map<String, Object> map);

    public void BuyMumberMoneyUpdateFail(Map<String, Object> map);

    Map<String, Object> BuyMumberMoneySelectByMumberId(String mumberid);
}
