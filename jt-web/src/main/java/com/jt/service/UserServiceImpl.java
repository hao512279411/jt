package com.jt.service;

import com.jt.pojo.User;
import com.jt.util.HttpClientServiceUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService{

    @Resource
    HttpClientServiceUtil clientServiceUtil;
    /**
     * 将user发送给 SSO 将USER数据存储到数据库
     * POST
     * 发送请求URL http://sso.jt.com/user/register
     */
    public String doRegister(User user) {

        Map userMap = new HashMap();

        userMap.put("username",user.getUsername());
        userMap.put("password",user.getPassword());

        if (!StringUtils.isEmpty(user.getPhone())) {
            userMap.put("phone", user.getPhone());
            userMap.put("email", user.getPhone());
        }else {
            userMap.put("email", user.getEmail());
        }
        String  results = clientServiceUtil.doPost("http://sso.jt.com/user/register", userMap);
        return results;
    }
}
