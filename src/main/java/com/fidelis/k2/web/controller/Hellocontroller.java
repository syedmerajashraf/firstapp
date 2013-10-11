package com.fidelis.k2.web.controller;

import java.util.ArrayList;
import java.util.List;

import my.utility.ServiceUtility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fidelis.k2.bo.HelloService;
import com.fidelis.k2.bo.StudentBo;
import com.fidelis.k2.entity.Student;
import com.fidelis.k2.entity.Teacher;
import com.fidelis.k2.model.StudentDto;


@Controller
public class Hellocontroller {
	
@Autowired
private HelloService helloService;

@RequestMapping(value="/newteacher",method = RequestMethod.POST)
public @ResponseBody String saveteacher(@RequestBody final Teacher teacher){
/*System.out.println("hi"+teacher.getTname());
     helloService.saveteacher(teacher);*/
	return "Added";
}




}
