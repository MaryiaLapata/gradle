package com.epam.cdp.userManagement.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


//@Entity
//@Table(name = "groupp")
//@PrimaryKeyJoinColumn(name = "group_id")
//@JsonIdentityInfo(
//		  generator = ObjectIdGenerators.PropertyGenerator.class, 
//		  property = "id", scope = Group.class)
public class Group extends License implements Serializable {
	
	private static final long serialVersionUID = -371208780919552318L;
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy="groupList")
	private List<User> userList = new ArrayList<>();
	
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
