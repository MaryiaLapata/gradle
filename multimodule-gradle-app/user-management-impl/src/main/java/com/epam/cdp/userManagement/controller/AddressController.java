package com.epam.cdp.userManagement.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.epam.cdp.userManagement.model.Address;
import com.epam.cdp.userManagement.service.IAddressService;

@RestController
public class AddressController {
	
	@Autowired
	private IAddressService service;
	
	@RequestMapping(value="/addresses", method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public List<Address> getAll(){
		return service.getAll();
	}
	
	@RequestMapping(value="/addresses/{id}", method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public Address getAddress(@PathVariable("id") long addressId){
		return service.getById(addressId);
	}
	
	@RequestMapping(value="/addresses", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
    public long createUserAddress(@Valid @RequestBody Address newAddress) throws Exception {
		return service.create(newAddress);
	}
	
	@RequestMapping(value="/addresses/{id}", method=RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
    public Address updateAddress(@PathVariable("id") long id, @RequestBody Address newAddress) {
		newAddress.setId(id);
		return service.update(newAddress);
	}
}
