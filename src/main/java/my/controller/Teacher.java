package my.controller;

import java.util.List;



public class Teacher {

	private String name;
	private String email;
	private List<Integer> sids;
	
	
	
	public List<Integer> getSids() {
		return sids;
	}

	public void setSids(List<Integer> sids) {
		this.sids = sids;
	}

	public Teacher() {
		
	}
	
	
	
	
	
	public Teacher(String name, String email, List<Integer> sids) {
		super();
		this.name = name;
		this.email = email;
		this.sids = sids;
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

}
