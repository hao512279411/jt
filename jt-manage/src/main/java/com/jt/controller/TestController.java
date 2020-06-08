package com.jt.controller;

import com.jt.pojo.Item;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@RestController
public class TestController {

    @PostMapping("/testPost")
    public String testPost(HttpServletRequest request){
        System.out.println("接收到Post 请求");
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()){

            System.out.println("参数是:"+parameterNames.nextElement());
        }


        return "请求测试成功";

    }
}
