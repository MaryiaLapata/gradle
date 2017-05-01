package com.epam.cdp.userManagement.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.epam.cdp.userManagement.dao.GroupRepository;

import com.epam.cdp.userManagement.dao.PermissionRepository;
import com.epam.cdp.userManagement.dao.UserRepository;
import com.epam.cdp.userManagement.dao.helper.UserRowMapper;
import com.epam.cdp.userManagement.exception.NoAffectedRowsDAOException;

import com.epam.cdp.userManagement.model.Group;
import com.epam.cdp.userManagement.model.License;
import com.epam.cdp.userManagement.model.Permission;
import com.epam.cdp.userManagement.model.User;

@Repository
public class UserRepositoryImpl implements UserRepository{
	
	private String SQL_USER_INSERT = "INSERT INTO user(user_id, first_name, last_name, email, phone, address_id) VALUES (LAST_INSERT_ID(), :firstName, :lastName, :email, :phone, :address.id)";
	private String SQL_SELECT_ALL = "select user_id, first_name, last_name, email, phone, user.address_id, city, street, house_number, flat_number, license.name from address, user INNER JOIN license ON user.user_id=license.license_id  where user.address_id=address.address_id";
	private String SQL_SELECT = "select * from address, user INNER JOIN license ON user.user_id=license.license_id where user.user_id=? AND user.address_id=address.address_id";
	private String SQL_UPDATE = "update user set first_name=?, last_name=?, email=?, phone=?,  address_id=? where user_id=?";
	private String SQL_DELETE = "delete from user where user_id=?";
	private String SQL_ADD_PERMISSION = "INSERT INTO user_permission(user_id, permission_id) VALUES (?,?)";
	private String SQL_ASSIGN_GROUP = "INSERT INTO user_group(user_id, group_id) VALUES (?, ?)";
	private String SQL_ASSIGN_PERMISSION = "INSERT INTO user_permission(user_id, permission_id) VALUES (?, ?)";
	private String SQL_SELECT_BY_GROUP = "SELECT * FROM user WHERE user.user_id IN (SELECT user_id FROM user_group WHERE user_group.group_id=?)";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	@Autowired
	private GroupRepository groupRepository;
	@Autowired
	private PermissionRepository permissionRepo;
	//@Autowired
	//private AddressRepository addressRepo;
	
	public long create(User user) {
		SqlParameterSource fileParameters = new BeanPropertySqlParameterSource(user);
		License licenseObj = new License(user.getLastName() + ", " + user.getFirstName());
		KeyHolder keyHolder = new GeneratedKeyHolder();
		namedParameterJdbcTemplate.update(SQL_LICENSE_INSERT, new BeanPropertySqlParameterSource(licenseObj), keyHolder);
		namedParameterJdbcTemplate.update(SQL_USER_INSERT, fileParameters);
		
		/*Address address = user.getAddress();
		try {
			address.setId(addressRepo.getIdByAddress(address));
		} catch (NoSuchModelException e) {
			//address.setId(addressRepo.create(address));
		}
		*/
		long userId = keyHolder.getKey().intValue();
		
		for (Group group : user.getGroupList()){
			long groupId = group.getId();
			if (groupId != 0 && groupRepository.getById(groupId) != null){
				assignGroup(userId, groupId);
			}
		}
		
		for (Permission permission : user.getPermissionList()) {
			long permissionId = permission.getId();
			if (permissionId != 0 && permissionRepo.getById(permissionId) != null) {
				assignPermission(userId, permissionId);
			}
		}
		
		return userId;
	    }


	@Override
	public User getById(long id) {
		User user;
		
		try{
			user = jdbcTemplate.queryForObject(SQL_SELECT, new UserRowMapper(), id);
			user.setGroupList(groupRepository.getByUserId(id));
			user.setPermissionList(permissionRepo.getByUserId(id));
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
		
		return user;
	}

	@Override
	public int update(User entity) {		
		jdbcTemplate.update(SQL_LICENSE_UPDATE, entity.getFirstName() + ", " + entity.getLastName(), entity.getId());
		return jdbcTemplate.update(
				SQL_UPDATE,
				entity.getFirstName(), entity.getLastName(), entity.getEmail(), entity.getPhone(), entity.getAddress().getId(), entity.getId());
	}

	@Override
	public void delete(long id) throws NoAffectedRowsDAOException {
		int updatedRows = jdbcTemplate.update(SQL_DELETE, id);
		
		if (updatedRows == 0) {
			throw new NoAffectedRowsDAOException(User.class, id);
		}
	}

	@Override
	public List<User> getAll() {
		return jdbcTemplate.query(SQL_SELECT_ALL,
                new UserRowMapper());
	}

	@Override
	public void assignPermissions(long userId, long[] permissionIds) {
		for(long id : permissionIds) {
			jdbcTemplate.update(
					SQL_ADD_PERMISSION,
					userId, id);
		}		
	}


	@Override
	public void assignGroup(long userId, long groupId) {
		jdbcTemplate.update(SQL_ASSIGN_GROUP, userId, groupId);
	}


	@Override
	public void assignPermission(long userId, long permissionId) {
		jdbcTemplate.update(SQL_ASSIGN_PERMISSION, userId, permissionId);
	}


	@Override
	public List<User> getUsersByGroupId(long groupId) {
		return jdbcTemplate.query(SQL_SELECT_BY_GROUP,
                new UserRowMapper(), groupId);
	}
}
