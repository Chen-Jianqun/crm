package com.itheima.service;

import com.itheima.entity.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 订单业务层接口
 */
public interface OrdersService {

    public List<Orders> findAll(Integer page,Integer size)throws Exception;

    public Orders findOrderById(String id)throws Exception;
}
