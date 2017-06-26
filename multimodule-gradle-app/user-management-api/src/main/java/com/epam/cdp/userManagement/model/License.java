package com.epam.cdp.userManagement.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.data.annotation.Id;

//@Entity
//@Table
//@Inheritance(strategy = InheritanceType.JOINED)
public class License implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="license_id")
	@GeneratedValue
	protected String id;
	@Column
	protected String name;
	
	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "lisence_permission", 
    		   joinColumns = @JoinColumn(name = "lisence_id"), 
    		   inverseJoinColumns = @JoinColumn(name = "permission_id"))
	protected List<Permission> permissionList = new ArrayList<>();
	
	public License(){}
	
	public License(String name) {
		this.name = name;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
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
