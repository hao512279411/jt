package com.jt.service;

import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.util.HttpClientServiceUtil;
import com.jt.util.JsonUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class ItemServiceImpl implements ItemService{

    //工具 跨域请求
    @Resource
    HttpClientServiceUtil httpClientServiceUtil;


    /**
     * 根据 itemId  查询单个的所有信息
     * @param itemId
     * @return
     */
    public Item fidItemById(Long itemId) {

        Map<String , String > paramMap = new HashMap();
        paramMap.put("itemId",itemId.toString());
        String json = httpClientServiceUtil.doGet("http://manage.jt.com/web/item/fidItemById", paramMap);
       return JsonUtil.toObject(json,Item.class);
    }

    /**
     * 根据 itemId  查询单个desc的所有信息
     * @param itemId
     * @return
     */
    public ItemDesc fidItemDescByItemId(Long itemId){
        Map<String , String > paramMap = new HashMap();
        paramMap.put("itemId",itemId.toString());
        String json = httpClientServiceUtil.doGet("http://manage.jt.com/web/itemDesc/fidItemDescByItemId", paramMap);

        return JsonUtil.toObject(json,ItemDesc.class);

    }


}
