package com.itheima.map;

import com.itheima.entity.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Product持久层接口
 */
public interface ProductMapper {

    //查找所有商品
    @Select("select * from product")
    public List<Product> findAll() throws Exception;

    //查找单个商品
    @Select("select * from product where id=#{id}")
    public Product findProductById(String id) throws Exception;

    //保存商品
    @Insert("insert into product(productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus) values(#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    public void save(Product product) throws Exception;
}
