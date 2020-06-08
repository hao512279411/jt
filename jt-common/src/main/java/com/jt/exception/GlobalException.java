package com.jt.exception;

import com.jt.vo.SysResult;
import com.sun.tools.javac.util.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//全局异常
//@ControllerAdvice
@RestControllerAdvice
@Slf4j
public class GlobalException {

    //捕获到异常时 运行这个类
    @ExceptionHandler(RuntimeException.class)
    public Object doHandle(RuntimeException e){
//        log.error(e.getMessage());
        e.printStackTrace();
        System.out.println("出现了异常");
        return SysResult.fail();
    }

}
