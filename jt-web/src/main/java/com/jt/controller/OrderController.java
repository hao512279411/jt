package com.jt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 订单业务
 */
@RequestMapping("/order")
@Controller
public class OrderController {

    /**
     * 跳转订单页面
     * 查看我的订单
     */
    @RequestMapping("myOrder")
    public String myOrder(){
        System.out.println("访问到订单界面");
        return "my-orders";
    }



}
