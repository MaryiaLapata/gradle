package com.epam.cdp.userManagement;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.epam.cdp.userManagement.model.Address;

public interface AddressRepoMongo extends MongoRepository<Address, String>{

	List<Address> findByCity(String city);
	List<Address> findByStreet(String street);
}
