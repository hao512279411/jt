package com.jt.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.jt.Service.DubboCartService;
import com.jt.mapper.CartMapper;
import com.jt.pojo.Cart;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class DubboCartServiceImpl implements DubboCartService {

    @Resource
    CartMapper cartMapper;


    /**
     * 查询客户的购物车列表
     * url: http://www.jt.com/cart/show.html
     * GET
     */
    public List<Cart> findCartListByUserId(Long userId) {
        QueryWrapper<Cart> qw =new QueryWrapper();
        qw.eq("user_id",userId);
        return cartMapper.selectList(qw);
    }

    /**
     * 根据商品ID  给用户购物车新增商品
     * url: http://www.jt.com/cart/add/562379.html
     */
    public void addCart(Cart cart) {

        //查询数据库里是否已经有商品了
        QueryWrapper<Cart> qw =new QueryWrapper();
        qw.eq("user_id",cart.getUserId()).eq("item_id",cart.getItemId());

        cart.setUpdated(new Date());

        Cart cartDB = cartMapper.selectOne(qw);
        //如果数据库里已经有商品了
        if(cartDB != null){
            //商品数量加在一起
            cart.setNum(cartDB.getNum()+cart.getNum());
            //更新数据库
            UpdateWrapper<Cart> uw = new UpdateWrapper();
            uw.eq("user_id",cart.getUserId()).eq("item_id",cart.getItemId());
            cartMapper.update(cart,uw);
        }else {
            // 数据库里没有商品
            cart.setCreated(new Date());
            cartMapper.insert(cart);
        }


    }

    /**
     * 删除客户 购物车中的商品
     * URL: http://www.jt.com/cart/delete/562379.html
     */
    public void deleteCartOne(Map cartMap) {

        cartMapper.deleteByMap(cartMap);

    }

    /**
     * 给用户 的商品新增
     * url: http://www.jt.com/cart/update/num/562379/4
     * Post
     */
    public void updateNum(Cart cart) {
        UpdateWrapper<Cart> uw = new UpdateWrapper();
        uw.eq("user_id",cart.getUserId()).eq("item_id",cart.getItemId());
        cart.setUpdated(new Date());
        cartMapper.update(cart,uw);


    }


}
