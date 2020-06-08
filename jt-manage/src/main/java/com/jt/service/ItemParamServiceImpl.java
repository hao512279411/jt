package com.jt.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.anno.CacheFind;
import com.jt.exception.ServiceException;
import com.jt.mapper.ItemParamItemMapper;
import com.jt.mapper.ItemParamMapper;
import com.jt.pojo.ItemParam;
import com.jt.pojo.ItemParamItem;
import com.jt.vo.JsonPage;
import com.jt.vo.SysResult;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class ItemParamServiceImpl implements ItemParamService{

    @Resource
    ItemParamMapper itemParamMapper;

    @Resource
    ItemParamItemMapper itemParamItemMapper;

    //  根据商品ID 查询 参数
    public ItemParamItem selectParamByCatId(Long catId){
        QueryWrapper<ItemParamItem> queryWrapper = new QueryWrapper();
        queryWrapper.eq("item_id",catId).select("param_data");
        ItemParamItem itemParamItem = itemParamItemMapper.selectOne(queryWrapper);
        if (itemParamItem==null){
            throw new ServiceException("未找到商品的参数详情");
        }
        return itemParamItem;
    }

    //  分页查询商品规格和参数
    @CacheFind(parKey = "ITEM_PARAM_PAGE_ROWS",seconds = 10)
    public JsonPage<ItemParam> selectItemParams(Long page, Integer rows){
/*        //创建分页查询条件  查询商品规格和参数
        Page <ItemParam> pageParam =new Page(page,rows);
        QueryWrapper<ItemParam> queryWrapper =new QueryWrapper();
        queryWrapper.orderByDesc("created");
        IPage<ItemParam> itemParamIPage = itemParamMapper.selectPage(pageParam, queryWrapper);
        //设置返回值类型
        JsonPage<ItemParam> jsonPage = new JsonPage<>();
        jsonPage.setTotal((int) itemParamIPage.getTotal());
        jsonPage.setRows(itemParamIPage.getRecords());
        return jsonPage;*/


        //查询 商品分类的name  根据类目id
//        page-1*rows  rows
        List<ItemParam> itemParams = itemParamMapper.selectItemParamAndCatName((page-1)*rows, rows);
        //设置返回值类型

        JsonPage<ItemParam> jsonPage = new JsonPage<>();
        //将总页数 放入 json
        jsonPage.setTotal(itemParamMapper.selectCount(null));
        //将参数放入 json
        jsonPage.setRows(itemParams);
        return jsonPage;
    }
    /**
     * 新增商品参数表 数据
     *
     */
    public SysResult saveParam(String paramData, Long itemCatId){
        ItemParam itemParam = new ItemParam();
        itemParam.setParamData(paramData).setItemCatId(itemCatId).setUpdated(new Date()).setCreated(new Date());
        int insert = itemParamMapper.insert(itemParam);
        SysResult sysResult = new SysResult();
        if (insert>0){
            sysResult.setStatus(200);
        }
        return sysResult;

    }
    /**
     * 删除 多个商品参数表
     */
    public SysResult deleteParamByIds(Long[] ids){
        List<Long> idsList = Arrays.asList(ids);

        int delete = itemParamMapper.deleteBatchIds(idsList);
        SysResult sysResult = new SysResult();
        if (delete>0){
            sysResult.setStatus(200);
        }
        return sysResult;
    }


    /**
     * URL: http://localhost/item/param/query/itemcatid/163
     * 根据类目ID  获取类目的参数规则表
     */
    public ItemParam queryItemParam(Long itemCatId){
        QueryWrapper<ItemParam> queryWrapper =new QueryWrapper();
        queryWrapper.eq("item_cat_id",itemCatId);
        ItemParam itemParam = itemParamMapper.selectOne(queryWrapper);
        if (itemCatId == null){
            throw new ServiceException();
        }
        return itemParam;
    }
}
