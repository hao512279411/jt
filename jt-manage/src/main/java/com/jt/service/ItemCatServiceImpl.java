package com.jt.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.anno.CacheFind;
import com.jt.mapper.ItemCatMapper;
import com.jt.pojo.Cat;
import com.jt.vo.EasyUITree;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Resource
    ItemCatMapper itemCatMapper;

//    @Resource
//    Jedis jedis;


    //通过catId获取cat的Name
    @CacheFind(parKey = "ITEM_CAT_ID" ,seconds = 10)
    public String queryItemName(Integer id){
        QueryWrapper<Cat> queryWrapper = new QueryWrapper();
        queryWrapper.eq("id",id);
        queryWrapper.select("name");
        Cat cat = itemCatMapper.selectOne(queryWrapper);
        return cat.getName();
    }

    //根据parent_id查询cat
    // item/cat/list
    @CacheFind(parKey = "ITEM_CAT_PARENT_ID",seconds = 10)
    public List<EasyUITree> catAll(Long id){
//            id= id==null ?  0 : id;
        QueryWrapper<Cat> queryWrapper = new QueryWrapper().select("name","id","is_parent");
        queryWrapper.eq("parent_id",id);
        List<Cat> catList = itemCatMapper.selectList(queryWrapper);

        ArrayList<EasyUITree> treeList = new ArrayList();
        for (int i = 0; i <catList.size(); i++) {
            Cat cat = catList.get(i);
            EasyUITree easyUITree = new EasyUITree();
            easyUITree.setId(cat.getId()).setText(cat.getName()).setState(cat.getIsParent());
            treeList.add(easyUITree);
        }
        return treeList;
    }

}



