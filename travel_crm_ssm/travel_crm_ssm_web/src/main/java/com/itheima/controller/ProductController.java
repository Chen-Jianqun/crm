package com.itheima.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.entity.Product;
import com.itheima.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductService productService;

    //查询所有商品信息
    @RequestMapping("findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1")Integer page,@RequestParam(name = "size",required = true,defaultValue = "4")Integer size) throws Exception{
        ModelAndView mv=new ModelAndView();
        List<Product> list=productService.findAll(page,size);
        PageInfo pageInfo=new PageInfo(list);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("product-list");
        return mv;
    }

    //保存商品信息
    @RequestMapping("save.do")
    public String save(Product product)throws Exception{
        productService.save(product);
        return "redirect:findAll.do";
    }
}
