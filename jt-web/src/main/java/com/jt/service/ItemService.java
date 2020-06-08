package com.jt.service;

import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import org.springframework.stereotype.Service;


public interface ItemService {

    /**
     * 根据 itemId  查询单个的所有信息
     * @param itemId
     * @return
     */
    public Item fidItemById(Long itemId);


    /**
     * 根据 itemId  查询单个desc的所有信息
     * @param itemId
     * @return
     */
    public ItemDesc fidItemDescByItemId(Long itemId);
}
