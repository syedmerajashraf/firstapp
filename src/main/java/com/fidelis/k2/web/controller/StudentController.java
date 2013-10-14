package com.fidelis.k2.web.controller;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import com.fidelis.k2.bo.StudentBo;
import com.fidelis.k2.bo.TeacherBo;
import com.fidelis.k2.entity.Student;
import com.fidelis.k2.entity.Teacher;
import com.fidelis.k2.model.CompleteStudentInfo;
import com.fidelis.k2.model.StudentDto;
import com.fidelis.k2.model.TeacherDto;

@Controller
@RequestMapping("/student")
public class StudentController {
	
	@Autowired
	private StudentBo studentBo;
	@Autowired
	private TeacherBo teacherBo;
	
	@RequestMapping(value="/newstudent",method = RequestMethod.POST)
	public @ResponseBody StudentDto savestudent(@RequestBody Student student ){
	
		StudentDto studentDto=studentBo.savestudent(student);
		return studentDto;
	}


	
	@RequestMapping(value="/getstudent",method = RequestMethod.GET)
	public @ResponseBody CompleteStudentInfo getstudents(@RequestParam int studentId){
		StudentDto studentinfo= studentBo.getStudentById(studentId);
		if(studentinfo.getTeachers()==null){
			studentinfo.setTeachers(new HashSet<TeacherDto>());
		}
		List<TeacherDto> teacherstobeadded=teacherBo.notAddedTeachers(studentinfo.getTeachers());
		CompleteStudentInfo completeStudentInfo=new CompleteStudentInfo();
		completeStudentInfo.setStudentDto(studentinfo);
		completeStudentInfo.setTeachers_to_be_added(teacherstobeadded);
		return completeStudentInfo;
	}	

	@RequestMapping(value="/removeteacher",method = RequestMethod.GET)
	public @ResponseBody  TeacherDto removeteacher(@RequestParam int studentid,@RequestParam int teacherid){
		TeacherDto teacherDto=studentBo.removeteacher(studentid,teacherid);
		return teacherDto;
	}

	@RequestMapping(value="/addteacherforstudent",method = RequestMethod.GET)
	public @ResponseBody  TeacherDto addteacherforstudent(@RequestParam int studentid,@RequestParam int teacherid){
	     
	    TeacherDto teacherDto= studentBo.addteacherforstudent(studentid,teacherid);
		
		return teacherDto;
		
	}
}
