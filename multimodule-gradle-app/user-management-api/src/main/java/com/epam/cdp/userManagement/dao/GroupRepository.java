package com.epam.cdp.userManagement.dao;

import java.util.List;

import com.epam.cdp.userManagement.model.Group;

public interface GroupRepository extends LicenseRepository<Group>{

	void addUsers(String groupId, List<String> userIds);
	
	List<Group> getByUserId(String userId);
}
