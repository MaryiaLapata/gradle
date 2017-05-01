package com.epam.cdp.userManagement.dao;


import com.epam.cdp.userManagement.model.License;

public interface LicenseRepository<T extends License> extends EntityRepository<T>{
	
	final String SQL_LICENSE_INSERT = "INSERT INTO license (name) VALUES(:name)";
	final String SQL_LICENSE_UPDATE = "update license set name=? where license_id=?";
	
	void assignPermissions(long groupId, long[] permissionIds);
}
