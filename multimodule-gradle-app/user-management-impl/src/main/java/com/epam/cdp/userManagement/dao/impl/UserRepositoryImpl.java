package com.epam.cdp.userManagement.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.epam.cdp.userManagement.dao.LicenseRepository;
import com.epam.cdp.userManagement.dao.helper.UserRowMapper;
import com.epam.cdp.userManagement.model.License;
import com.epam.cdp.userManagement.model.User;

@Repository
public class UserRepositoryImpl implements LicenseRepository<User>{
	
	private String SQL_USER_INSERT = "INSERT INTO user(user_id, first_name, last_name, email, phone, address_id) VALUES (LAST_INSERT_ID(), :firstName, :lastName, :email, :phone, :address.id)";
	private String SQL_SELECT_ALL = "select user_id, first_name, last_name, email, phone, user.address_id, city, street, house_number, flat_number, license.name from address, user INNER JOIN license ON user.user_id=license.license_id  where user.address_id=address.address_id";
	private String SQL_SELECT = "select * from address, user INNER JOIN license ON user.user_id=license.license_id where user.user_id=? AND user.address_id=address.address_id";
	private String SQL_UPDATE = "update user set first_name=?, last_name=?, email=?, phone=?,  address_id=? where user_id=?";
	private String SQL_DELETE = "delete from user where user_id=?";
	private String SQL_ADD_PERMISSION = "INSERT INTO user_permission(user_id, permission_id) VALUES (?,?)";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	public long create(User user) {
		SqlParameterSource fileParameters = new BeanPropertySqlParameterSource(user);
		License licenseObj = new License(user.getLastName() + ", " + user.getFirstName());
		KeyHolder keyHolder = new GeneratedKeyHolder();
		namedParameterJdbcTemplate.update(SQL_LICENSE_INSERT, new BeanPropertySqlParameterSource(licenseObj), keyHolder);
		namedParameterJdbcTemplate.update(SQL_USER_INSERT, fileParameters);
		return keyHolder.getKey().intValue();
	    }


	@Override
	public User getById(long id) {
		return jdbcTemplate.queryForObject(SQL_SELECT,
                new UserRowMapper(), id);
	}

	@Override
	public int update(User entity) {		
		jdbcTemplate.update(SQL_LICENSE_UPDATE, entity.getFirstName() + ", " + entity.getLastName(), entity.getId());
		return jdbcTemplate.update(
				SQL_UPDATE,
				entity.getFirstName(), entity.getLastName(), entity.getEmail(), entity.getPhone(), entity.getAddress().getId(), entity.getId());
	}

	@Override
	public void delete(long id) {
		jdbcTemplate.update(
				SQL_DELETE,
				id);
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
}
