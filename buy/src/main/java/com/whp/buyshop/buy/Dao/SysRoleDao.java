package com.whp.buyshop.buy.Dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author : 张吉伟
 * @data : 2019/9/9 15:45
 * @descrpition :
 */
@Mapper
public interface SysRoleDao {


    /**
     * @param buyid
     * @return
     */
    public List<Map<String, Object>> SysRoleSelect(@Param("buyid") String buyid);

    public void SysRoleInsert(Map<String, Object> map);

    public void SysRoleModuleInsert(Map<String, Object> map);

    public void SysRoleModuleDel(@Param("roleid") String roleid);

    public void SysRoleUpdate(Map<String, Object> map);
}
