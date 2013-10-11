package com.fidelis.k2.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="student")
public class Student {

	public Student() {}

	public Student(String name, String email) {
		this.name = name;
		this.email = email;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="sid")
	private Integer id;
	@Column(name="sname")
	private String name;
	@Column(name="semail")
	private String email;
	@ManyToMany
	@JoinTable(name="student_teacher_mapping", joinColumns={@JoinColumn(name="sid")}, inverseJoinColumns={@JoinColumn(name="tid")})
	private Set<Teacher> teachers;

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

	public Set<Teacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(Set<Teacher> teachers) {
		this.teachers = teachers;
	}

	public void addTeacher(Teacher teacher){
		if(teachers == null){
			teachers = new HashSet<Teacher>();
		}
		teachers.add(teacher);
	}
}
