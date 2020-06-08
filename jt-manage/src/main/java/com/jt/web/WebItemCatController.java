package com.jt.web;

import com.jt.mapper.ItemCatMapper;
import com.jt.service.ItemCatService;
import com.jt.service.ItemService;
import com.jt.vo.EasyUITree;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RequestMapping("/web/itemcat")
@RestController
public class WebItemCatController {


    @Resource
    ItemCatService itemCatService;

    /**
     * url: http://manage.jt.com/web/itemcat/all?callback=category.getDataService
     *
     * 查询所有 itemcat
     */
//    @RequestMapping("/all")
//    public String allItemCat(@RequestParam(defaultValue = "0") Long id) {
//        List<EasyUITree> easyUITrees = itemCatService.catAll(id);
//        return easyUITrees;
//
//    }


}
