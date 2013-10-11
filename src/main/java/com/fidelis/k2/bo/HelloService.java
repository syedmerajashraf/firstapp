package com.fidelis.k2.bo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.fidelis.k2.entity.Student;
import com.fidelis.k2.entity.Teacher;
import com.fidelis.k2.model.StudentDto;


public class HelloService {

	@PersistenceContext
	private EntityManager em ;
	
	@Transactional
	public void saveteacher(Teacher teacher) {
		 em.persist(teacher);
	}
    
    @Transactional
	public void teacherforstudent(int sid, int tid) {
		Student student=em.find(Student.class, sid);
		Teacher teacher=em.find(Teacher.class, tid);
		student.getTeachers().add(teacher);
		
	}
	
}
