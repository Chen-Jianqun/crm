package com.itheima.map;

import com.itheima.entity.SysLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SysLogMapper {

    @Insert("insert into syslog(visitTime,username,ip,url,executionTime,method) values(#{visitTime},#{username},#{ip},#{url},#{executionTime},#{method})")
    public void save(SysLog sysLog)throws Exception;

    @Select("select * from syslog")
    public List<SysLog> findAll()throws Exception;
}
