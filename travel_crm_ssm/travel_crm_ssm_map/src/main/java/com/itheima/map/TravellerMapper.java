package com.itheima.map;

import com.itheima.entity.Traveller;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TravellerMapper {

    //根据Id查询游客信息
    @Select("select * from traveller where id in (select travellerId from order_traveller where orderId= #{ordersId})")
    public List<Traveller> findTravellerById(String ordersId);
}
