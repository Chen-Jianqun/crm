package com.itheima.entity;

import java.util.List;

/**
 * 角色实体类
 * 角色与用户是多对多关系
 * 角色与权限是多对多关系
 */
public class Role {
    private String id;
    private String roleName;
    private String roleDesc;
    private List<Permission> permissions;
    private List<Users> users;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public List<Users> getUsers() {
        return users;
    }

    public void setUsers(List<Users> users) {
        this.users = users;
    }

}
