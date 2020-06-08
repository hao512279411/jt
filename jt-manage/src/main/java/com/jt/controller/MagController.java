package com.jt.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MagController {
    @Value("${server.port}")
    private String port;

    @RequestMapping("getMag")
    public String getMag(){

        return "当前端口号是:"+port;
    }

}
