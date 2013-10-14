package com.fidelis.k2.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fidelis.k2.bo.StudentBo;
import com.fidelis.k2.bo.TeacherBo;
import com.fidelis.k2.entity.Student;
import com.fidelis.k2.entity.Teacher;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

	@Autowired
	private TeacherBo teacherBo;
	
	@Autowired
	private StudentBo studentBo;
	
	
	@RequestMapping(value="/newteacher",method = RequestMethod.POST)
	public @ResponseBody String savestudent(@RequestBody Teacher teacher ){

		teacherBo.saveteacher(teacher);
		return "Added";
	}

}
