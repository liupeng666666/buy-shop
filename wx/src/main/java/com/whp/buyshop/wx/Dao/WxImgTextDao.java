package com.whp.buyshop.wx.Dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author : 张吉伟
 * @data : 2019/12/15 17:48
 * @descrpition :
 */
@Mapper
public interface WxImgTextDao {
    public List<Map<String, Object>> WxImgTextReplySelect(@Param("buyid") String buyid, @Param("replyid") String replyid, @Param("type") int type);

    public int WxImgTextInsert(Map<String, Object> map);

    public List<Map<String, Object>> WxImgTextSelectByPage(@Param("map") Map<String, Object> map, @Param("page") int page, @Param("num") int num);

    public int WxImgTextSelectByCount(@Param("map") Map<String, Object> map);

    public int WxImgTextUpdateState(@Param("map") Map<String, Object> map);

    public int WxImgTextUpdate(@Param("map") Map<String, Object> map);

    public List<Map<String, Object>> WxImgTextSelectById(@Param("map") Map<String, Object> map);

    public int wxImgTextUpdateById(@Param("map") Map<String, Object> map);

    public List<Map<String, Object>> WxImgTextSelectByReplyId(@Param("map") Map<String, Object> map);

    public int wxImgTextUpdateByReplyId(@Param("map") Map<String, Object> map);
}