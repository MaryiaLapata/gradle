package com.epam.cdp.userManagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.cdp.userManagement.dao.AddressRepository;
import com.epam.cdp.userManagement.model.Address;
import com.epam.cdp.userManagement.service.IAddressService;

@Component
public class AddressServiceImpl implements IAddressService{
	
	@Autowired
	private AddressRepository addressRepo;

	@Override
	public List<Address> getAll() {
		return addressRepo.getAll();
	}

	@Override
	public Address getById(long addressId) {
		return addressRepo.getById(addressId);
	}

	@Override
	public long create(Address address) {
		return addressRepo.create(address);
	}

	@Override
	public Address update(Address address) {
		if(addressRepo.update(address) > 0) {
			return addressRepo.getById(address.getId());
		}
		
		return null;
	}
	
	

}
