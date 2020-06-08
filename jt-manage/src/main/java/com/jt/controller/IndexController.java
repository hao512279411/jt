package com.jt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	
	@RequestMapping("/page/{moduleName}")
	public String module(@PathVariable 	String moduleName) {
		System.out.println("访问页面："+moduleName);
		return moduleName;
	}

	@RequestMapping("/test")
	public String a() {
		return "test/easyui-tree-url";
	}
}
