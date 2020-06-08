package com.jt.controller;

import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.service.ItemService;
import com.jt.util.HttpClientServiceUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/items")
public class ItemController {

    @Resource
     private ItemService itemService;

    /**
     * 根据 itemId  查询单个的所有信息
     * @param itemId
     * @return
     */
    @RequestMapping("/{itemId}")
    public String fidItemById(@PathVariable Long itemId, Model model){
        //调用service
        Item item = itemService.fidItemById(itemId);
        //调用方法 根据itemId 查询Desc
        ItemDesc itemDesc = itemService.fidItemDescByItemId(itemId);
        //存入session域
        model.addAttribute("item",item);
        model.addAttribute("itemDesc",itemDesc);
        return "item";
    }


    @Resource
    HttpClientServiceUtil httpClientServiceUtil;

    @RequestMapping("/test")
    @ResponseBody
    public String testPost(){
        String url="http://manage.jt.com/testPost";
        Map<String , String > map = new HashMap();
        map.put("id","100");
        String s = null;
        s = httpClientServiceUtil.doPost(url, map, null);
        return s;
    }

}
