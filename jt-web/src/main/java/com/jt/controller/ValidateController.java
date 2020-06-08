package com.jt.controller;

import com.jt.vo.SysResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 验证邮箱测试类
 */
@RestController
public class ValidateController {

    /**
     * URL: http://www.jt.com/validate/isEmailEngaged?email=tony@263.con&r=0.32773873041794466
     *
     */

    @RequestMapping("/validate/isEmailEngaged")
    public SysResult validate(){

        return SysResult.success(false);
    }

    /**
     * url http://www.jt.com/validateuser/isEmailEngaged?email=tony@263.con&r=0.11650017539884838
     */
    @RequestMapping("/validateuser/isEmailEngaged")
    public SysResult validateuser(){

        return SysResult.success(false);
    }



}
