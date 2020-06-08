package com.jt.controller;

import com.jt.pojo.User;
import com.jt.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserConroller {

    @Resource
    UserService userService;

    /**
     * url: http://www.jt.com/user/login.html
     * 跳转登录界面
     */
    @GetMapping("/{path}")
    public String login(@PathVariable String path) {

        return path;
    }


    /**
     * url: http://www.jt.com/user/doLogin?r=0.08355039569574596
     * 用户发送账号密码登录
     * user: 接收输入的username和password
     */
    @RequestMapping("doLogin")
    public String doLogin(User user) {
        System.out.println(user);
        return null;
    }

    /**
     * URL: http://www.jt.com/user/doRegister
     * 提交注册信息  新增单个用户
     */
    @RequestMapping("/doRegister")
    @ResponseBody
    public String doRegister(User user) {
       return userService.doRegister(user);
    }


}
