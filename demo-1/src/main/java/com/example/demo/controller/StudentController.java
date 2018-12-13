package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.BaimoResult;
import com.example.demo.entity.BaimoResultEnum;
import com.example.demo.entity.Student;

@RestController
@RequestMapping("student")
public class StudentController {
	
	@Value("${my.url}")
	private String url;


	@GetMapping("/get")
	public String getStudent() {
		
		return " get -> " + url;
	}
	
	// 你的接口的 所有返回值 类型   都用  BaimoResult.
	@GetMapping("/test")
	public BaimoResult test() {
		// 你自己的代码 。。。
		
		// 如果 这里是 这个手机尚未注册,可以进行注册  ,   你在枚举类中找一下, 找到了下面这个  
		//   SMS_ALLOW_REGISTER(40601,"这个手机尚未注册，可以进行注册","SUCCESS"),
		
		//你的 返回值 就这样写。
		return  new BaimoResult(BaimoResultEnum.SMS_ALLOW_REGISTER);
	}
	
	// 你的接口的 所有返回值 类型   都用  BaimoResult.
		@GetMapping("/te")
		public BaimoResult te() {
			
			Student student = new Student();
			student.setId(1);
			student.setName("小明");
			student.setSex("男");
			return  new BaimoResult(BaimoResultEnum.SMS_ALLOW_REGISTER, student);
		}
}
