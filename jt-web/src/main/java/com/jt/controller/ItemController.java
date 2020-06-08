package com.jt.controller;

import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.service.ItemService;
import com.jt.util.HttpClientServiceUtil;
import com.jt.util.JsonUtil;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

        System.out.println("收到ID"+itemId);
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
        System.out.println("测试Post请求开始");
        String url="http://manage.jt.com/testPost";
        Map<String , String > map = new HashMap();
        map.put("id","100");
        String s = null;
        s = httpClientServiceUtil.doPost(url, map, null);
        System.out.println("Post请求 接收到的参数:"+s);
        return s;
    }

}
