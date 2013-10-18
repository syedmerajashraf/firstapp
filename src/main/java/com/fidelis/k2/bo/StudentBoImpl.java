package com.fidelis.k2.bo;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fidelis.k2.dao.StudentDao;
import com.fidelis.k2.dao.StudentDaoImpl;
import com.fidelis.k2.dao.TeacherDao;
import com.fidelis.k2.entity.Student;
import com.fidelis.k2.entity.Teacher;
import com.fidelis.k2.model.StudentDto;
import com.fidelis.k2.model.TeacherDto;

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

	@Transactional
	@Override
	public TeacherDto addteacherforstudent(int studentId, int teacherId) {
		
		Student student = studentDao.findById(studentId);
		Teacher teacher = teacherDao.findById(teacherId);
		student.addTeacher(teacher);
		TeacherDto teacherDto=new TeacherDto().fillTeacherData(teacher);
		return teacherDto;
		
	}

	@Transactional
	@Override
	public TeacherDto removeteacher(int studentId, int teacherId) {
		Student student = studentDao.findById(studentId);
		Teacher teacher = teacherDao.findById(teacherId);
		student.getTeachers().remove(teacher);
		TeacherDto teacherDto=new TeacherDto().fillTeacherData(teacher);
		return teacherDto;
	}

	@Transactional
	@Override
	public List<StudentDto> notAddedStudents(Set<StudentDto> students) {
		int flag;
		List<Student> allstudentList=studentDao.findAll();
		List<StudentDto> studenttobeadded=new ArrayList<StudentDto>();
		for(Student student:allstudentList){
			 flag=0;
			 StudentDto temp_student=new StudentDto();
			 for(StudentDto studentDto:students){
				 if(studentDto.getId()==student.getId()){
					 flag=1;
					 break;
				}
		     }
			if(flag==0){
				temp_student.fillStudentData(student);
				studenttobeadded.add(temp_student);
				
			}
		 
	     
	     }
		return studenttobeadded;
	}
	
} 
