package com.itheima.service.impl;

import com.itheima.entity.SysLog;
import com.itheima.map.SysLogMapper;
import com.itheima.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 日志业务层实现类
 */
@Service
@Transactional
public class SysLogServiceImpl implements SysLogService {

    @Autowired
    private SysLogMapper sysLogMapper;

    @Override
    public void save(SysLog sysLog)throws Exception {
        sysLogMapper.save(sysLog);
    }

    @Override
    public List<SysLog> findAll() throws Exception{
        return sysLogMapper.findAll();
    }
}
