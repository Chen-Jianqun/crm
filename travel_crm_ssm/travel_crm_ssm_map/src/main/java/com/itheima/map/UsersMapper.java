package com.itheima.map;

import com.itheima.entity.Role;
import com.itheima.entity.Users;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UsersMapper {

    @Select("select * from users where username=#{username}")
    @Results(value = {
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "username",property = "username"),
            @Result(column = "email",property = "email"),
            @Result(column = "password",property = "password"),
            @Result(column = "phoneNum",property = "phoneNum"),
            @Result(column = "status",property = "status"),
            @Result(column = "id",property = "roles",javaType = java.util.List.class,many = @Many(select = "com.itheima.map.RoleMapper.findRoleByUserId")),
    })
    public Users findUserByUsername(String username)throws Exception;

    @Select("select * from users")
    public List<Users> findAll();

    @Insert("insert into users(email, username, password, phoneNum, status) values(#{email},#{username},#{password},#{phoneNum},#{status})")
    public void save(Users user);

    @Select("select * from users where id=#{id}")
    @Results(value = {
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "username",property = "username"),
            @Result(column = "email",property = "email"),
            @Result(column = "password",property = "password"),
            @Result(column = "phoneNum",property = "phoneNum"),
            @Result(column = "status",property = "status"),
            @Result(column = "id",property = "roles",javaType = java.util.List.class,many = @Many(select = "com.itheima.map.RoleMapper.findRoleByUserId")),
    })
    public Users findUserById(String id)throws Exception;

    @Select("select * from role where id not in(select roleId from users_role where userId=#{id})")
    public List<Role> findOtherRoles(String id)throws Exception;

    @Insert("insert into users_role(userId,roleId) values(#{userId},#{id})")
    public void addRoleToUser(@Param("userId") String userId, @Param("id") String id)throws Exception;
}
