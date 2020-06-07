package com.itheima.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.entity.Role;
import com.itheima.entity.Users;
import com.itheima.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1")Integer page,@RequestParam(name = "size",required = true,defaultValue = "4")Integer size)throws Exception{
        ModelAndView mv=new ModelAndView();
        List<Users> list=usersService.findAll(page,size);
        PageInfo pageInfo=new PageInfo(list);
        mv.addObject("userList",pageInfo);
        mv.setViewName("user-list");
        return mv;
    }

    @RequestMapping("/findUserById.do")
    public ModelAndView findUserById(@RequestParam(name = "id",required = true) String id)throws Exception{
        ModelAndView mv=new ModelAndView();
        Users user=usersService.findUserById(id);
        mv.addObject("user",user);
        mv.setViewName("user-show");
        return mv;
    }

    @RequestMapping("/save.do")
    public String save(Users user)throws Exception{
        usersService.save(user);
        return "redirect:findAll.do";
    }

    @RequestMapping("/findUserByIdAndAllRole.do")
    public ModelAndView findUserByIdAndAllRole(String id)throws Exception{
        ModelAndView mv=new ModelAndView();
        Users user=usersService.findUserById(id);
        List<Role> list=usersService.findOtherRoles(id);
        mv.addObject("user",user);
        mv.addObject("roleList",list);
        mv.setViewName("user-role-add");
        return mv;
    }

    @RequestMapping("/addRoleToUser.do")
    public String addRoleToUser(@RequestParam(name = "userId",required = true) String userId,@RequestParam(name = "ids",required = true) String[] ids)throws Exception{
        System.out.println(userId);
        for (String id:ids) {
            System.out.println(id);
        }
        usersService.addRoleToUser(userId,ids);
        return "redirect:/users/findAll.do";
    }
}
