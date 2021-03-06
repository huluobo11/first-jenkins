package com.example.demo.entity;

import java.util.Date;

import javax.persistence.Table;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
@Table(name = "学生表的表名")
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;

	private String sex;
	
	private Date birthday;
	//班级
	@ManyToOne(cascade =CascadeType.ALL)
	@JoinColumn(name = "class_id")
	private Clazz clazz;
	
	// 学生表中的一个class_id字段，对应班级表中的id
	// 一个学生只有一个班级，一个班级 中有多个学生
}
