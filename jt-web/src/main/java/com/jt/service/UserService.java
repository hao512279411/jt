package com.jt.service;

import com.jt.pojo.User;

import java.util.Map;

public interface UserService {
    /**
     * URL: http://www.jt.com/user/doRegister
     * 提交注册信息  新增单个用户
     */
    public String doRegister(User user);
}
