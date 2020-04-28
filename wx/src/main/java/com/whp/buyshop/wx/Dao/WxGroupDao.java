package com.whp.buyshop.wx.Dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author : 张吉伟
 * @data : 2019/12/15 16:25
 * @descrpition :
 */
@Mapper
public interface WxGroupDao {

    public List<Map<String, Object>> WxGroupSelect(@Param("map") Map<String, Object> map, @Param("page") int page, @Param("num") int num);

    public int WxGroupCount(@Param("map") Map<String, Object> map);

    public void WxGroupInsert(Map<String, Object> map);

    public void WxGroupUpdate(Map<String, Object> map);

    public void WxGroupModifyState(Map<String, Object> map);

    public Map<String, Object> WxGroupDanSelect(@Param("pid") String pid);

    public void WxGroupUpdateState(Map<String, Object> map);

    public int WxGroupByTime(Map<String, Object> map);

    public void WxGroupUpdateType(Map<String, Object> map);
}
