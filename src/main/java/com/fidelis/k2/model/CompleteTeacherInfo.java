package com.fidelis.k2.model;

import java.util.List;

public class CompleteTeacherInfo {
	private TeacherDto teacherDto;
	private List<StudentDto> students_to_be_added;

	public TeacherDto getTeacherDto() {
		return teacherDto;
	}
	public void setTeacherDto(TeacherDto teacherDto) {
		this.teacherDto = teacherDto;
	}
	public List<StudentDto> getStudents_to_be_added() {
		return students_to_be_added;
	}
	public void setStudents_to_be_added(List<StudentDto> students_to_be_added) {
		this.students_to_be_added = students_to_be_added;
	}
	
	
}
