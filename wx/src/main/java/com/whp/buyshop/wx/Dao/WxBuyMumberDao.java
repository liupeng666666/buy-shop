package com.whp.buyshop.wx.Dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author : 张吉伟
 * @data : 2019/11/25 12:12
 * @descrpition :
 */
@Mapper
public interface WxBuyMumberDao {

    public void WxBuyMumberUpdate(@Param("openid") String openid, @Param("mumberid") String mumberid);
}
