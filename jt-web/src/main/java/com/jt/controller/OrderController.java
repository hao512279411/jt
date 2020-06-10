package com.jt.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jt.Service.DubboCartService;
import com.jt.Service.DubboOrderService;
import com.jt.pojo.Cart;
import com.jt.pojo.Order;
import com.jt.pojo.User;
import com.jt.util.UserUtil;
import com.jt.vo.SysResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 订单业务
 */
@RequestMapping("/order")
@Controller
public class OrderController {

    @Reference
    DubboOrderService dubboOrderService;

    @Reference
    DubboCartService dubboCartService;


    @Resource
    UserUtil userUtil;

    /**
     * 跳入到购物车订单结算界面
     * url:http://www.jt.com/order/create.html
     */
    @RequestMapping("/create")
    public String orderCart(HttpServletRequest request, Model model){

        User user = userUtil.getUserByReq(request);

        List<Cart> cartList= dubboCartService.orderCart(user.getId());
        model.addAttribute("carts",cartList);
        return "order-cart";
    }


    /**
     * 跳转订单页面
     * 查看我的订单
     */
    @RequestMapping("myOrder")
    public String myOrder(){
        System.out.println("访问到订单界面");
        return "my-orders";
    }

    /**
     * 提交订单  生成订单和数据
     * url:http://www.jt.com/order/submit
     * 返回订单id
     */
    @RequestMapping("/submit")
    @ResponseBody
    public SysResult saveOrder(Order order , HttpServletRequest request){
        User user = userUtil.getUserByReq(request);
        order.setUserId(user.getId());

        String orderId = dubboOrderService.saveOrder(order);
        return SysResult.success(orderId);
    }



}
