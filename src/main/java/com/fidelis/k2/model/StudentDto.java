package com.fidelis.k2.model;

import java.util.HashSet;
import java.util.Set;

import com.fidelis.k2.entity.Student;
import com.fidelis.k2.entity.Teacher;


public class StudentDto {

	public StudentDto(){}
	public StudentDto(Student student){
		fillStudentDataWithTeachers(student);
	}
	
	private Integer id;
	private String name;
	private String email;
	private Set<TeacherDto> teachers;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Set<TeacherDto> getTeachers() {
		return teachers;
	}
	
	public StudentDto fillStudentData(Student student){
		this.id = student.getId();
		this.name = student.getName();
		this.email = student.getEmail();
		return this;
	}
	
	public StudentDto fillStudentDataWithTeachers(Student student){
		this.id = student.getId();
		this.name = student.getName();
		this.email = student.getEmail();
		if(student.getTeachers() != null && student.getTeachers().size()>0){
			teachers = new HashSet<TeacherDto>();
			for(Teacher teacher : student.getTeachers()){
				teachers.add((new TeacherDto()).fillTeacherData(teacher));
			}
		}
		return this;
	}
	
}
