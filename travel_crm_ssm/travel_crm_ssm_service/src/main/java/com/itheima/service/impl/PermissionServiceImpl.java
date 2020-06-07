package com.itheima.service.impl;

import com.itheima.entity.Permission;
import com.itheima.map.PermissionMapper;
import com.itheima.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 权限业务层实现类
 */
@Service("permissionService")
@Transactional
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    //查找所有权限
    @Override
    public List<Permission> findAll() throws Exception {
        return permissionMapper.findAll();
    }

    //保存权限
    @Override
    public void save(Permission permission) throws Exception {
        permissionMapper.save(permission);
    }
}
