package com.fidelis.k2.web.controller;

import java.util.HashSet;
import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fidelis.k2.bo.StudentBo;
import com.fidelis.k2.bo.TeacherBo;
import com.fidelis.k2.entity.Student;
import com.fidelis.k2.exceptions.CustomGenericException;
import com.fidelis.k2.model.StudentInfoWrapper;
import com.fidelis.k2.model.StudentDto;
import com.fidelis.k2.model.TeacherDto;

@Controller
@RequestMapping("/student")
public class StudentController {
	private static final Logger logger = Logger.getLogger(StudentController.class);
	
	@Autowired
	private StudentBo studentBo;
	@Autowired
	private TeacherBo teacherBo;
	
	@RequestMapping(value="/newstudent",method = RequestMethod.POST)
	public @ResponseBody StudentDto savestudent(@RequestBody @Valid Student student,BindingResult result ){
		if(result.hasErrors()){
     	   StringBuilder errorMessages = new StringBuilder();
     	   List<FieldError> errors = result.getFieldErrors();
     	   for (FieldError error : errors ) {
     		  errorMessages.append(error.getField() + " - " + error.getDefaultMessage());
     		  errorMessages.append("<br/>");
     	      logger.debug("The Object Student"+student+"has validation error");
     	   }
     	      throw new CustomGenericException("2", errorMessages.toString());
     	   
        }
		StudentDto studentDto=studentBo.savestudent(student);
		logger.info("Saved Student with id"+studentDto.getId());
		return studentDto;
	}
	@RequestMapping(value="/getstudent",method = RequestMethod.GET)
	public @ResponseBody StudentInfoWrapper getstudents(@RequestParam int studentId){
		StudentDto studentinfo= studentBo.getStudentById(studentId);
		if(studentinfo.getTeachers()==null){
			studentinfo.setTeachers(new HashSet<TeacherDto>());
		}
		List<TeacherDto> teacherstobeadded=teacherBo.notAddedTeachers(studentinfo.getTeachers());
		StudentInfoWrapper completeStudentInfo=new StudentInfoWrapper();
		completeStudentInfo.setStudentDto(studentinfo);
		completeStudentInfo.setTeachers_to_be_added(teacherstobeadded);
		logger.info("Returning Complete Info For Student id"+studentinfo.getId());
		return completeStudentInfo;
	}	
	@RequestMapping(value="/removeteacher",method = RequestMethod.GET)
	public @ResponseBody  TeacherDto removeteacher(@RequestParam int studentid,@RequestParam int teacherid){
		TeacherDto teacherDto=studentBo.removeteacher(studentid,teacherid);
		logger.info("Removed Teacher id "+teacherid+"For Student id "+studentid);
		return teacherDto;
	}
	@RequestMapping(value="/addteacherforstudent",method = RequestMethod.GET)
	public @ResponseBody  TeacherDto addteacherforstudent(@RequestParam int studentid,@RequestParam int teacherid){
	    TeacherDto teacherDto= studentBo.addteacherforstudent(studentid,teacherid);
	    logger.info("Added Teacher id "+teacherid+"For Student id "+studentid);
		return teacherDto;
	}
	
	
	
	/*@ExceptionHandler(Exception.class)
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
 
	}*/

}
