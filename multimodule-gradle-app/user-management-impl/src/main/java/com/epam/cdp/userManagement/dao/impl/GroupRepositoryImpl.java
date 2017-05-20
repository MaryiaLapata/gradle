package com.epam.cdp.userManagement.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.epam.cdp.userManagement.dao.GroupRepository;
import com.epam.cdp.userManagement.dao.PermissionRepository;
import com.epam.cdp.userManagement.exception.NoSuchModelException;
import com.epam.cdp.userManagement.model.Group;
import com.epam.cdp.userManagement.model.Permission;

@Repository
public class GroupRepositoryImpl implements GroupRepository {

	private String HQL_SELECT_ALL = "SELECT group FROM Group group";
	private String HQL_SELECT_BY_USER = "SELECT group FROM Group group JOIN User user WHERE user.id=:userId)";
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private PermissionRepository permissionRepo;
	
	@Override
	public long create(Group entity) {
		entity.setId(0);
		entityManager.persist(entity);
		entityManager.flush();
		return entity.getId();
	}

	@Override
	public Group getById(long id) {
		return entityManager.find(Group.class, id);
	}

	@Override
	public Group update(Group entity) {
		Group group = getById(entity.getId());
		if(group == null) {
			return null;
		}
		return entityManager.merge(entity);
	}

	@Override
	public void delete(long id) throws NoSuchModelException {
		Group group = getById(id);
		if(group == null) {
			throw new NoSuchModelException(Group.class, id);
		}
		entityManager.remove(group);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Group> getAll() {
		return (List<Group>)entityManager.createQuery(HQL_SELECT_ALL).getResultList();
	}

	@Override
	public void addUsers(long groupId, List<Long> userIds) {
		Group group = getById(groupId);
		for(Long id : userIds) {
			//User user = userRepo.getById(id);
			//group.getUserList().add(user);
		}
		entityManager.merge(group);
	}

	@Override
	public void assignPermissions(long groupId, long[] permissionIds) {
		Group group = getById(groupId);
		for(long id : permissionIds) {
			Permission permision = permissionRepo.getById(id);
			group.getPermissionList().add(permision);
		}
		entityManager.merge(group);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Group> getByUserId(long userId) {
		Query query = entityManager.createQuery(HQL_SELECT_BY_USER);
		query.setParameter("userId", userId);
		return (List<Group>)query.getResultList();
		
	}

}
