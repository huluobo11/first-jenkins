package com.example.demo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.demo.entity.Student;

public interface StudentDao extends  PagingAndSortingRepository<Student, Integer>{
	
	
	
	 Page<Student> findAll(Pageable pageable);
	
	
	 
	 
	 
	 
	 
	 
	//根据班级id查学生信息
	List<Student> findByClazzId(Integer classId);
	// TODO 链表查询user表和banckCat表中 根据账户编号查询出user表中role和banckCat表中idCard
}
