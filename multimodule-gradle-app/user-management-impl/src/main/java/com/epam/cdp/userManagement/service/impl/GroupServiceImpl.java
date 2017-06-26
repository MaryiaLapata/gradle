package com.epam.cdp.userManagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.cdp.userManagement.dao.GroupRepository;
import com.epam.cdp.userManagement.exception.NoAffectedRowsDAOException;
import com.epam.cdp.userManagement.exception.NoSuchModelException;
import com.epam.cdp.userManagement.model.Group;
import com.epam.cdp.userManagement.service.IGroupService;

//@Component
public class GroupServiceImpl implements IGroupService{
	
	@Autowired
	private GroupRepository groupRepository;
	//@Autowired
	//private UserRepository userRepo;

	@Override
	public List<Group> getAll() {
		return groupRepository.getAll();
	}

	@Override
	public Group getById(String groupId) {
		Group group = groupRepository.getById(groupId);
		//group.setUserList(userRepo.getUsersByGroupId(groupId));
		return group;
	}

	@Override
	public String create(Group newGroup) {
		return groupRepository.create(newGroup);
	}

	@Override
	public void delete(String id) throws NoSuchModelException {
		groupRepository.delete(id);
	}

	@Override
	public Group update(String id, Group newGroup) {
		newGroup.setId(id);
		return groupRepository.update(newGroup);
	}

	@Override
	public List<Group> getByUserId(String userId) {
		
		return groupRepository.getByUserId(userId);
	}

}
