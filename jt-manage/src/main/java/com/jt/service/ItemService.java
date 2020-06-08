package com.jt.service;

import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.vo.JsonPage;
import com.jt.vo.SysResult;
import org.springframework.web.bind.annotation.PathVariable;

public interface ItemService {
    //查询所有商品列表
    public JsonPage<Item> queryAll(Integer page, Integer rows);
    //批量下架商品
    public SysResult instock(Integer[] ids);
    //批量上架 商品 1
    public SysResult reshelf(Integer[] ids);
    //	批量删除
    public Integer deleteByIds(Long[] ids);
    //基于ID 查询单个
    public Item queryByIdOne(Integer id);
    /**
     * 根据 ID 更新单条
     */
    public void updateOneById(Item item);

    /**
     * /item/save 新增单条
     */
    public void saveItemOne(Item item);

    /**
     * 获取 item 的商品描述信息
     * @return
     */
    public ItemDesc queryItemDescByItemId(Long itemId);

    /*==============================处理跨域请求================================================================================*/

}
