package com.epam.cdp.userManagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.cdp.userManagement.dao.GroupRepository;
import com.epam.cdp.userManagement.model.Group;
import com.epam.cdp.userManagement.service.IGroupService;

@Component
public class GroupServiceImpl implements IGroupService{
	
	@Autowired
	private GroupRepository groupRepository;

	@Override
	public List<Group> getAll() {
		return groupRepository.getAll();
	}

	@Override
	public Group getById(long groupId) {
		// TODO Auto-generated method stub
		return groupRepository.getById(groupId);
	}

	@Override
	public long create(Group newGroup) {
		// TODO Auto-generated method stub
		return groupRepository.create(newGroup);
	}

}
