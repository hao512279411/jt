package com.jt.service;

import com.jt.pojo.ItemParam;
import com.jt.pojo.ItemParamItem;
import com.jt.vo.JsonPage;
import com.jt.vo.SysResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

public interface ItemParamService {


    //  根据商品ID 查询 参数
    public ItemParamItem selectParamByCatId(Long catId);

    //  分页查询商品规格和参数
    public JsonPage<ItemParam> selectItemParams(Long page, Integer rows);
    /**
     * 新增商品参数表 数据
     *
     */
    public SysResult saveParam(String paramData, Long itemCatId);
    /**
     * 删除 多个商品参数表
     */
    public SysResult deleteParamByIds(Long[] ids);


    /**
     * URL: http://localhost/item/param/query/itemcatid/163
     * 根据类目ID  获取类目的参数规则表
     */
    public ItemParam queryItemParam(Long itemCatId);
}
