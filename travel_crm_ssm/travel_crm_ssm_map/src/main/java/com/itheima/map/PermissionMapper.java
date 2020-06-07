package com.itheima.map;

import com.itheima.entity.Permission;
import com.itheima.entity.Role;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 权限持久层接口
 */
public interface PermissionMapper {

    //根据角色ID查找权限
    @Select("select * from permission where id in(select permissionId from role_permission where roleId=#{id})")
    public List<Permission> findPermissionByRoleId(String id)throws Exception;

    //查找所有权限
    @Select("select * from permission")
    public List<Permission> findAll()throws Exception;

    //保存权限
    @Insert("insert into permission(permissionName,url) values(#{permissionName},#{url}) ")
    public void save(Permission permission)throws Exception;
}
