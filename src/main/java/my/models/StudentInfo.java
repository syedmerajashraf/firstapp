package my.models;

import java.util.List;

import my.controller.Student;
import my.controller.Teacher;

public class StudentInfo {

	private Student student;
	private List<Teacher> teacher;
	private List<Teacher> teacher_to_be_added;
	
	
	public List<Teacher> getTeacher_to_be_added() {
		return teacher_to_be_added;
	}
	
	public void setTeacher_to_be_added(List<Teacher> teacher_to_be_added) {
		this.teacher_to_be_added = teacher_to_be_added;
	}
	
	public Student getStudent() {
		return student;
	}
	
	public void setStudent(Student student) {
		this.student = student;
	}
	
	public List<Teacher> getTeacher() {
		return teacher;
	}
	
	public void setTeacher(List<Teacher> teacher) {
		this.teacher = teacher;
	}
	
}
