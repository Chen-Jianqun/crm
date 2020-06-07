package com.itheima.service;

import com.itheima.entity.Permission;

import java.util.List;

/**
 * 权限业务层接口
 */
public interface PermissionService {

    //查找所有权限
    public List<Permission> findAll()throws Exception;

    //保存权限
    public void save(Permission permission)throws Exception;
}
