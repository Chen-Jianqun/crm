package com.itheima.service;

import com.itheima.entity.Role;
import com.itheima.entity.Users;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UsersService extends UserDetailsService {

    public List<Users> findAll(Integer page,Integer size)throws Exception;

    public void save(Users user)throws Exception;

    public Users findUserById(String id)throws Exception;

    public List<Role> findOtherRoles(String id)throws Exception;

    public void addRoleToUser(String userId, String[] ids)throws Exception;
}
