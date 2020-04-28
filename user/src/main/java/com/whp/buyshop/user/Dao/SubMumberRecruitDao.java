package com.whp.buyshop.user.Dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author : 张吉伟
 * @data : 2019/9/11 19:19
 * @descrpition :
 */
@Mapper
public interface SubMumberRecruitDao {

    public List<Map<String, Object>> SubMumberRecruitSelect(@Param("buyid") String buyid);
}
