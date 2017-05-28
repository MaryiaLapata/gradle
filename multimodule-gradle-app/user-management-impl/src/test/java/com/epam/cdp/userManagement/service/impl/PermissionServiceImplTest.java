package com.epam.cdp.userManagement.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.epam.cdp.userManagement.dao.PermissionRepository;
import com.epam.cdp.userManagement.model.Permission;
import com.epam.cdp.userManagement.service.impl.PermissionServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
public class PermissionServiceImplTest {
	
	@InjectMocks
	private PermissionServiceImpl service;
	
	@Mock
	private PermissionRepository permissionRepo;
	
	private long permissionId;
	private Permission permission;

	@Before
	public void setup(){
		permissionId = 3;
		permission = new Permission(3, "Permission", "111");
	}
	
	@Test
	public void getAllTest(){
		List<Permission> permissions = new ArrayList<>();
		permissions.add(permission);
		permissions.add(new Permission(2, "Permission", "101"));
		when(permissionRepo.getAll()).thenReturn(permissions);
		List<Permission> result = service.getAll();
		verify(permissionRepo, times(1)).getAll();
		assertEquals(2, result.size());
	}
	
	@Test
	public void getByIdTest() {
		when(permissionRepo.getById(permissionId)).thenReturn(permission);
		Permission result = service.getById(permissionId);
		assertEquals(result.getId(), permission.getId());
	}
	
	@Test
	public void createTest() {
		long newAddressId = 2;
		when(permissionRepo.create(permission)).thenReturn(newAddressId);
		long result = permissionRepo.create(permission);
		assertEquals(result, newAddressId);
	}
	
	@Test
	public void updateTest(){
		when(permissionRepo.update(permission)).thenReturn(permission);
		Permission result = service.update(4, permission);
		assertEquals(4, result.getId());
		verify(permissionRepo, times(1)).update(permission);
	}
}
