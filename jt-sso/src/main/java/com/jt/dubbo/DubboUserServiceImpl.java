package com.jt.dubbo;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.Service.DubboUserService;
import com.jt.exception.ServiceException;
import com.jt.mapper.UserMapper;

import com.jt.pojo.User;
import com.jt.util.JsonUtil;
import com.jt.vo.SysResult;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import redis.clients.jedis.JedisCluster;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

@Service(timeout = 3000)
public class DubboUserServiceImpl implements DubboUserService {

    @Resource
    UserMapper userMapper ;
    @Autowired
    JedisCluster jedisCluster;


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
    @Transactional
    public SysResult doRegister(User user) {
        //1、将密码进行加密
        SimpleHash sh =new SimpleHash("MD5",user.getPassword());
        user.setPassword(sh.toHex()).setEmail(user.getPhone()).setCreated(new Date()).setUpdated(new Date());
        System.out.println("当前USer加密后的 详情"+user);
        int results = userMapper.insert(user);
        if (results > 0){
            return SysResult.success(user.getUsername());
        }
        return SysResult.fail();
    }


    /**
     * url: http://www.jt.com/user/doLogin?r=0.08355039569574596
     * 用户发送账号密码登录
     * user: 接收输入的username和password
     */
    public String findUserByUP(User user) {
        String uuid=null;
        //数据库中查询账号密码
        QueryWrapper<User> qw=new QueryWrapper();
        //密码加密
        String d5Pass = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        qw.eq("username",user.getUsername()).eq("password",d5Pass);
        User userDB = userMapper.selectOne(qw);

        //账户密码正确
        if (userDB != null){
            //抹掉密码
            userDB.setPassword(null);
            //设置缓存key
            uuid = UUID.randomUUID().toString();
            //存入缓存
            jedisCluster.setex(uuid,7*24*60*60, JsonUtil.toJson(userDB));
            //返回账号唯一表示
            return uuid;
        }else {
            //账号密码错误
            return uuid;
        }
    }
    /**
     * 退出登录状态
     * 删除用户的cookie 和缓存里的cookie
     * URL: http://www.jt.com/user/logout.html
     * GET
     */
    public void removeCookie(String redisKey) {
        System.out.println("开始删除redisKey:"+redisKey);
        jedisCluster.del(redisKey);
    }


}
