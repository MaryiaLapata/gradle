package com.epam.cdp.userManagement.service;

import java.util.List;

import com.epam.cdp.userManagement.exception.NoSuchModelException;
import com.epam.cdp.userManagement.model.Permission;

public interface IPermissionService {

	List<Permission> getAll();
	
	Permission  getById(String permissionId);
	
	String create(Permission permission);
	
	void delete(String permissionId) throws NoSuchModelException;
	
	Permission update(String id, Permission permission);
	
	List<Permission> getByUserId(String userId);
}
