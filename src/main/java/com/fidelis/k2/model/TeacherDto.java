package com.fidelis.k2.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fidelis.k2.entity.Student;
import com.fidelis.k2.entity.Teacher;

public class TeacherDto {
	
	public TeacherDto(){}
	public TeacherDto(Teacher teacher){
		fillTeacherDataWithStudents(teacher);
	}
	private Integer id;
	private String name;
	private String email;
	private Set<StudentDto> students;
	private List<String> notifications;
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
	
	public List<String> getNotifications() {
		return notifications;
	}
	public void setNotifications(List<String> notifications) {
		this.notifications = notifications;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Set<StudentDto> getStudents() {
		return students;
	}
	public void setStudents(Set<StudentDto> students) {
		this.students = students;
	}
	
	public TeacherDto fillTeacherData(Teacher teacher){
		this.id = teacher.getId();
		this.name = teacher.getName();
		this.email = teacher.getEmail();
		return this;
	}
	
	public TeacherDto fillTeacherDataWithStudents(Teacher teacher){
		this.id = teacher.getId();
		this.name = teacher.getName();
		this.email = teacher.getEmail();
		if(teacher.getStudents() != null && teacher.getStudents().size()>0){
			students = new HashSet<StudentDto>();
			for(Student student : teacher.getStudents()){
				students.add((new StudentDto()).fillStudentData(student));
			}
		}
		return this;
	}
}
