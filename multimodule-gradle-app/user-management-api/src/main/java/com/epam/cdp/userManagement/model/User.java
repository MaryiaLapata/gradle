package com.epam.cdp.userManagement.model;

import java.util.List;

public class User extends License {
	
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private Address address;
	private List<Group> groupList;
	
	public User(){};
	
	public User(String firstName, String lastName, String email, String phone, Address address){
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.address = address;
	};	
	
	public User(long id, String firstName, String lastName, String email, String phone, Address address){
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.address = address;
	};	
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Group> getGroupList() {
		return groupList;
	}

	public void setGroupList(List<Group> groupList) {
		this.groupList = groupList;
	}
}
