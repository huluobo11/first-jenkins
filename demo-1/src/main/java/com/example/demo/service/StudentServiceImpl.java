package com.example.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.example.demo.entity.BaimoResultEnum;
import com.example.demo.entity.Student;
import com.example.demo.exception.UserException;
import com.example.demo.repository.StudentDao;

@Service
public class StudentServiceImpl implements StudentService{

	@Autowired
	private StudentDao studentDao;


	@Override
	public Page<Student> getStudentPage(int pageNumber, int pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize, Direction.DESC, "要排序的字段的字段名");
		Page<Student> page = studentDao.findAll(pageable);
		return page;
	}
	
	
	
	
	
	
	
	
	
	@Override
	public void get() throws Exception {
		int i = 1;
		throw new UserException(BaimoResultEnum.IS_NOT_MOBILE);
	}

}
