package com.epam.cdp.userManagement.model;

import java.util.List;

public class Group extends License{
	
	private List<User> userList;
	
	public Group() {}
	
	public Group(String name) {
		this.name = name;
	}
	
	public List<User> getUserList() {
		return userList;
	}
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
}
