package com.epam.cdp.userManagement.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import com.epam.cdp.userManagement.exception.NoSuchModelException;
import com.epam.cdp.userManagement.model.Permission;
import com.epam.cdp.userManagement.service.IPermissionService;

//@RestController
public class PermissionController {
	
	@Autowired
	IPermissionService service;
	
	@RequestMapping(value="/permissions", method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public List<Permission> getAll(){
		return service.getAll();
	}
	
	@RequestMapping(value="/permissions/{id}", method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public Permission getPermission(@PathVariable("id") String permissionId){
		return service.getById(permissionId);
	}
	
	@RequestMapping(value="/permissions", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public String createPermission(@Valid @RequestBody Permission permission) {
		return service.create(permission);
	}
	
	@RequestMapping(value="/permissions/{id}", method=RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePermission(@PathVariable("id") String id) throws NoSuchModelException {
		service.delete(id);
	}
	
	@RequestMapping(value="/permissions/{id}", method=RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
    public Permission updateUser(@PathVariable("id") String id, @RequestBody Permission permission) {
		permission.setId(id);
		return service.update(id, permission);
	}
}
