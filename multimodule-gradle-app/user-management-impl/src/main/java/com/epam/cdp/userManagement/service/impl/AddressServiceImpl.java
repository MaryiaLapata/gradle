package com.epam.cdp.userManagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.epam.cdp.userManagement.dao.AddressRepository;
import com.epam.cdp.userManagement.dao.mongo.AddressRepoMongo;
import com.epam.cdp.userManagement.model.Address;
import com.epam.cdp.userManagement.service.IAddressService;

@Component
@Transactional(rollbackFor = Exception.class)
public class AddressServiceImpl implements IAddressService{
	
	@Autowired
	public AddressRepoMongo addressrepo;

	@Override
	public List<Address> getAll() {
		return addressrepo.findAll();
	}

	@Override
	public Address getById(String addressId) {
		return addressrepo.findOne(addressId);
	}

	@Override
	public String create(Address address) {
		Address savedAddress = addressrepo.save(address);
		return savedAddress.getId();
	}

	@Override
	public Address update(Address address) {
		if (!addressrepo.exists(address.getId())) {
			return null;
		}
		return addressrepo.save(address);
	}
	
	

}
