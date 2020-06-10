package com.jt.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jt.Service.DubboUserService;


import com.jt.pojo.User;
import com.jt.vo.SysResult;
import org.jboss.netty.handler.codec.http.CookieEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
@RequestMapping("/user")
public class UserController {

    @Reference
    DubboUserService dubboUserService;

    /**
     * url: http://www.jt.com/user/login.html
     * 跳转登录界面
     */
    @GetMapping("/login")
    public String login() {
        return "login";
    }


    /**
     * url: http://www.jt.com/user/doLogin?r=0.08355039569574596
     * 用户发送账号密码登录
     * user: 接收输入的username和password
     */
    @RequestMapping("doLogin")
    @ResponseBody
    public SysResult doLogin(User user , HttpServletResponse response) {


        String uuid = dubboUserService.findUserByUP(user);

        if (StringUtils.isEmpty(uuid)){
            //没有生成uuid的话 登录失败
            return SysResult.fail();
        }
        //登录成功将uuid 传入cook
        Cookie jt_ticket = new Cookie("JT_TICKET", uuid);
        jt_ticket.setMaxAge(7*24*60*60);
        jt_ticket.setPath("/");
        jt_ticket.setDomain("jt.com");

        response.addCookie(jt_ticket);

        return SysResult.success();
    }

    /**
     * 提交注册信息  新增单个用户
     * URL: http://www.jt.com/user/doRegister
     */
    @PostMapping("/doRegister")
    @ResponseBody
    public SysResult doRegister(User user) {
        return  dubboUserService.doRegister(user);
    }


    /**
     * 退出登录状态
     * 删除用户的cookie 和缓存里的cookie
     * URL: http://www.jt.com/user/logout.html
     * GET
     */
    @GetMapping("/logout")
    public String logout(HttpServletRequest request,HttpServletResponse response){
        System.out.println("进入到删除cookie方法");
        Cookie cookie=null;
        Cookie[] cookies = request.getCookies();
        if (cookies == null || cookies.length<1){
            return "redirect:/";
        }

        for (Cookie ck:cookies) {
            if ("JT_TICKET".equals(ck.getName())){
                cookie = ck;
                break;
            }
        }
        //删除缓存中的cookie
        dubboUserService.removeCookie(cookie.getValue());
        //删除客户端的cookie
//        cookie.setValue("");
        cookie.setMaxAge(0);
        cookie.setPath("/");
        cookie.setDomain("jt.com");
        response.addCookie(cookie);
        return "redirect:/";

    }



}
