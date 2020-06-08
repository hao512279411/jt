package com.jt.service;

import com.jt.pojo.User;
import com.jt.vo.SysResult;

public interface UserService {
    /**
     * URL: http://sso.jt.com/user/check/{param}/{type}
     *		Type为类型，可选参数1 username、2 phone、3 email
     *	检查注册用户名填写的资料是否重复
     */
    SysResult checkUserRepeat(String param, Byte type);
    /**
     * 提交注册信息  新增单个用户
     * URL: http://sso.jt.com/user/register
     */
    SysResult doRegister(User user);
}
