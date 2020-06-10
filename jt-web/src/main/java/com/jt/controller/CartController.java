package com.jt.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jt.Service.DubboCartService;
import com.jt.pojo.Cart;
import com.jt.pojo.User;
import com.jt.util.UserUtil;
import com.jt.vo.SysResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 购物车业务
 */
@RequestMapping("/cart")
@Controller
public class CartController {

    @Reference(check = false)
    DubboCartService dubboCartService;

    @Resource
    UserUtil userUtil;

    /**
     * 查询客户的购物车列表
     * url: http://www.jt.com/cart/show.html
     * GET
     */
    @GetMapping("/show")
    public String show(Model model, HttpServletRequest request){
//        User user = userUtil.getUserByReq(request);
//        Long userId =  user.getId();
        Long userId = ((User)request.getAttribute("JT_USER")).getId();


        List<Cart> cartList = dubboCartService.findCartListByUserId(userId);
        model.addAttribute("cartList",cartList);
        return "cart";
    }






    /**
     * 根据商品ID  给用户购物车新增商品
     * url: http://www.jt.com/cart/add/562379.html
     */
    @PostMapping("/add/{itemId}")
    public String addCart(Cart cart, HttpServletRequest request){
//        User user = userUtil.getUserByReq(request);
//        Long userId =  user.getId();
        Long userId = ((User)request.getAttribute("JT_USER")).getId();
        dubboCartService.addCart(cart.setUserId(userId));
        return "redirect:/cart/show";
    }

    /**
     * 删除客户 购物车中的商品
     * URL: http://www.jt.com/cart/delete/562379.html
     */
    @GetMapping("/delete/{itemId}")
    public String deleteCartOne(@PathVariable Long itemId,HttpServletRequest request){
//        User user = userUtil.getUserByReq(request);
//        Long userId =  user.getId();
        Long userId = ((User)request.getAttribute("JT_USER")).getId();
        Map<String , Object> map =new HashMap();
        map.put("item_id",itemId);
        map.put("user_id",userId);
        dubboCartService.deleteCartOne(map);
        return "redirect:/cart/show";
    }


    /**
     * 给用户 的商品新增
     * url: http://www.jt.com/cart/update/num/562379/4
     * Post
     */
    @PostMapping("/update/num/{itemId}/{num}")
    public SysResult updateNum(HttpServletRequest request, Cart cart){

        User user = userUtil.getUserByReq(request);
        cart.setUserId(user.getId());
        dubboCartService.updateNum(cart);

        return SysResult.success();
    }


}
