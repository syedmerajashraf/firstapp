package com.fidelis.k2.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="user")
@DiscriminatorValue("Teacher")
public class Teacher extends User {
	
	public Teacher() {super();}
	public Teacher(String name, String email) {
		super(name,email);
	}
   
	@ManyToMany//(mappedBy="teachers")
	@JoinTable(name="student_teacher_mapping", joinColumns={@JoinColumn(name="teacher_id")}, inverseJoinColumns={@JoinColumn(name="student_id")})
	private Set<Student> students;
	
	public Set<Student> getStudents() {
		return students;
	}
	public void setStudents(Set<Student> students) {
		this.students = students;
	}
	
	public void addStudent(Student student){
		if(students == null){
			students = new HashSet<Student>();
		}
		students.add(student);
	}
}
