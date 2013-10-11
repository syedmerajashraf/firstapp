package com.fidelis.k2.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fidelis.k2.dao.StudentDao;
import com.fidelis.k2.dao.TeacherDao;
import com.fidelis.k2.entity.Student;
import com.fidelis.k2.model.StudentDto;

@Service
public class StudentBoImpl implements StudentBo{

	@Autowired
	private TeacherBo teacherBo;
	@Autowired
	private StudentDao studentDao;
	@Autowired
	private TeacherDao teacherDao;
	
	@Transactional
	@Override
	public StudentDto savestudent(Student student) {
		studentDao.save(student);
		return new StudentDto(student);
	}
	
	@Transactional
	@Override
	public List<StudentDto> getAllStudents() {
    	List<Student> studentList = studentDao.findAll();
    	List<StudentDto> studentDtoList= new ArrayList<StudentDto>();
    	for(Student student:studentList){
    		StudentDto studentDto=new StudentDto(student);
    		studentDtoList.add(studentDto);
    	}
		return studentDtoList;
	}
	
	@Transactional
	@Override
	public StudentDto getStudentById(Integer studentId) {
    	Student student = studentDao.findById(studentId);
    	StudentDto studentDto = new StudentDto(student);
		return studentDto;
	}
}