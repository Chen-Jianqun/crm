package com.itheima.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.entity.Orders;
import com.itheima.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 订单控制器类
 */
@Controller
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    //查找所有订单
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1")Integer page, @RequestParam(name = "size",required = true,defaultValue = "4")Integer size)throws Exception{
        ModelAndView mv=new ModelAndView();
        List<Orders> ordersList=ordersService.findAll(page,size);
        PageInfo pageInfo=new PageInfo(ordersList);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("orders-list");
        return mv;
    }

    //查询订单详情
    @RequestMapping("/findOrderById.do")
    public ModelAndView findOrderById(@RequestParam(name = "id",required = true)String id)throws Exception{
        ModelAndView mv=new ModelAndView();
        Orders orders=ordersService.findOrderById(id);
        mv.addObject("orders",orders);
        mv.setViewName("orders-show");
        return mv;
    }
}
