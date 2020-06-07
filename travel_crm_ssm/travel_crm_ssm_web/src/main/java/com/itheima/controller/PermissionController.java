package com.itheima.controller;

import com.itheima.entity.Permission;
import com.itheima.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll()throws Exception{
        ModelAndView mv=new ModelAndView();
        List<Permission> list=permissionService.findAll();
        mv.addObject("permissionList",list);
        mv.setViewName("permission-list");
        return mv;
    }

    @RequestMapping("/save.do")
    public String save(Permission permission)throws Exception{
        permissionService.save(permission);
        return "redirect:/permission/findAll.do";
    }
}
