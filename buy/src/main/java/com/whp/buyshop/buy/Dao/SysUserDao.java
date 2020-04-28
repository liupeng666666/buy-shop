package com.whp.buyshop.buy.Dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author : 张吉伟
 * @data : 2019/9/10 13:44
 * @descrpition :
 */
@Mapper
public interface SysUserDao {

    public List<Map<String, Object>> SysUserSelect(@Param("buyid") String buyid);

    public int SysUserCx(@Param("username") String username);

    public void SysUserInsert(Map<String, String> map);

    public void SysUserUpdate(Map<String, Object> map);
}
