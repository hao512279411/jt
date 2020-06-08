package com.jt.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jt.mapper.ContentMapper;
import com.jt.pojo.Content;
import com.jt.vo.JsonPage;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ContentServiceImpl implements ContentService{

    @Resource
    ContentMapper contentMapper;
    // 分页获取所有内容
    public JsonPage<Content> queryContentAll(Integer categoryId, Long page, Integer rows){
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.orderByAsc("id");
        Page page1 =new Page(page,rows);
        IPage iPage = contentMapper.selectPage(page1, queryWrapper);

        JsonPage<Content> jsonPage = new JsonPage();
        jsonPage.setTotal((int) iPage.getTotal());
        jsonPage.setRows(iPage.getRecords());
        return jsonPage;

    }
}
