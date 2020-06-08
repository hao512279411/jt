package com.jt.dubbo;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.Service.DubboUserService;
import com.jt.exception.ServiceException;
import com.jt.mapper.UserMapper;

import com.jt.pojo.User;
import com.jt.vo.SysResult;
import org.apache.shiro.crypto.hash.SimpleHash;

import javax.annotation.Resource;
import java.util.Date;

@Service(timeout = 3000)
public class DubboUserServiceImpl implements DubboUserService {

    @Resource
    UserMapper userMapper ;


    /**
     * URL: http://sso.jt.com/user/check/{param}/{type}
     *		Type为类型，可选参数1 username、2 phone、3 email
     *	检查注册用户名填写的资料是否重复
     */
    public SysResult checkUserRepeat(String param, Byte type) {
        QueryWrapper<User> qw = new QueryWrapper();
        switch (type){
            case 1:
                qw.eq("username",param);
                break;
            case 2:
                qw.eq("phone",param);
                break;
            case 3:
                qw.eq("email",param);
                break;
            default:
                throw new ServiceException("检查注册用户名填写的资料是否重复时 传入 type有误");
        }
        Integer integer = userMapper.selectCount(qw);
        //查看是否有数据
        if (integer == 0){
            return SysResult.success(false);
        }
        //已有数据
        return SysResult.success(true);

    }

    /**
     * 提交注册信息  新增单个用户
     * URL: http://sso.jt.com/user/register
     */
    public SysResult doRegister(User user) {
        //1、将密码进行加密
        SimpleHash sh =new SimpleHash("MD5",user.getPassword());//hashIterations表示加密次数)
        user.setPassword(sh.toHex()).setEmail(user.getPhone()).setCreated(new Date()).setUpdated(new Date());
        System.out.println("当前USer加密后的 详情"+user);
        int results = userMapper.insert(user);
        System.out.println("=========================>"+results);
        if (results > 0){
            return SysResult.success(user.getUsername());
        }
        return SysResult.fail();
    }
}
