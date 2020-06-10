package com.jt.Service;

import com.jt.pojo.Cart;

import java.util.List;
import java.util.Map;

public interface DubboCartService {


    List<Cart> findCartListByUserId(Long userId);
    /**
     * 根据商品ID  给用户购物车新增商品
     * url: http://www.jt.com/cart/add/562379.html
     */
    void addCart( Cart setItemId);
    /**
     * 删除客户 购物车中的商品
     * URL: http://www.jt.com/cart/delete/562379.html
     */
    void deleteCartOne(Map cartMap);
    /**
     * 给用户 的商品新增
     * url: http://www.jt.com/cart/update/num/562379/4
     * Post
     */
    void updateNum(Cart cart);
    /**
     * 跳入到购物车订单结算界面
     * url:http://www.jt.com/order/create.html
     */
    public List<Cart> orderCart(Long userid);
}
