package com.itheima.service;

import com.itheima.entity.Permission;
import com.itheima.entity.Role;

import java.util.List;

/**
 * 角色业务层接口
 */
public interface RoleService {

    //查询所有角色
    public List<Role> findAll()throws Exception;

    //保存角色
    public void save(Role role)throws Exception;

    //根据id查询角色
    public Role findRoleById(String id)throws Exception;

    //查询该Role中不包含的权限
    public List<Permission> findOtherPermissions(String id)throws Exception;

    //把选中的权限加入该角色的权限中
    public void addPermissionToRole(String roleId, String[] ids);
}
