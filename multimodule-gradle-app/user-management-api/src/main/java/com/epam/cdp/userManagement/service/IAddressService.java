package com.epam.cdp.userManagement.service;

import java.util.List;

import com.epam.cdp.userManagement.model.Address;

public interface IAddressService {
	
	List<Address> getAll();
	
	Address getById(String addressId);
	
	String create(Address address);
	
	Address update(Address address);

}
