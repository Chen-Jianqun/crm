package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.itheima.entity.Role;
import com.itheima.entity.Users;
import com.itheima.map.UsersMapper;
import com.itheima.service.UsersService;
import com.itheima.util.BCryptPasswordEncoderUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("usersService")
@Transactional
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersMapper usersMapper;
//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user=null;
        try {
            user=usersMapper.findUserByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        User user1=new User(user.getUsername(),user.getPassword(),user.getStatus()==0?false:true,true,true,true,getAuthority(user.getRoles()));
        return user1;
    }

    //获取角色集合
    public List<SimpleGrantedAuthority> getAuthority(List<Role> roles){
        List<SimpleGrantedAuthority> list=new ArrayList<>();
        for (Role role:roles){
            list.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        }
        return list;
    }

    @Override
    public List<Users> findAll(Integer page,Integer size) throws Exception {
        PageHelper.startPage(page,size);
        return usersMapper.findAll();
    }

    @Override
    public void save(Users user) throws Exception {
        //使用Spring-security提供的加密器对密码进行加密
        //user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setPassword(BCryptPasswordEncoderUtils.encodPassword(user.getPassword()));
        usersMapper.save(user);
    }

    @Override
    public Users findUserById(String id) throws Exception {
        return usersMapper.findUserById(id);
    }

    @Override
    public List<Role> findOtherRoles(String id) throws Exception {
        return usersMapper.findOtherRoles(id);
    }

    @Override
    public void addRoleToUser(String userId, String[] ids) throws Exception {
        for(String id:ids){
            usersMapper.addRoleToUser(userId,id);
        }

    }
}
