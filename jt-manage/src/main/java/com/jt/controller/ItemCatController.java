package com.jt.controller;

import com.jt.service.ItemCatService;
import com.jt.vo.EasyUITree;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/item/cat/")
public class ItemCatController {

    @Resource
    ItemCatService itemCatService;

    //通过catId获取cat的Name
    //item/cat/queryItemName
    @RequestMapping("queryItemName")
    public String queryItemName(Integer itemCatId) {
        return itemCatService.queryItemName(itemCatId);
    }
    //根据ID查询所有cat的数据结构
//     item/cat/list
    @RequestMapping("list")

    public List<EasyUITree> catNameAll(@RequestParam (defaultValue = "0") Long id){
        return itemCatService.catAll(id);
    }
}
