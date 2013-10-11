package com.fidelis.k2.dao;

import org.springframework.stereotype.Repository;

import com.fidelis.k2.entity.Teacher;

@Repository
public class TeacherDaoImpl extends BaseDaoImpl<Teacher> implements TeacherDao{

	public TeacherDaoImpl() {
		super(Teacher.class);
	}

}
