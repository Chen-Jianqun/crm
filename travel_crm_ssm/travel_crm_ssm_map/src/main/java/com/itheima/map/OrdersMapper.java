package com.itheima.map;

import com.itheima.entity.Member;
import com.itheima.entity.Orders;
import com.itheima.entity.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 订单持久层接口
 */
public interface OrdersMapper {

    @Select("select * from orders")
    @Results(value = {
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "orderNum",property = "orderNum"),
            @Result(column = "orderTime",property = "orderTime"),
            @Result(column = "orderStatus",property = "orderStatus"),
            @Result(column = "peopleCount",property = "peopleCount"),
            @Result(column = "productId",property = "product",javaType = Product.class,one = @One(select = "com.itheima.map.ProductMapper.findProductById")),
            @Result(column = "payType",property = "payType"),
            @Result(column = "orderDesc",property = "orderDesc")
    })
    public List<Orders> findAll();

    @Select("select * from orders where id=#{id}")
    @Results(value = {
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "orderNum",property = "orderNum"),
            @Result(column = "orderTime",property = "orderTime"),
            @Result(column = "orderStatus",property = "orderStatus"),
            @Result(column = "peopleCount",property = "peopleCount"),
            @Result(column = "productId",property = "product",javaType = Product.class,one = @One(select = "com.itheima.map.ProductMapper.findProductById")),
            @Result(column = "memberId",property = "member",javaType = Member.class,one = @One(select="com.itheima.map.MemberMapper.findMemberById")),
            @Result(column = "id",property = "travellers",many = @Many(select = "com.itheima.map.TravellerMapper.findTravellerById")),
            @Result(column = "payType",property = "payType"),
            @Result(column = "orderDesc",property = "orderDesc")
    })
    public Orders findOrderById(String id);
}
