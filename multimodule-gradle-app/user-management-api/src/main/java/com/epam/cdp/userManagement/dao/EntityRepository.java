package com.epam.cdp.userManagement.dao;

import java.util.List;

import com.epam.cdp.userManagement.exception.NoAffectedRowsDAOException;

public interface EntityRepository<T> {
	
	long create(T entiry);
	T getById(long id);
	int update(T entity);
	void delete(long id) throws NoAffectedRowsDAOException;
	
	List<T> getAll();

}
