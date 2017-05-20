package com.epam.cdp.userManagement.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.epam.cdp.userManagement.dao.PermissionRepository;
import com.epam.cdp.userManagement.exception.NoSuchModelException;
import com.epam.cdp.userManagement.model.Permission;

@Repository
public class PermissionRepositoryImpl implements PermissionRepository {
	
	private String HQL_SELECT_ALL = "SELECT permission FROM Permission permission";
	private String HQL_SELECT_BY_USER = "SELECT permission FROM Permission permission JOIN User user WHERE user.id = :userId";
	private String HQL_SELECT_BY_GROUP = "SELECT permission FROM Permission permission JOIN Group group WHERE group.id = :groupId";

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public long create(Permission entity) {
		entity.setId(0);
		entityManager.persist(entity);
		entityManager.flush();
		return entity.getId();
	}

	@Override
	public Permission getById(long id) {
		return entityManager.find(Permission.class, id);
	}

	@Override
	public Permission update(Permission entity) {
		Permission permission = getById(entity.getId());
		if(permission == null){
			return null;
		}
		return entityManager.merge(entity);
	}

	@Override
	public void delete(long id) throws NoSuchModelException {
		Permission permission = getById(id);
		if(permission == null){
			throw new NoSuchModelException(Permission.class, id);
		}
		entityManager.remove(permission);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Permission> getAll() {
		return (List<Permission>)entityManager.createQuery(HQL_SELECT_ALL).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Permission> getByUserId(long userId) {
		Query query = entityManager.createQuery(HQL_SELECT_BY_USER);
		query.setParameter("userId", userId);
		return (List<Permission>)query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Permission> getByGroupId(long groupId) {
		Query query = entityManager.createQuery(HQL_SELECT_BY_GROUP);
		query.setParameter("groupId", groupId);
		return (List<Permission>)query.getResultList();
	}

}
