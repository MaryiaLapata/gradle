package com.epam.cdp.userManagement.service;

import java.util.List;

import com.epam.cdp.userManagement.exception.NoSuchModelException;
import com.epam.cdp.userManagement.model.Group;

public interface IGroupService {

	List<Group> getAll();
	
	Group getById(String groupId);
	
	String create(Group newGroup);

	void delete(String id) throws NoSuchModelException;
	
	Group update(String id, Group newGroup);
	
	List<Group> getByUserId(String userId);
}
