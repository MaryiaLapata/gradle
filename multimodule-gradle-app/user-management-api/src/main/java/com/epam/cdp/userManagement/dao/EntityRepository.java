package com.epam.cdp.userManagement.dao;

import java.io.Serializable;
import java.util.List;

import com.epam.cdp.userManagement.exception.NoAffectedRowsDAOException;
import com.epam.cdp.userManagement.exception.NoSuchModelException;
import com.epam.cdp.userManagement.model.Address;

public interface EntityRepository<T extends Serializable> {
	
	String create(T entiry);
	T getById(String id);
	T update(T entity);
	void delete(String id) throws NoSuchModelException;
	
	List<T> getAll();

}
