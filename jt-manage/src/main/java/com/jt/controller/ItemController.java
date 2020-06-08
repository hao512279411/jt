package com.jt.controller;

import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.vo.JsonPage;
import com.jt.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.jt.service.ItemService;

import javax.annotation.Resource;

@RestController
@RequestMapping("/item")
public class ItemController {

    //item/query/item/desc/1474391959
    @Autowired
    private ItemService itemService;

    //item/query?page=1&rows=20
    //分页查询所有商品列表
    @GetMapping("/query")
    public JsonPage<Item> queryAll(Integer page, Integer rows) {
        return itemService.queryAll(page, rows);
    }

    /**
     * url: http://localhost/item/save  新增单条
     */
    @PostMapping("/save")
    public SysResult saveItemOne(Item item) {
        System.out.println(item);
		itemService.saveItemOne(item);
		return SysResult.success();
    }

    /**
     * item/instock
     * 批量下架商品
     * @param ids
     * @return
     */
    @RequestMapping("/instock")
    public SysResult instock(Integer[] ids) {

        return itemService.instock(ids);
    }

    //  /item/reshelf
    //批量上架 商品 1
    @RequestMapping("/reshelf")
    public SysResult reshelf(Integer[] ids) {
        return itemService.reshelf(ids);
    }


    //  /item/delete
    //	批量删除
    @PostMapping("/delete")
    public SysResult delete(Long[] ids) {
        SysResult sysResult = new SysResult();
        if (itemService.deleteByIds(ids) > 0) {
            sysResult.setStatus(200);
        } else {
            sysResult.setMsg("删除失败");
        }
        return sysResult;
    }



    //	/item/update

    /**
     * 根据 ID 更新单条
     */
    @PostMapping("/update")
    public SysResult updateOneById(Item item) {
        itemService.updateOneById(item);
        return SysResult.success("更新成功",null);
    }


    //	/item/param/list?page=1&rows=30
    /**
     * http://localhost/item/query/item/desc/1474391976
     * 获取 item 的商品描述信息
     */
    @RequestMapping("/query/item/desc/{itemId}")
	public SysResult<ItemDesc> queryItemDescByItemId(@PathVariable Long itemId){

        return SysResult.success( itemService.queryItemDescByItemId(itemId));
    }






}
