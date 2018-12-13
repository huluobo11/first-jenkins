package com.example.demo.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Student;

@RestController
public class RedisController {

	@Autowired
	private IVipRedisService iVipRedisService;
	
	@GetMapping("/redis/test")
	public List<Object> getStudent() {
		
		String str = iVipRedisService.getStr("user");
		Student student = new Student();
		student.setId(12);
		student.setName("小明");
		student.setSex("男");
		iVipRedisService.setObj("admin", student);
		return Arrays.asList(str);
	}
	
}
