package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.itheima.entity.Orders;
import com.itheima.map.OrdersMapper;
import com.itheima.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 订单业务层实现类
 */
@Service("ordersService")
@Transactional()
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersMapper ordersMapper;

    //查询所有订单
    @Override
    public List<Orders> findAll(Integer page,Integer size)throws Exception {
        PageHelper.startPage(page,size);
        return ordersMapper.findAll();
    }

    //通过id查询订单详情
    @Override
    public Orders findOrderById(String id) throws Exception {
        return ordersMapper.findOrderById(id);
    }
}
