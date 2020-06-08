package com.jt.controller;

import com.jt.pojo.ItemParam;
import com.jt.service.ItemParamService;
import com.jt.vo.JsonPage;
import com.jt.vo.SysResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(("/item/param"))
public class ItemParamController {

    @Resource
    ItemParamService itemParamService;

    /**
     * /item/param/item/query/1474391961
     * 根据商品分类ID 查询 商品规格参数和商品关系表  获取商品的具体规格参数
     * @param itemId
     * @return
     */
    @RequestMapping("/item/query/{itemId}")
    public SysResult selectParamByCatId(@PathVariable Long itemId){
        return SysResult.success(itemParamService.selectParamByCatId(itemId));

    }


    //  //localhost/item/param/list?page=1&rows=30
    //  分页查询商品规格和参数
    @RequestMapping("list")
    public JsonPage<ItemParam> selectItemParams(Long page, Integer rows){
       return itemParamService.selectItemParams(page, rows);

    }

    ////localhost/item/param/save/1149
    /**
     * 新增商品参数表 数据
     * id 分类ID
     */
    @RequestMapping("save/{itemCatId}")
    public SysResult saveParam(String paramData, @PathVariable Long itemCatId){
        return itemParamService.saveParam(paramData, itemCatId);

    }

    //  //localhost/item/param/delete
    /**
     * 删除 多个商品参数表
     */
    @RequestMapping("/delete")
    public SysResult deleteParamByIds(Long[] ids){
        return itemParamService.deleteParamByIds(ids);

    }


    /**
     * URL: http://localhost/item/param/query/itemcatid/163
     * 根据类目ID  获取类目的参数规则表
     */
    @RequestMapping("/query/itemcatid/{itemCatId}")
    public SysResult queryItemParam(@PathVariable Long itemCatId){
        ItemParam itemParam = itemParamService.queryItemParam(itemCatId);

        return SysResult.success(itemParam);
    }

}
