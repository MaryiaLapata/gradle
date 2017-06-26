package com.epam.cdp.userManagement.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.epam.cdp.userManagement.dao.GroupRepository;

import com.epam.cdp.userManagement.dao.PermissionRepository;
import com.epam.cdp.userManagement.dao.UserRepository;
import com.epam.cdp.userManagement.exception.NoAffectedRowsDAOException;
import com.epam.cdp.userManagement.exception.NoSuchModelException;
import com.epam.cdp.userManagement.model.Group;
import com.epam.cdp.userManagement.model.Permission;
import com.epam.cdp.userManagement.model.User;

//@Repository
public class UserRepositoryImpl implements UserRepository {

	private String HQL_SELECT = "SELECT user FROM User user";
	private String HQL_SELECT_BY_GROUP = "SELECT user FROM Group groupp INNER JOIN groupp.userList user WHERE groupp.id = :groupId";
	
	@PersistenceContext
	private EntityManager entityManager;
	@Autowired
	private GroupRepository groupRepository;
	@Autowired
	private PermissionRepository permissionRepo;
	
	public String create(User user) {
		user.setId("0");
		entityManager.persist(user);
		entityManager.flush();
		return user.getId();
	}

	@Override
	public User getById(String id) {
		return entityManager.find(User.class, id);
	}

	@Override
	public User update(User entity) {
		User user = getById(entity.getId());
		if(user == null) {
			return null;
		}
		return entityManager.merge(entity);
	}

	@Override
	public void delete(String id) throws NoSuchModelException {
		User user = getById(id);
		if(user == null) {
			throw new NoSuchModelException(User.class, id);
		}
		
		entityManager.remove(getById(id));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAll() {
		return (List<User>)entityManager.createQuery(HQL_SELECT).getResultList();
	}

	@Override
	public void assignPermissions(String userId, String[] permissionIds) {
		User user = getById(userId);
		for (String id : permissionIds) {
			Permission permission = permissionRepo.getById(id);
			user.getPermissionList().add(permission);
		}
		
		entityManager.merge(user);
	}

	@Override
	public void assignGroup(String userId, String groupId) {
		User user = getById(userId);
		Group group = groupRepository.getById(groupId);
		user.getGroupList().add(group);
		entityManager.merge(user);
	}

	@Override
	public void assignPermission(String userId, String permissionId) {
		User user = getById(userId);
		Permission permission = permissionRepo.getById(permissionId);
		user.getPermissionList().add(permission);
		entityManager.merge(user);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUsersByGroupId(String groupId) {
		Query query = entityManager.createQuery(HQL_SELECT_BY_GROUP);
		query.setParameter("groupId", groupId);
		return (List<User>)query.getResultList();
	}
}
