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
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="teacher")
public class Teacher {
	
	public Teacher() {}
	public Teacher(String name, String email) {
		this.name = name;
		this.email = email;
	}
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="tid")
    private Integer id;
    @NotEmpty
    @NotNull
    @Column(name="tname")
    private String name;
    
    @NotEmpty
    @Email
    @NotNull
    @Column(name="temail")
	private String email;
	@ManyToMany//(mappedBy="teachers")
	@JoinTable(name="student_teacher_mapping", joinColumns={@JoinColumn(name="tid")}, inverseJoinColumns={@JoinColumn(name="sid")})
	private Set<Student> students;
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
