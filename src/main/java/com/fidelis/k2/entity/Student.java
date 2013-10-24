package com.fidelis.k2.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
/*@NamedQueries({
	@NamedQuery(name="Student.FindAll",
		        query = "SELECT s FROM Student s"),
    @NamedQuery(name="Student.FindById",
                query="SELECT s FROM Student s WHERE s.id= :studentId"),
	})*/
@Entity
@Table(name="user")
@DiscriminatorValue("Student")
public class Student extends User{

	public Student() {super();}

	public Student(String name, String email) {
		super(name,email);
	}
	@ManyToMany
	@JoinTable(name="student_teacher_mapping", joinColumns={@JoinColumn(name="student_id")}, inverseJoinColumns={@JoinColumn(name="teacher_id")})
	private Set<Teacher> teachers;
	@ManyToOne
    @JoinColumn(name="coach_id")
	@Transient
    private Coach coach;
	
	public Set<Teacher> getTeachers() {
		return teachers;
	}
	public void setTeachers(Set<Teacher> teachers) {
		this.teachers = teachers;
	}
	public Coach getCoach() {
		return coach;
	}
	public void setCoach(Coach coach) {
		this.coach = coach;
	}
	public void addTeacher(Teacher teacher){
		if(teachers == null){
			teachers = new HashSet<Teacher>();
		}
		teachers.add(teacher);
	}
}
