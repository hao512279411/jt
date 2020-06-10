package com.jt.service;


import com.jt.Service.DubboOrderService;
import com.jt.pojo.Order;
import com.jt.pojo.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;


import com.alibaba.dubbo.config.annotation.Service;

import com.jt.mapper.OrderItemMapper;
import com.jt.mapper.OrderMapper;
import com.jt.mapper.OrderShippingMapper;
import org.springframework.transaction.annotation.Transactional;


@Service
public class OrderServiceImpl implements DubboOrderService {
	
	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private OrderShippingMapper orderShippingMapper;
	@Autowired
	private OrderItemMapper orderItemMapper;


	/**
	 * 提交订单  生成订单和数据
	 * url:http://www.jt.com/order/submit
	 * 返回订单id
	 * @return
	 */
	@Transactional
	public String saveOrder(Order order) {

		String orderId=new StringBuffer("").append(order.getUserId()).append(System.currentTimeMillis()).toString();
		order.setOrderId(orderId);
		orderMapper.insert(order);
		order.getOrderShipping().setOrderId(orderId);
		orderShippingMapper.insert(order.getOrderShipping());
		for (OrderItem orderItem :order.getOrderItems() ) {
			orderItem.setOrderId(orderId);
		}
		orderItemMapper.insertItems(order.getOrderItems());
		return orderId;
	}
}
