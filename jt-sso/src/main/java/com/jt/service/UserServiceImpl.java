package com.jt.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.exception.ServiceException;
import com.jt.pojo.User;
import com.jt.vo.SysResult;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.QueryMethod;
import org.springframework.stereotype.Service;

import com.jt.mapper.UserMapper;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import javax.sql.rowset.serial.SerialException;
import java.util.Date;

@Service
public class UserServiceImpl implements UserService {
	
	@Resource
	private UserMapper userMapper;


	/**
	 * 检查注册用户名填写的资料是否重复
	 * URL: http://sso.jt.com/user/check/{param}/{type}
	 *		Type为类型，可选参数1 username、2 phone、3 email
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
		String md5Pass = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
		user.setPassword(md5Pass).setCreated(new Date()).setUpdated(new Date());
		int results = userMapper.insert(user);
		if (results > 0){
			return SysResult.success(user.getUsername());
		}
		return SysResult.fail();
	}
}
