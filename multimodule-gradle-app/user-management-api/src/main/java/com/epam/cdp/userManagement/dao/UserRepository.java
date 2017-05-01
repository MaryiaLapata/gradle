package com.epam.cdp.userManagement.dao;

import java.util.List;

import com.epam.cdp.userManagement.model.User;

public interface UserRepository extends LicenseRepository<User>{

	void assignGroup(long userId, long groupId);
	
	void assignPermission(long userId, long permissionId);
	
	List<User> getUsersByGroupId(long groupId);
}
