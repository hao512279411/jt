package com.jt.service;

import com.jt.pojo.Content;
import com.jt.vo.JsonPage;

public interface ContentService {
    // 分页获取所有内容
    public JsonPage<Content> queryContentAll(Integer categoryId, Long page, Integer rows);
}
