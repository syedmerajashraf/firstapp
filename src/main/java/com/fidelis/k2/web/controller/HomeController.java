package com.fidelis.k2.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fidelis.k2.bo.StudentBo;
import com.fidelis.k2.bo.TeacherBo;
import com.fidelis.k2.entity.Student;
import com.fidelis.k2.model.StudentDto;

@Controller
@RequestMapping("/home")
public class HomeController {
	
	@Autowired
	private StudentBo studentBo;
	@Autowired
	private TeacherBo teacherBo;

	@RequestMapping(method = RequestMethod.GET)
	public String hello(ModelMap model){
		List<StudentDto> studentDtolist=studentBo.getAllStudents();
		model.addAttribute("studentlist",studentDtolist);
		model.addAttribute("message","hello world");
		return "hello";
	}
}
