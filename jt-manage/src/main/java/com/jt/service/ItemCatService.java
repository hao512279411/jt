package com.jt.service;

import com.jt.pojo.Cat;
import com.jt.vo.EasyUITree;

import java.util.List;

public interface ItemCatService {

    //通过catId获取cat的Name
    public String queryItemName(Integer id);
    //查询所有cat
    public List<EasyUITree> catAll(Long id);

}
