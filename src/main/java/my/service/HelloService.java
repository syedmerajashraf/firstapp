package my.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import my.controller.Teacher;
import my.models.StudentInfo;
import my.utility.ServiceUtility;


public class HelloService {
	@Autowired
	private ServiceUtility utility;
	public StudentInfo getstudentbyid(int id) {
		StudentInfo studentinfo=new StudentInfo();
		List<Teacher> teacherlist=new ArrayList<Teacher>();
		List<Teacher> teachertobeaddedlist=new ArrayList<Teacher>();
		int flag;
				studentinfo.setStudent(utility.studentdb.get(id));
				if(studentinfo.getStudent().getTid()!=null){
			for(Integer tid:studentinfo.getStudent().getTid()){
				teacherlist.add(utility.teacherdb.get(tid));
			}
			for(Integer tid: utility.teacherdb.keySet()){
				flag=0;
				for(Integer stid: studentinfo.getStudent().getTid()){
					if(stid==tid){
						flag=1;
					}
				}
					if(flag==1){
						continue;
					}
					else{
						teachertobeaddedlist.add(utility.teacherdb.get(tid));
					}
				
				
			}
				}
			studentinfo.setTeacher(teacherlist);
			studentinfo.setTeacher_to_be_added(teachertobeaddedlist);
		    
		return studentinfo;
	}
	public void removeteacherbyemail(int id, String email) {
		
		int tid_to_be_removed=0;
		for(Integer tid: utility.teacherdb.keySet()){
			if((utility.teacherdb.get(tid).getEmail()).equals(email)){
				tid_to_be_removed=tid;
			};
			
		}
		
		utility.studentdb.get(id).getTid().remove(new Integer(tid_to_be_removed));
		
		
	}
	public void teacherforstudent(int id, String email) {
		
		int tid_to_be_added=0;
		for(Integer tid: utility.teacherdb.keySet()){
			if((utility.teacherdb.get(tid).getEmail()).equals(email)){
				tid_to_be_added=tid;
			};
			
		}
		utility.studentdb.get(id).getTid().add(tid_to_be_added);
		
	}

}
