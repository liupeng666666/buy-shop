package com.whp.buyshop.wx.Dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author : 张吉伟
 * @data : 2019/11/25 11:40
 * @descrpition :
 */
@Mapper
public interface WxSubBuyDao {

    public List<Map<String, Object>> WxSubBuySelect();

    public Map<String, Object> WxSubBuyDan(@Param("wechat_appid") String wechat_appid);

    public Map<String, Object> WxSubBuyPidDan(@Param("buyid") String buyid);
}
