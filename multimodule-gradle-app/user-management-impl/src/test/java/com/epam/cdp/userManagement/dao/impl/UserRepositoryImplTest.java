package com.epam.cdp.userManagement.dao.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.epam.cdp.userManagement.UserManagementApp;
import com.epam.cdp.userManagement.exception.NoAffectedRowsDAOException;
import com.epam.cdp.userManagement.exception.NoSuchModelException;
import com.epam.cdp.userManagement.model.Address;
import com.epam.cdp.userManagement.model.Permission;
import com.epam.cdp.userManagement.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = UserManagementApp.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Transactional
@Rollback(true)
public class UserRepositoryImplTest {

	@Autowired
	private UserRepositoryImpl userRepository;
	
	@Test
	public void getAllTest(){
		List<User> userList = userRepository.getAll();
		assertEquals(20, userList.size());
	}
	
	@Test
	public void createTest(){
		Address newAddress = new Address(1, "NY", "Baker street", 25, 6);
		User newUser = new User(1, "Adams", "Collin", "Adams_Collin@global.com", "78531596", newAddress);
		long createdUserId = userRepository.create(newUser);
		User createdUser = userRepository.getById(createdUserId);
		assertNotEquals(1, createdUserId);
		assertEquals(newUser.getFirstName(), createdUser.getFirstName());
		assertEquals(newUser.getLastName(), createdUser.getLastName());
		assertEquals(newUser.getEmail(), createdUser.getEmail());
		assertEquals(newUser.getPhone(), createdUser.getPhone());
	}
	
	@Test
	public void getByIdTest(){
		User user = userRepository.getById(2);
		assertEquals(user.getId(), 2);
		assertEquals(user.getFirstName(), "Holly");
		assertEquals(user.getLastName(), "Ivan");
		assertEquals(user.getPhone(), "32165478");
	}
	
	@Test
	public void updateTestShouldReturnNullWhenThereIsNoUser(){
		User user = userRepository.update(new User(1, "Adams", "Collin", "Adams_Collin@global.com", "78531596", null));
		assertNull(user);
	}
	
	@Test
	public void updateTest(){
		User user = new User(2, "Adams", "Collin", "Adams_Collin@global.com", "78531596", null);
		User updatedUser = userRepository.update(user);
		User userFromDB = userRepository.getById(2);
		assertEquals(updatedUser.getId(), 2);
		assertEquals(updatedUser.getFirstName(), userFromDB.getFirstName());
		assertEquals(updatedUser.getPhone(), userFromDB.getPhone());
		assertEquals(updatedUser.getAddress(), userFromDB.getAddress());
	}
	
	@Test(expected = NoSuchModelException.class)
	public void deleteTestShouldThrowExcWhenNoUser() throws NoSuchModelException{
		userRepository.delete(1);
	}
	
	@Test
	public void delete() throws NoSuchModelException{
		userRepository.delete(2);
		User user = userRepository.getById(2);
		assertNull(user);
	}
	
	@Test
	public void assignPermissionTest(){
		long permissionId = 1;
		userRepository.assignPermission(3, 1);
		User user = userRepository.getById(3);
		Permission permission = user.getPermissionList().get(0);
		assertEquals(permissionId, permission.getId());
	}
	
	@Test
	public void getUsersByGroupIdTest(){
		List<User> users = userRepository.getUsersByGroupId(6);
		assertEquals(4, users.size());
	}
}
