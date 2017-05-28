package com.epam.cdp.userManagement.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.epam.cdp.userManagement.dao.AddressRepository;
import com.epam.cdp.userManagement.dao.UserRepository;
import com.epam.cdp.userManagement.exception.NoAffectedRowsDAOException;
import com.epam.cdp.userManagement.exception.NoSuchModelException;
import com.epam.cdp.userManagement.model.Address;
import com.epam.cdp.userManagement.model.User;
import com.epam.cdp.userManagement.service.IUserService;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceImplTest {
	
	private long id = 123;
	
	@Mock
	private UserRepository userRepository;
	
	@Mock
	private AddressRepository addressRepo;
	
	@InjectMocks
	private UserServiceImpl userService;
	
	@Test
	public void getByIdTest() throws NoSuchModelException {
		User user = new User(id, "Garry", "Smith", "Garry.Smith@gmail.com", "123456789", null);
		when(userRepository.getById(id)).thenReturn(user);
		
		User result = userService.getById(id);
		
		assertEquals(user, result);
	}
	

	@Test
	public void deleteTest() throws NoSuchModelException, NoAffectedRowsDAOException {
		userService.delete(id);
		verify(userRepository, times(1)).delete(123);
	}
	
	@Test
	public void getAllTest(){
		List<User> expectedList = new ArrayList<>();
		expectedList.add(new User(12, "Garry", "Smith", "Garry.Smith@gmail.com", "123456789", null));
		expectedList.add(new User(13, "Barry", "Noida", "Barry.Noida@gmail.com", "987654321", null));
		when(userRepository.getAll()).thenReturn(expectedList);
		
		List<User> result = userService.getAll();
		assertEquals(2, result.size());
	}
	
	@Test
	public void createTest() {
		Address address = new Address();
		User user = new User(id, "Garry", "Smith", "Garry.Smith@gmail.com", "123456789", address);
		when(userRepository.create(user)).thenReturn(id);
		when(addressRepo.create(address)).thenReturn(0L);
		long result = userService.create(user);
		assertEquals(result, id);
	}
	
	@Test
	public void updateTestShoudSetEntityId(){
		long entityId = 33;
		User user = new User(id, "Garry", "Smith", "Garry.Smith@gmail.com", "123456789", null);
		User updatedUser = new User(entityId, "Garry", "Smith", "Garry.Smith@gmail.com", "123456789", null);
		when(userRepository.update(any(User.class))).thenReturn(updatedUser);
		User result = userService.update(entityId, user);
		assertEquals(entityId, result.getId());
	}
}
