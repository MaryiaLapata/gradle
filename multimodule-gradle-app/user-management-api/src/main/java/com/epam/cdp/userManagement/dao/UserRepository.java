package com.epam.cdp.userManagement.dao;

import java.util.List;

import com.epam.cdp.userManagement.model.User;

public interface UserRepository extends LicenseRepository<User>{

	void assignGroup(String userId, String groupId);
	
	void assignPermission(String userId, String permissionId);
	
	List<User> getUsersByGroupId(String groupId);
}
