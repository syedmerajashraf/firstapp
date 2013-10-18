package com.fidelis.k2.web.controller;

import java.util.HashSet;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fidelis.k2.bo.StudentBo;
import com.fidelis.k2.bo.TeacherBo;
import com.fidelis.k2.entity.Teacher;
import com.fidelis.k2.exceptions.CustomGenericException;
import com.fidelis.k2.exceptions.TeacherValidationException;
import com.fidelis.k2.model.TeacherInfoWrapper;
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
	public @ResponseBody  TeacherDto saveteacher(@RequestBody Teacher teacher ) throws TeacherValidationException{

		TeacherDto addedteacher=null;
		if(teacher.getName().isEmpty())
			throw new CustomGenericException("1", "The Name Is Empty");
		if(teacher.getEmail().isEmpty())
			throw new CustomGenericException("2", "Hey!! Email Cant be blank");
	    addedteacher = teacherBo.saveteacher(teacher);
		return addedteacher;
	}
	
	@RequestMapping(value="/getteacher",method = RequestMethod.GET)
	public @ResponseBody TeacherInfoWrapper getteachers(@RequestParam int teacherId){
		TeacherDto teacherinfo= teacherBo.getTeacherById(teacherId);
		if(teacherinfo.getStudents()==null){
			teacherinfo.setStudents(new HashSet<StudentDto>());
		}
		List<StudentDto> studentstobeadded=studentBo.notAddedStudents(teacherinfo.getStudents());
		TeacherInfoWrapper completeTeacherInfo=new TeacherInfoWrapper();
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
	
	
	
	
	
	
	
	@ExceptionHandler(Exception.class)
    ResponseEntity<String> exceptionHandler(Exception ex) {
		System.out.println("exception for super handler called");
        return new ResponseEntity<String>(
                "Something Is Wrong As The Request Cant be completed",
                 HttpStatus.NOT_ACCEPTABLE );
    }
	@ExceptionHandler(CustomGenericException.class)
	public ModelAndView handleCustomException(CustomGenericException ex) {
		ModelAndView model = new ModelAndView("error");
		model.addObject("errCode", ex.getErrCode());
		model.addObject("errMsg", ex.getErrMsg());
		return model;
 
	}
}
