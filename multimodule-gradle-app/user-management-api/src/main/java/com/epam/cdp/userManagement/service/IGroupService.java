package com.epam.cdp.userManagement.service;

import java.util.List;

import com.epam.cdp.userManagement.exception.NoSuchModelException;
import com.epam.cdp.userManagement.model.Group;

public interface IGroupService {

	List<Group> getAll();
	
	Group getById(long groupId);
	
	long create(Group newGroup);

	void delete(long id) throws NoSuchModelException;
	
	Group update(long id, Group newGroup);
	
	List<Group> getByUserId(long userId);
}
