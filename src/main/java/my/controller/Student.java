package my.controller;

import java.util.List;



public class Student {
private String name;


private String email;
private List<Integer> tid;



public Student(String name, String email, List<Integer> tid) {
	super();
	this.name = name;
	this.email = email;
	this.tid = tid;
}

public Student() {
	
}

public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}
public List<Integer> getTid() {
	return tid;
}
public void setTid(List<Integer> tid) {
	this.tid = tid;
}

}
