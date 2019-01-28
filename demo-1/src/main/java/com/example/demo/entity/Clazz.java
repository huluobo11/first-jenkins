package com.example.demo.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="班级表的表名")
public class Clazz {
	
	// 班级id
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	// 班级名称
	private String name;
	
	// 班级中的全部学生
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "class_id")
	private List<Student> students;
	
	// 学生表中的一个class_id字段，对应班级表中的id
	// 一个学生只有一个班级，一个班级 中有多个学生
}
