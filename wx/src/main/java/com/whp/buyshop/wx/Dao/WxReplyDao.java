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
public interface WxReplyDao {

    public List<Map<String, Object>> WxReplySelect(@Param("map") Map<String, Object> map, @Param("page") int page, @Param("num") int num);

    public int WxReplyCount(@Param("map") Map<String, Object> map);

    public void WxReplyInsert(Map<String, Object> map);

    public void WxReplyUpdate(Map<String, Object> map);

    public void WxReplyStateUpdate(Map<String, Object> map);

    public Map<String, Object> WxReplyDanSelect(@Param("pid") String pid);

    public void WxReplyUpdateState(Map<String, Object> map);
}
