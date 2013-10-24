package com.fidelis.k2.entity;

import java.util.Set;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Transient;


@Entity
@DiscriminatorValue("Coach")
public class Coach extends User{
	
	public Coach() {}
	public Coach(String name, String email) {
		super(name, email);
	}

	@Transient
	@OneToMany(mappedBy="coach")
    private Set<Student> students;
	
	public Set<Student> getStudents() {
		return students;
	}
	public void setStudents(Set<Student> students) {
		this.students = students;
	}
	
}
