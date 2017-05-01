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

import com.epam.cdp.userManagement.dao.GroupRepository;
import com.epam.cdp.userManagement.dao.PermissionRepository;
import com.epam.cdp.userManagement.dao.helper.GroupRowMapper;
import com.epam.cdp.userManagement.model.Group;
import com.epam.cdp.userManagement.model.License;

@Repository
public class GroupRepositoryImpl implements GroupRepository {

	private String SQL_SELECT = "SELECT * FROM `group` INNER JOIN license ON `group`.group_id=license.license_id WHERE `group`.group_id=?";
	private String SQL_SELECT_ALL = "SELECT * FROM `group` INNER JOIN license ON `group`.group_id=license.license_id";
	private String SQL_DELETE = "DELETE FROM `group` WHERE group_id=?";
	private String SQL_UPDATE = "update `group` set name=? WHERE group_id=?";
	private String SQL_INSERT = "INSERT INTO `group`(group_id) VALUES (LAST_INSERT_ID())";
	private String SQL_ADD_USER = "INSERT INTO user_group(user_id, group_id) VALUES (?,?)";
	private String SQL_ADD_PERMISSION = "INSERT INTO group_permission(group_id, permission_id) VALUES (?,?)";
	private String SQL_SELECT_BY_USER = "SELECT * FROM `group` INNER JOIN license ON `group`.group_id=license.license_id WHERE `group`.group_id IN (SELECT user_group.group_id FROM user_group WHERE user_group.user_id=?)";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	@Autowired
	private PermissionRepository permissionRepo;
//	@Autowired
//	private UserRepository userRepo;
	
	@Override
	public long create(Group entity) {
		SqlParameterSource fileParameters = new BeanPropertySqlParameterSource(entity);
		License licenseObj = new License(entity.getName());
	    KeyHolder keyHolder = new GeneratedKeyHolder();
	    namedParameterJdbcTemplate.update(SQL_LICENSE_INSERT, new BeanPropertySqlParameterSource(licenseObj), keyHolder);
	    namedParameterJdbcTemplate.update(SQL_INSERT, fileParameters);
	    return keyHolder.getKey().intValue();
	}

	@Override
	public Group getById(long id) {
		Group group =  jdbcTemplate.queryForObject(SQL_SELECT,
                new GroupRowMapper(), id);
		group.setPermissionList(permissionRepo.getByGroupId(id));
		//group.setUserList(userRepo.getUsersByGroupId(id));
		return group;
	}

	@Override
	public int update(Group entity) {
		return jdbcTemplate.update(
				SQL_UPDATE,
				entity.getName(), entity.getId());
	}

	@Override
	public void delete(long id) {
		jdbcTemplate.update(
				SQL_DELETE,
				id);
	}

	@Override
	public List<Group> getAll() {
		return jdbcTemplate.query(SQL_SELECT_ALL,
                new GroupRowMapper());
	}

	@Override
	public void addUsers(long groupId, List<Long> userIds) {
		for(Long id : userIds) {
			jdbcTemplate.update(
					SQL_ADD_USER,
					id, groupId);
		}
	}

	@Override
	public void assignPermissions(long groupId, long[] permissionIds) {
		for(long id : permissionIds) {
			jdbcTemplate.update(
					SQL_ADD_PERMISSION,
					groupId, id);
		}
	}

	@Override
	public List<Group> getByUserId(long userId) {
		List<Group> groups =  jdbcTemplate.query(SQL_SELECT_BY_USER,
                new GroupRowMapper(), userId);
		for(Group group : groups) {
			group.setPermissionList(permissionRepo.getByGroupId(group.getId()));
		}
		return groups;
	}

}
