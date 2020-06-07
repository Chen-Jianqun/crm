package com.itheima.service.impl;

import com.itheima.entity.Permission;
import com.itheima.entity.Role;
import com.itheima.map.RoleMapper;
import com.itheima.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 角色业务层接口实现类
 */
@Service("roleService")
@Transactional
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleMapper roleMapper;

    //查找所有角色
    @Override
    public List<Role> findAll() throws Exception{
        return roleMapper.findAll();
    }

    //保存角色
    @Override
    public void save(Role role) throws Exception {
        roleMapper.save(role);
    }

    @Override
    public Role findRoleById(String id) throws Exception {
        return roleMapper.findRoleById(id);
    }

    @Override
    public List<Permission> findOtherPermissions(String id) throws Exception {
        return roleMapper.findOtherPermission(id);
    }

    @Override
    public void addPermissionToRole(String roleId, String[] ids) {
        for (String id:ids) {
            roleMapper.addPermissionToRole(roleId,id);
        }
    }
}
