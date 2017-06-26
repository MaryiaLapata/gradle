package com.epam.cdp.userManagement.dao;

import java.util.List;

import com.epam.cdp.userManagement.model.Permission;

public interface PermissionRepository extends EntityRepository<Permission>{

	List<Permission> getByUserId(String userId);
	
	List<Permission> getByGroupId(String groupId);
}
