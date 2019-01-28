package com.example.demo.service;

import org.springframework.data.domain.Page;

import com.example.demo.entity.Student;

public interface StudentService {
	void get() throws Exception;
	
	/**
	 *     分页查询 
	 * @param pageNumber  页码
	 * @param pageSize  每页的记录数
	 * @return
	 */
	Page<Student> getStudentPage(int pageNumber,int pageSize);
}
