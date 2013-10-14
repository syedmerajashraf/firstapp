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
import com.fidelis.k2.entity.Teacher;
import com.fidelis.k2.model.CompleteTeacherInfo;
import com.fidelis.k2.model.StudentDto;
import com.fidelis.k2.model.TeacherDto;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

	@Autowired
	private TeacherBo teacherBo;
	
	@Autowired
	private StudentBo studentBo;
	
	
	@RequestMapping(value="/newteacher",method = RequestMethod.POST)
	public @ResponseBody TeacherDto savestudent(@RequestBody Teacher teacher ){

		TeacherDto addedteacher=teacherBo.saveteacher(teacher);
		return addedteacher;
	}
	
	@RequestMapping(value="/getteacher",method = RequestMethod.GET)
	public @ResponseBody CompleteTeacherInfo getteachers(@RequestParam int teacherId){
		TeacherDto teacherinfo= teacherBo.getTeacherById(teacherId);
		if(teacherinfo.getStudents()==null){
			teacherinfo.setStudents(new HashSet<StudentDto>());
		}
		List<StudentDto> studentstobeadded=studentBo.notAddedStudents(teacherinfo.getStudents());
		CompleteTeacherInfo completeTeacherInfo=new CompleteTeacherInfo();
		completeTeacherInfo.setTeacherDto(teacherinfo);
		completeTeacherInfo.setStudents_to_be_added(studentstobeadded);
		return completeTeacherInfo;
	}	
	@RequestMapping(value="/addstudentforteacher",method = RequestMethod.GET)
	public @ResponseBody  StudentDto addstudentforteacher(@RequestParam int studentid,@RequestParam int teacherid){
	     
	    StudentDto studentDto= teacherBo.addstudentforteacher(studentid,teacherid);
		
		return studentDto;
		
	}
	@RequestMapping(value="/removestudent",method = RequestMethod.GET)
	public @ResponseBody  StudentDto removestudent(@RequestParam int studentid,@RequestParam int teacherid){
		StudentDto studentDto=teacherBo.removestudent(studentid,teacherid);
		return studentDto;
	}

}
