package com.fidelis.k2.web.controller;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.fidelis.k2.bo.StudentBo;
import com.fidelis.k2.bo.TeacherBo;
import com.fidelis.k2.dao.StudentDao;
import com.fidelis.k2.model.StudentDto;
import com.fidelis.k2.model.TeacherDto;

@Controller
@RequestMapping("/home")
public class HomeController {
	private static final Logger logger = Logger.getLogger(HomeController.class);
	@Autowired
	private StudentBo studentBo;
	@Autowired
	private TeacherBo teacherBo;
	@Autowired
	private StudentDao studentDao;
	@PersistenceContext
	protected EntityManager em;
	@RequestMapping(method = RequestMethod.GET)
	public String hello(ModelMap model){
		List<StudentDto> studentDtolist=studentBo.getAllStudents();
		List<TeacherDto> teacherDtolist=teacherBo.getAllTeachers();
		model.addAttribute("studentlist",studentDtolist);
		model.addAttribute("teacherlist",teacherDtolist);
		logger.info(model+"meraj");
		if(logger.isInfoEnabled()){
			System.out.println("hii");
			logger.info(model+"meraj");
		}
		return "hello";
	}
	
}
