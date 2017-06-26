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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.epam.cdp.userManagement.exception.NoSuchModelException;
import com.epam.cdp.userManagement.model.Group;
import com.epam.cdp.userManagement.service.IGroupService;

//@RestController
public class GroupController {

	@Autowired
	private IGroupService groupService;
	
	@RequestMapping(value="/groups", method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
    public List<Group> getGroupList() {
		return groupService.getAll();
	}

	@RequestMapping(value="/groups/{id}", method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
    public Group getGroupDetails(@PathVariable("id") String id) {
		return groupService.getById(id);
	}
	
	@RequestMapping(value="/groups", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
    public String createGroup(@Valid @RequestBody Group newGroup) {
		return groupService.create(newGroup);
	}
	
	@RequestMapping(value="/groups/{id}", method=RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGroup(@PathVariable("id") String id) throws NoSuchModelException {
		groupService.delete(id);
	}
	
	@RequestMapping(value="/groups/{id}", method=RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
    public Group updateGroup(@PathVariable("id") String id, @Valid @RequestBody Group newGroup) {
		return groupService.update(id, newGroup);
	}
}
