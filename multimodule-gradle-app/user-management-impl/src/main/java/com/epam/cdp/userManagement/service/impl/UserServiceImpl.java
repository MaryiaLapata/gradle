package com.epam.cdp.userManagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.epam.cdp.userManagement.dao.EntityRepository;
import com.epam.cdp.userManagement.dao.UserRepository;
import com.epam.cdp.userManagement.exception.NoAffectedRowsDAOException;
import com.epam.cdp.userManagement.exception.NoSuchModelException;
import com.epam.cdp.userManagement.model.Address;
import com.epam.cdp.userManagement.model.User;
import com.epam.cdp.userManagement.service.IUserService;

@Component
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private EntityRepository<Address> addressRepository;
		
	@Override
	public List<User> getAll() {
		return userRepository.getAll();
	}

	@Override
	public User getById(long userId) throws NoSuchModelException {
		User user = userRepository.getById(userId);
		
		if (user == null) {
			throw new NoSuchModelException(User.class, userId);
		}
		
		return user;
	}

	@Override
	public long create(User newUser) {	
		long addressId = addressRepository.create(newUser.getAddress());
		newUser.getAddress().setId(addressId);
		return userRepository.create(newUser);
	}

	@Override
	public User update(long id, User user) {
		user.setId(id);
		
		return userRepository.update(user);
	}

	@Override
	public void delete(long id) throws NoSuchModelException {
		userRepository.delete(id);		
	}

	@Override
	public void assignGroup(long userId, long groupId) {
		userRepository.assignGroup(userId, groupId);
	}

	@Override
	public void assignPermission(long userId, long permissionId) {
		userRepository.assignPermission(userId, permissionId);
	}
}
