package com.jt.util;


import com.jt.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import redis.clients.jedis.JedisCluster;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * 缓存查询用户信息工具
 */
@Component
public class UserUtil {
    @Autowired
    private  JedisCluster jedisCluster;

    /**
     * 通过 uuid 缓存中获取user对象
     */
    public  User getUserByReq(HttpServletRequest request){
//        Cookie cookie=null;
//        Cookie[] cookies = request.getCookies();
//        for (Cookie ck:cookies) {
//            if ("JT_TICKET".equals(ck.getName())){
//                cookie=ck;
//                break;
//            }
//        }

        Cookie cookie=CookieUtil.getCookie(request,"JT_TICKET");

        if (cookie == null){
            return null;
        }

        String value = jedisCluster.get(cookie.getValue());

        if (StringUtils.isEmpty(value)){
            return null;
        }

        return JsonUtil.toObject(value,User.class);
    }



}
