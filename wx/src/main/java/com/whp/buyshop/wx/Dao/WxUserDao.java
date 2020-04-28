package com.whp.buyshop.wx.Dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface WxUserDao {
    public List<Map<String, Object>> WxUserSelectByPage(@Param("map") Map<String, Object> map, @Param("page") int page, @Param("num") int num);

    public int WxUserSelectByCount(@Param("map") Map<String, Object> map);

    public List<Map<String, Object>> WxUserSelect(@Param("appid") String appid, @Param("name") String name);

    public Map<String, Object> WxUserSelectByOpenId(@Param("openid") String openid);
}
