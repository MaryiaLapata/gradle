package com.epam.cdp.userManagement.dao;

import java.util.List;

import com.epam.cdp.userManagement.exception.NoAffectedRowsDAOException;
import com.epam.cdp.userManagement.exception.NoSuchModelException;
import com.epam.cdp.userManagement.model.Address;

public interface EntityRepository<T> {
	
	long create(T entiry);
	T getById(long id);
	T update(T entity);
	void delete(long id) throws NoAffectedRowsDAOException, NoSuchModelException;
	
	List<T> getAll();

}
