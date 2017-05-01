package com.epam.cdp.userManagement.service;

import java.util.List;

import com.epam.cdp.userManagement.exception.NoSuchModelException;
import com.epam.cdp.userManagement.model.Permission;

public interface IPermissionService {

	List<Permission> getAll();
	
	Permission  getById(long permissionId);
	
	long create(Permission permission);
	
	void delete(long permissionId) throws NoSuchModelException;
	
	Permission update(long id, Permission permission);
	
	List<Permission> getByUserId(long userId);
}
