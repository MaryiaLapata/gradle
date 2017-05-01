package com.epam.cdp.userManagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.cdp.userManagement.dao.PermissionRepository;
import com.epam.cdp.userManagement.exception.NoAffectedRowsDAOException;
import com.epam.cdp.userManagement.exception.NoSuchModelException;
import com.epam.cdp.userManagement.model.Permission;
import com.epam.cdp.userManagement.service.IPermissionService;

@Component
public class PermissionServiceImpl implements IPermissionService{

	@Autowired
	PermissionRepository permissionRepo;
	
	@Override
	public List<Permission> getAll() {
		return permissionRepo.getAll();
	}

	@Override
	public Permission getById(long permissionId) {
		return permissionRepo.getById(permissionId);
	}

	@Override
	public long create(Permission permission) {
		return permissionRepo.create(permission);
	}

	@Override
	public void delete(long permissionId) throws NoSuchModelException {
		try {
			permissionRepo.delete(permissionId);
		} catch (NoAffectedRowsDAOException e) {
			throw new NoSuchModelException(Permission.class, e.getEntityId());
		}
	}

	@Override
	public Permission update(long id, Permission permission) {
		permission.setId(id);
		
		if(permissionRepo.update(permission) > 0) {
			return permissionRepo.getById(id);
		}
		
		return null;
	}

	@Override
	public List<Permission> getByUserId(long userId) {
		return permissionRepo.getByUserId(userId);
	}

}
