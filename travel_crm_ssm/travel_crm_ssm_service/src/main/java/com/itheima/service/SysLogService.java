package com.itheima.service;

import com.itheima.entity.SysLog;

import java.util.List;

/**
 * 日志业务层接口
 */
public interface SysLogService {

    public void save(SysLog sysLog)throws Exception;

    public List<SysLog> findAll() throws Exception;
}
