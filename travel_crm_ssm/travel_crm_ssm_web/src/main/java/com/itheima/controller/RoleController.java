package com.itheima.controller;

import com.itheima.entity.Permission;
import com.itheima.entity.Role;
import com.itheima.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll()throws Exception{
        ModelAndView mv = new ModelAndView();
        List<Role> list=roleService.findAll();
        mv.addObject("roleList",list);
        mv.setViewName("role-list");
        return mv;
    }

    @RequestMapping("/save.do")
    public String save(Role role)throws Exception{
        roleService.save(role);
        return "redirect:/role/findAll.do";
    }

    @RequestMapping("/findRoleByIdAndAllPermission")
    public ModelAndView findRoleByIdAndAllPermission(String id) throws Exception{
        ModelAndView mv = new ModelAndView();
        Role role=roleService.findRoleById(id);
        List<Permission> list=roleService.findOtherPermissions(id);
        mv.addObject("role",role);
        mv.addObject("permissionList",list);
        mv.setViewName("role-permission-add");
        return mv;
    }

    @RequestMapping("/addPermissionToRole.do")
    public String addPermissionToRole(@RequestParam(name="roleId",required = true) String roleId,@RequestParam(name = "ids",required = true) String[] ids){
        roleService.addPermissionToRole(roleId,ids);
        return "redirect:/role/findAll.do";
    }
}
