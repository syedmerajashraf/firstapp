package my.controller;

import java.util.ArrayList;
import java.util.List;

import my.models.StudentInfo;
import my.service.HelloService;
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


@Controller
public class Hellocontroller {
	private static int studentcount=4;
	private static int teachercount=4;
@Autowired
private HelloService helloService;

@Autowired
private ByeController byeController;

@Autowired
private ServiceUtility utility;

@RequestMapping(value="/Welcome", method = RequestMethod.GET)
public String hello(ModelMap model){
	model.addAttribute("message", "hello World");
	model.addAttribute("student_db",utility.studentdb);
	model.addAttribute("teacher_db",utility.teacherdb);
	return "hello";
}

@RequestMapping(value="/newteacher",method = RequestMethod.POST)
public @ResponseBody String saveteacher(@RequestBody final Teacher teacher){
System.out.println("hi"+teacher.getName());
	
	return "Added";
}


@RequestMapping(value="/newstudent",method = RequestMethod.POST)
public @ResponseBody String savestudent(@RequestBody Student st ){
System.out.println("hi" + st.getName());
List <Integer> teacherlist=new ArrayList<Integer>();
st.setTid(teacherlist);
utility.studentdb.put(studentcount, st);
studentcount++;
	return "Added";
}

@RequestMapping(value="/newstudent",method = RequestMethod.GET)
public @ResponseBody Student getstudent(@RequestParam int id){
     Student s = null;
	 Student s1=new Student();
	 s1.setName("meraj");
	 s1.setEmail("xyz@gmail.com");
	 
	 Student s2=new Student();
	 s2.setName("ashraf");
	 s2.setEmail("xyzzzzz00");
	 if(id==1)
		 s=s1;
	 else if(id==2)
		  s=s2;
	 
	 
	return s;
}	
	
@RequestMapping(value="/getstudent",method = RequestMethod.GET)
public @ResponseBody  StudentInfo getstudents(@RequestParam int id){
	StudentInfo info=helloService.getstudentbyid(id);
	return info;
}	

@RequestMapping(value="/removeteacher",method = RequestMethod.GET)
public @ResponseBody  String removeteacher(@RequestParam int id,@RequestParam String email){
	helloService.removeteacherbyemail(id,email);
	return "removed";
}

@RequestMapping(value="/addteacherforstudent",method = RequestMethod.GET)
public @ResponseBody  Teacher addteacherforstudent(@RequestParam int id,@RequestParam String email){
	helloService.teacherforstudent(id,email);
	return null;
}


}
