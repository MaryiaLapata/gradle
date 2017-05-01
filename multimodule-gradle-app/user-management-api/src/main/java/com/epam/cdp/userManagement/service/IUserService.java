package com.epam.cdp.userManagement.service;

import java.util.List;

import com.epam.cdp.userManagement.exception.NoSuchModelException;
import com.epam.cdp.userManagement.model.User;

public interface IUserService {

	List<User> getAll();
	
	User getById(long userId) throws NoSuchModelException;
	
	long create(User newUser);
	
	User update(long id, User newUser);
	
	void delete(long id) throws NoSuchModelException;
	
	void assignGroup(long userId, long groupId);
	
	void assignPermission(long userId, long permissionId);
}
