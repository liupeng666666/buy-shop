package com.whp.buyshop.buy.Dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author : 张吉伟
 * @data : 2019/9/6 17:44
 * @descrpition :
 */
@Mapper
public interface SysModuleDao {
    /**
     * @param roleid
     * @return
     */
    public List<Map<String, Object>> SysModuleSelect(@Param("roleid") String roleid);
}
