package com.epam.cdp.userManagement.service;

import java.util.List;

import com.epam.cdp.userManagement.model.Address;

public interface IAddressService {
	
	List<Address> getAll();
	
	Address getById(long addressId);
	
	long create(Address address);
	
	Address update(Address address);

}
