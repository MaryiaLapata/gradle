package com.epam.cdp.userManagement.model;

import java.util.ArrayList;
import java.util.List;

public class License {
	
	protected long id;
	protected String name;
	protected List<Permission> permissionList = new ArrayList<>();
	
	public License(){}
	
	public License(String name) {
		this.name = name;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public List<Permission> getPermissionList() {
		return permissionList;
	}
	public void setPermissionList(List<Permission> permissionList) {
		this.permissionList = permissionList;
	}	
}
