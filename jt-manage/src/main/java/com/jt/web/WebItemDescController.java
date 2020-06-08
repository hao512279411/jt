package com.jt.web;


import com.jt.mapper.ItemDescMapper;
import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.service.ItemService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/web/itemDesc")
public class WebItemDescController {

    @Resource
    ItemDescMapper itemDescMapper;

    @Resource
    ItemService itemService;
    /**
     * url: http://manage.jt.com/web/itemDesc/fidItemDescByItemId
     * 根据itemId 查询对象的Desc对象
     */
    @RequestMapping("/fidItemDescByItemId")
    public ItemDesc fidItemDescByItemId(Long itemId){

         ItemDesc itemDesc=itemService.queryItemDescByItemId(itemId);
        return itemDesc;
    }



}
