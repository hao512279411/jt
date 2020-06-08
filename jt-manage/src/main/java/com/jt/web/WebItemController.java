package com.jt.web;

import com.jt.pojo.Item;
import com.jt.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/web/item")
public class WebItemController {

    @Autowired
    ItemService itemService;


    /*==============================处理跨域请求================================================================================*/
    /**
     * URL: http://manage.jt.com/web/item/fidItemById
     * 根据 itemId  查询单个的所有信息
     * @param itemId
     * @return
     */
    @GetMapping("/fidItemById")
    public Item fidItemById(Integer itemId){
        Item item = itemService.queryByIdOne(itemId);
        return item;

    }

    //http://manage.jt.com/web/itemcat/all?callback=category.getDataService

}
