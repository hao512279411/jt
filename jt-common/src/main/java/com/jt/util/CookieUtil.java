package com.jt.util;

import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {


    /**
     * 获取指定Cookie
     */
    public static Cookie getCookie(HttpServletRequest request, String key){
        if (StringUtils.isEmpty(key)){return null;}
        Cookie[] cookies = request.getCookies();
        for (Cookie ck:cookies) {
            if (key.equals(ck.getName())){
                return ck;
            }
        }
        return null;
    }

    /**
     * 获取所有Cookie名称
     */
    public static String[] gatCookieNames(HttpServletRequest request){
        if (request == null){return null;}
        Cookie[] cookies = request.getCookies();
        if (cookies == null || cookies.length<1){return null;}
        String[] cookieNames = new String[cookies.length];
        for (int i = 0; i < cookies.length; i++) {
            cookieNames[i]=cookies[i].getName();
        }
        return cookieNames;
    }

    /**
     * 新增Cookie
     * 默认方法
     */

    public static void addCookie(HttpServletResponse response,String uuid){
        if (response == null || StringUtils.isEmpty(uuid)){return;}
        Cookie jt_ticket = new Cookie("JT_TICKET", uuid);
        jt_ticket.setMaxAge(7*24*60*60);
        jt_ticket.setPath("/");
        jt_ticket.setDomain("jt.com");
        response.addCookie(jt_ticket);
    }
    public static void addCookie(HttpServletResponse response,String key,String value,String path,String domain,Integer maxAge){
        if (response == null){return;}
        Cookie cookie =new Cookie(key,value);
        //存活时间
        cookie.setMaxAge(maxAge);
        //作用 路径
        cookie.setPath(path);
        //作用 域名
        cookie.setDomain(domain);
        response.addCookie(cookie);
    }

    /**
     * 删除cookie
     * 默认方法
     */
    public static void deleteCookie(HttpServletRequest request,HttpServletResponse response){
        if (response == null){return;}
        if (request == null){return;}
        Cookie cookie = getCookie(request, "JT_TICKET");
        cookie.setMaxAge(0);
        cookie.setPath("/");
        cookie.setDomain("jt.com");
        response.addCookie(cookie);
    }



}
