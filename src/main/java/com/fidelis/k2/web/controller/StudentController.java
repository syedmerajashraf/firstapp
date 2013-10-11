package com.fidelis.k2.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fidelis.k2.bo.HelloService;
import com.fidelis.k2.bo.StudentBo;
import com.fidelis.k2.bo.TeacherBo;
import com.fidelis.k2.entity.Student;
import com.fidelis.k2.entity.Teacher;
import com.fidelis.k2.model.StudentDto;
import com.fidelis.k2.model.TeacherDto;

@Controller
@RequestMapping("/student")
public class StudentController {
	@Autowired
	private HelloService helloService;
	
	@Autowired
	private StudentBo studentBo;
	@Autowired
	private TeacherBo teacherBo;
	
@RequestMapping(value="/newstudent",method = RequestMethod.POST)
public @ResponseBody String savestudent(@RequestBody Student student ){

	studentBo.savestudent(student);
	return "Added";
}


	
@RequestMapping(value="/getstudent",method = RequestMethod.GET)
public @ResponseBody  StudentDto getstudents(@RequestParam int studentId){
	StudentDto studentinfo= studentBo.getStudentById(studentId);
	List<TeacherDto> teachertobeadded=teacherBo.notAddedTeachers(studentinfo.getTeachers());
	System.out.println(teachertobeadded.size()+"  meraj");
	return studentinfo;
}	

@RequestMapping(value="/removeteacher",method = RequestMethod.GET)
public @ResponseBody  String removeteacher(@RequestParam int id,@RequestParam String email){
	//helloService.removeteacherbyemail(id,email);
	return "removed";
}

@RequestMapping(value="/addteacherforstudent",method = RequestMethod.GET)
public @ResponseBody  Teacher addteacherforstudent(@RequestParam int sid,@RequestParam int tid){
	helloService.teacherforstudent(sid,tid);
	
	return null;
	
}
}
