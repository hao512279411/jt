package com.jt.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jt.Service.DubboUserService;


import com.jt.pojo.User;
import com.jt.vo.SysResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/user")
public class UserController {

    @Reference
    DubboUserService dubboUserService;

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
    @PostMapping("/doRegister")
    @ResponseBody
    public SysResult doRegister(User user) {
        return  dubboUserService.doRegister(user);
    }


}
