package com.jt.controller;

import com.jt.pojo.Content;
import com.jt.service.ContentService;
import com.jt.vo.JsonPage;
import com.jt.vo.SysResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 商品类容管理
 */
@RestController
@RequestMapping("/content")
public class ContentController {

    @Resource
    ContentService contentService;
    //  /content/query/list?categoryId=0&page=1&rows=20

    /**
     * Request URL: http://localhost/content/query/list?categoryId=0&page=1&rows=20
     * Request Method: GET
     * @param categoryId
     * @param page
     * @param rows
     * @return
     */

    @RequestMapping("/query/list")
    // 分页获取所有内容
    public JsonPage<Content> queryContentAll(Integer categoryId, Long page, Integer rows){
        return contentService.queryContentAll(categoryId,page,rows);

    }

    //  //localhost/content/category/list
    // 获取所有内容分类表
//    @RequestMapping("/category/list")
//    public SysResult categoryListXY(){
//        SysResult sysResult = new SysResult();
//
//
//        return sysResult;
//    }

}
