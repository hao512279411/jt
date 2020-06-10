package com.jt.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jt.ordersql.OrderItemSQL;
import com.jt.pojo.OrderItem;
import org.apache.ibatis.annotations.InsertProvider;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderItemMapper extends BaseMapper<OrderItem>{




    @InsertProvider(type = OrderItemSQL.class , method = "insertItems")
    void insertItems(@Param("list") List<OrderItem> items);


}