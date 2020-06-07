package com.itheima.service;

import com.itheima.entity.Product;

import java.util.List;

/**
 * Product业务层接口
 */
public interface ProductService {

    //查找所有商品
    public List<Product> findAll(Integer page,Integer size) throws Exception;

    //查找单个商品
    public Product findProductById(String id) throws Exception;

    //保存商品
    public void  save(Product product) throws Exception;
}
