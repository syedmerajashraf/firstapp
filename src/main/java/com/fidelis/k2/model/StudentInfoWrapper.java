package com.fidelis.k2.model;

import java.util.List;

public class StudentInfoWrapper {
	
	
	private StudentDto studentDto;
	private List<TeacherDto> teachers_to_be_added;
	public StudentDto getStudentDto() {
		return studentDto;
	}
	public void setStudentDto(StudentDto studeDto) {
		this.studentDto = studeDto;
	}
	public List<TeacherDto> getTeachers_to_be_added() {
		return teachers_to_be_added;
	}
	public void setTeachers_to_be_added(List<TeacherDto> teachers_to_be_added) {
		this.teachers_to_be_added = teachers_to_be_added;
	}

}
