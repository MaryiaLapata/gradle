package com.epam.cdp.userManagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.cdp.userManagement.dao.PermissionRepository;
import com.epam.cdp.userManagement.exception.NoAffectedRowsDAOException;
import com.epam.cdp.userManagement.exception.NoSuchModelException;
import com.epam.cdp.userManagement.model.Permission;
import com.epam.cdp.userManagement.service.IPermissionService;

//@Component
public class PermissionServiceImpl implements IPermissionService{

	@Autowired
	PermissionRepository permissionRepo;
	
	@Override
	public List<Permission> getAll() {
		return permissionRepo.getAll();
	}

	@Override
	public Permission getById(String permissionId) {
		return permissionRepo.getById(permissionId);
	}

	@Override
	public String create(Permission permission) {
		return permissionRepo.create(permission);
	}

	@Override
	public void delete(String permissionId) throws NoSuchModelException {
		permissionRepo.delete(permissionId);
	}

	@Override
	public Permission update(String id, Permission permission) {
		permission.setId(id);
		return permissionRepo.update(permission);
	}

	@Override
	public List<Permission> getByUserId(String userId) {
		return permissionRepo.getByUserId(userId);
	}

}
