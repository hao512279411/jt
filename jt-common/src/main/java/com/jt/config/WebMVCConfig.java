package com.jt.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMVCConfig implements WebMvcConfigurer {
    //新增跨域访问
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  //设置可以跨域的目录
                .allowedOrigins("*")    //同意的请求网址,可以多个 用 ,  分割
                .allowedMethods("GET", "POST", "DELETE", "PUT", "OPTIONS","HEAD")   //允许请求类型
                .allowCredentials(true) //是否允许携带cookie
                .maxAge(3600);          //校验请求的有效期 /s
    }



}
