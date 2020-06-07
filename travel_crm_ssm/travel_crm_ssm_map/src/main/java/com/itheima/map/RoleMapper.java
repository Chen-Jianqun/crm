package com.itheima.map;

import com.itheima.entity.Permission;
import com.itheima.entity.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 角色持久层类
 */
public interface RoleMapper {

    @Select("select * from role where id in (select roleId from users_role where userId=#{userId})")
    @Results(value = {
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "roleName",property = "roleName"),
            @Result(column = "roleDesc",property = "roleDesc"),
            @Result(column = "id",property = "permissions",javaType = java.util.List.class,many = @Many(select = "com.itheima.map.PermissionMapper.findPermissionByRoleId")),
    })
    public List<Role> findRoleByUserId(String userId)throws Exception;

    @Select("select * from role")
    public List<Role> findAll()throws Exception;

    @Insert("insert into role(roleName,roleDesc) values(#{roleName},#{roleDesc})")
    public void save(Role role)throws Exception;

    @Select("select * from role where id=#{id}")
    public Role findRoleById(String id);

    @Select("select * from permission where id not in(select permissionId from role_permission where roleId=#{id} )")
    public List<Permission> findOtherPermission(String id);

    @Insert("insert into role_permission(permissionId,roleId) values(#{id},#{roleId}) ")
    public void addPermissionToRole(@Param("roleId")String roleId,@Param("id") String id);
}
