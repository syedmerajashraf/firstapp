package com.fidelis.k2.dao;

import org.springframework.stereotype.Repository;

import com.fidelis.k2.entity.Student;

@Repository
public class StudentDaoImpl  extends BaseDaoImpl<Student> implements StudentDao{

	public StudentDaoImpl() {
		super(Student.class);
	}

	
}
