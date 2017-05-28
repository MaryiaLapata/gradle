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

import com.epam.cdp.userManagement.dao.AddressRepository;
import com.epam.cdp.userManagement.model.Address;
import com.epam.cdp.userManagement.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
public class AddressServiceImplTest {

	@InjectMocks
	private AddressServiceImpl service;
	
	@Mock
	private AddressRepository addressRepo;
	
	private long addressId;
	private Address address;
	
	@Before
	public void setup(){
		addressId = 1;
		address = new Address(1, "Minsk", "Kuleshova", 55, 2);
	}
	
	@Test
	public void getAllTest(){
		List<Address> addresses = new ArrayList<>();
		addresses.add(address);
		addresses.add(new Address(2, "Brest", "Kulman", 89, 23));
		when(addressRepo.getAll()).thenReturn(addresses);
		List<Address> result = service.getAll();
		verify(addressRepo, times(1)).getAll();
		assertEquals(2, result.size());
	}
	
	@Test
	public void getByIdTest() {
		when(addressRepo.getById(addressId)).thenReturn(address);
		Address result = service.getById(addressId);
		assertEquals(result.getId(), address.getId());
	}
	
	@Test
	public void createTest() {
		long newAddressId = 2;
		when(addressRepo.create(address)).thenReturn(newAddressId);
		long result = addressRepo.create(address);
		assertEquals(result, newAddressId);
	}
	
	@Test
	public void updateTest(){
		when(addressRepo.update(address)).thenReturn(address);
		Address result = service.update(address);
		assertEquals(address, result);
	}
}
