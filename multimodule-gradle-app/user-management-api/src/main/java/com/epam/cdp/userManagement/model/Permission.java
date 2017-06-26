package com.epam.cdp.userManagement.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;

//@Entity
//@Table
public class Permission implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="permission_id")
	@GeneratedValue
	private String id;
	@NotEmpty
	@Column
	private String object;
	@NotEmpty
	@Pattern(regexp = "[0-1]{3}")
	@Column
	private String actionType;
	
	//@ManyToMany(mappedBy="permissionList")
	//private List<User> userList = new ArrayList<>();
	
	public Permission(){}
	
	public Permission(String id, String object, String actionType) {
		this.id = id;
		this.object = object;
		this.actionType = actionType;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getObject() {
		return object;
	}
	public void setObject(String object) {
		this.object = object;
	}
	public String getActionType() {
		return actionType;
	}
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
	
}
