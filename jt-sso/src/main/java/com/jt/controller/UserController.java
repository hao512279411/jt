package com.jt.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.jt.pojo.User;
import com.jt.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.jt.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;

	/**
	 * 检查注册用户名填写的资料是否重复
	 * URL: http://sso.jt.com/user/check/{param}/{type}
	 *		Type为类型，可选参数1 username、2 phone、3 email
	 */
	@RequestMapping("/check/{param}/{type}")
	public JSONPObject checkUserRepeat(@PathVariable String param, @PathVariable Byte type,String callback){
		SysResult sysResult = userService.checkUserRepeat(param, type);
		return new JSONPObject(callback,sysResult);

	}

	/**
	 * 提交注册信息  新增单个用户
	 * post
	 * URL: http://sso.jt.com/user/register
	 */
	@PostMapping("/register")
	public SysResult doRegister(User user){
		SysResult sysResult=userService.doRegister(user);
		return sysResult;
	}

	/**
	 * 通过JSONP 查询当前用户 uuid是否存在缓存  存在的话就是已登录客户
	 * url: http://sso.jt.com/user/query/356c5420-efc7-48ca-acd7-bd32437a58e4
	 * GET
	 */
	@GetMapping("/query/{uuid}")
	public JSONPObject queryUUID(@PathVariable String uuid,String callback){
		SysResult sysResult = userService.queryUUID(uuid);
		return new JSONPObject(callback,sysResult);
	}




}
