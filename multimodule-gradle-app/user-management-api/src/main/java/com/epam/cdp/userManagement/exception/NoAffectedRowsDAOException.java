package com.epam.cdp.userManagement.exception;

public class NoAffectedRowsDAOException extends Exception{

	private static final long serialVersionUID = 1L;
	
	private long entityId;
	private String entityName;
	private String entityClassName;
	
	public NoAffectedRowsDAOException(){}
	
	public NoAffectedRowsDAOException(Class entityClass, long id) {
		this.entityClassName = entityClass.getName();
		this.entityName = entityClass.getSimpleName();
		this.entityId = id;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public long getEntityId() {
		return entityId;
	}

	public void setEntityId(long entityId) {
		this.entityId = entityId;
	}

	public String getEntityClassName() {
		return entityClassName;
	}

	public void setEntityClassName(String entityClassName) {
		this.entityClassName = entityClassName;
	}
}
