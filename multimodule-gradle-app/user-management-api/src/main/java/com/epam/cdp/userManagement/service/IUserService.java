package com.epam.cdp.userManagement.service;

import java.util.List;

import com.epam.cdp.userManagement.exception.NoSuchModelException;
import com.epam.cdp.userManagement.model.User;

public interface IUserService {

	List<User> getAll();
	
	User getById(String userId) throws NoSuchModelException;
	
	String create(User newUser);
	
	User update(String id, User newUser);
	
	void delete(String id) throws NoSuchModelException;
	
	void assignGroup(String userId, String groupId);
	
	void assignPermission(String userId, String permissionId);
}
