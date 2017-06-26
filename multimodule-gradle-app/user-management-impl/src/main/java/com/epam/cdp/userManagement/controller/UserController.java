package com.epam.cdp.userManagement.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.epam.cdp.userManagement.exception.NoSuchModelException;
import com.epam.cdp.userManagement.model.Group;
import com.epam.cdp.userManagement.model.Permission;
import com.epam.cdp.userManagement.model.User;
import com.epam.cdp.userManagement.service.IGroupService;
import com.epam.cdp.userManagement.service.IPermissionService;
import com.epam.cdp.userManagement.service.IUserService;


//@RestController
public class UserController {
	
	@Autowired
	private IUserService userService;
	@Autowired
	private IGroupService groupService;
	@Autowired
	private IPermissionService permissionService;

	@RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }
	
	@RequestMapping(value="/users", method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
    public List<User> getUserList() {
		return userService.getAll();
	}

	@RequestMapping(value="/users/{id}", method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
    public User getUserDetails(@PathVariable("id") String id) throws NoSuchModelException {
		return userService.getById(id);
	}
	
	@RequestMapping(value="/users/{id}", method=RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable("id") String id) throws NoSuchModelException {
		userService.delete(id);
	}

	@RequestMapping(value="/users", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
    public String createUser(@Valid @RequestBody User newUser) throws Exception {
		return userService.create(newUser);
	}
	
	@RequestMapping(value="/users/{id}", method=RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
    public User updateUser(@PathVariable("id") String id, @RequestBody User newUser) {
		newUser.setId(id);
		return userService.update(id, newUser);
	}
	
	@RequestMapping(value="/users/{id}/groups", method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public List<Group> getGroupsByUserId(@PathVariable("id") String userId) {
		return groupService.getByUserId(userId);
	}
	
	@RequestMapping(value="/users/{id}/groups", params="action=assign", method=RequestMethod.PUT)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void assignGroup(@PathVariable("id") String userId, @RequestParam("groupId") String groupId) {
		userService.assignGroup(userId, groupId);
	}
	
	@RequestMapping(value="/users/{id}/permissions", method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public List<Permission> getPermissionsByUserId(@PathVariable("id") String userId) {
		return permissionService.getByUserId(userId);
	}
	
	@RequestMapping(value="/users/{id}/permissions", params="action=assign", method=RequestMethod.PUT)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void assignPermission(@PathVariable("id") String userId, @RequestParam("permissionId") String permissionId) {
		userService.assignPermission(userId, permissionId);
	}
}
