package com.fidelis.k2.bo;

import java.util.List;
import java.util.Set;

import com.fidelis.k2.entity.Teacher;
import com.fidelis.k2.model.StudentDto;
import com.fidelis.k2.model.TeacherDto;

public interface TeacherBo  {
	List<TeacherDto> getAllTeachers();

	List<TeacherDto> notAddedTeachers( Set<TeacherDto> teachers);
}
