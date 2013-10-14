package com.fidelis.k2.bo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fidelis.k2.dao.StudentDao;
import com.fidelis.k2.dao.TeacherDao;
import com.fidelis.k2.entity.Student;
import com.fidelis.k2.entity.Teacher;
import com.fidelis.k2.model.StudentDto;
import com.fidelis.k2.model.TeacherDto;

@Service
public class TeacherBoImpl implements TeacherBo{

	@Autowired
	private StudentBo studentBo;
	@Autowired
	private StudentDao studentDao;
	@Autowired
	private TeacherDao teacherDao;
	@Transactional
	@Override
	public List<TeacherDto> notAddedTeachers(Set<TeacherDto> teachers){
		int flag;
		List<Teacher> allteacherList=teacherDao.findAll();
		List<TeacherDto> teachertobeadded=new ArrayList<TeacherDto>();
		for(Teacher teacher:allteacherList){
			 flag=0;
			 TeacherDto temp_teacher=new TeacherDto();
			 for(TeacherDto teacherDto:teachers){
				 if(teacherDto.getId()==teacher.getId()){
					 flag=1;
					 break;
				}
		     }
			if(flag==0){
				temp_teacher.fillTeacherData(teacher);
				teachertobeadded.add(temp_teacher);
				
			}
		 
	     
	     }
		return teachertobeadded;
	}
	
	@Transactional
	@Override
	public List<TeacherDto> getAllTeachers() {
		List<Teacher> teacherList = teacherDao.findAll();
		List<TeacherDto> teacherDtoList= new ArrayList<TeacherDto>();
    	for(Teacher teacher:teacherList){
    		TeacherDto teacherDto=new TeacherDto(teacher);
    		teacherDtoList.add(teacherDto);
    	}
		return teacherDtoList;
		
	}
	@Transactional
	@Override
	public TeacherDto saveteacher(Teacher teacher) {
		
		teacherDao.save(teacher);
		return new TeacherDto(teacher);
		
		
	}
}
