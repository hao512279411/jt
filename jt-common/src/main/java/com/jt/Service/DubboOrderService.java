package com.jt.Service;

import com.jt.pojo.Order;

/**
 * 提交订单  生成订单和数据
 * url:http://www.jt.com/order/submit
 * 返回订单id
 */
public interface DubboOrderService {


    String saveOrder(Order order);
}
