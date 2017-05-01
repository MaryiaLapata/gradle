package com.epam.cdp.userManagement.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.epam.cdp.userManagement.dao.PermissionRepository;
import com.epam.cdp.userManagement.dao.helper.PermissionRowMapper;
import com.epam.cdp.userManagement.model.Permission;

@Repository
public class PermissionRepositoryImpl implements PermissionRepository {
	
	private String SQL_SELECT = "SELECT * FROM permission WHERE permission_id=?";
	private String SQL_SELECT_ALL = "SELECT * FROM permission";
	private String SQL_DELETE = "DELETE FROM permission WHERE permission_id=?";
	private String SQL_UPDATE = "update permission set object=?, action_type=? WHERE permission_id=?";
	private String SQL_INSERT = "INSERT INTO permission(object, action_type) VALUES (:object, :action_type)";
	private String SQL_SELECT_BY_USER = "SELECT * FROM permission WHERE permission_id IN (SELECT user_permission.permission_id FROM user_permission WHERE user_permission.user_id=?)";
	private String SQL_SELECT_BY_GROUP = "SELECT * FROM permission WHERE permission_id IN (SELECT group_permission.permission_id FROM group_permission WHERE group_permission.group_id=?)";

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Override
	public long create(Permission entity) {
		SqlParameterSource fileParameters = new MapSqlParameterSource()
		        .addValue("action_type", entity.getActionType())
		        .addValue("object", entity.getObject());
	    KeyHolder keyHolder = new GeneratedKeyHolder();
	    namedParameterJdbcTemplate.update(SQL_INSERT, fileParameters, keyHolder);
	    return keyHolder.getKey().intValue();
	}

	@Override
	public Permission getById(long id) {
		return jdbcTemplate.queryForObject(SQL_SELECT,
                new PermissionRowMapper(), id);
	}

	@Override
	public int update(Permission entity) {
		return jdbcTemplate.update(
				SQL_UPDATE,
				entity.getObject(), entity.getActionType(), entity.getId());
	}

	@Override
	public void delete(long id) {
		jdbcTemplate.update(
				SQL_DELETE,
				id);
	}

	@Override
	public List<Permission> getAll() {
		return jdbcTemplate.query(SQL_SELECT_ALL,
                new PermissionRowMapper());
	}

	@Override
	public List<Permission> getByUserId(long userId) {
		return jdbcTemplate.query(SQL_SELECT_BY_USER,
                new PermissionRowMapper(), userId);
	}

	@Override
	public List<Permission> getByGroupId(long groupId) {
		return jdbcTemplate.query(SQL_SELECT_BY_GROUP,
                new PermissionRowMapper(), groupId);
	}

}
