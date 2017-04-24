package com.epam.cdp.userManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.epam.cdp.userManagement.model.Group;
import com.epam.cdp.userManagement.service.IGroupService;

@RestController
public class GroupController {

	@Autowired
	private IGroupService groupService;
	
	@RequestMapping(value="/groups", method=RequestMethod.GET)
    public List<Group> getGroupList() {
		return groupService.getAll();
	}

	@RequestMapping(value="/groups/{id}", method=RequestMethod.GET)
    public Group getGroupDetails(@PathVariable("id") long id) {
		return groupService.getById(id);
	}
	
	@RequestMapping(value="/groups", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public long createGroup(@RequestBody Group newGroup) {
		return groupService.create(newGroup);
	}
}
