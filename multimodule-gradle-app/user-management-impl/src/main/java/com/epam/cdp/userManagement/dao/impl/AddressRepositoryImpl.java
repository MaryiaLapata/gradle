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

import com.epam.cdp.userManagement.dao.AddressRepository;
import com.epam.cdp.userManagement.dao.helper.AddressRowMapper;
import com.epam.cdp.userManagement.exception.NoSuchModelException;
import com.epam.cdp.userManagement.model.Address;

@Repository
public class AddressRepositoryImpl implements AddressRepository {
	
	private String SQL_INSERT = "INSERT INTO address(city, street, house_number, flat_number) VALUES (:city, :street, :houseNumber, :flatNumber)";
	private String SQL_SELECT = "SELECT * FROM address WHERE address_id=?";
	private String SQL_SELECT_ALL = "SELECT * FROM address";
	private String SQL_DELETE = "DELETE FROM address WHERE address_id=?";
	private String SQL_UPDATE = "update address set city=?, street=?, house_number=?, flat_number=? WHERE address_id=?";
	private String SQL_SELECT_ID_BY_ADDRESS = "SELECT address_id FROM cdp_users.address WHERE city=? AND street=? AND house_number=? AND flat_number=?";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Override
	public long create(Address entity) {
		SqlParameterSource fileParameters = new BeanPropertySqlParameterSource(entity);
	    KeyHolder keyHolder = new GeneratedKeyHolder();
	    namedParameterJdbcTemplate.update(SQL_INSERT, fileParameters, keyHolder);
	    return keyHolder.getKey().intValue();
	}

	@Override
	public Address getById(long id) {
		return jdbcTemplate.queryForObject(SQL_SELECT,
                new AddressRowMapper(), id);
	}

	@Override
	public int update(Address entity) {
		return jdbcTemplate.update(
				SQL_UPDATE,
				entity.getCity(), entity.getStreet(), entity.getHouseNumber(), entity.getFlatNumber(), entity.getId());
	}

	@Override
	public void delete(long id) {
		jdbcTemplate.update(
				SQL_DELETE,
				id);
	}

	@Override
	public List<Address> getAll() {
		return jdbcTemplate.query(SQL_SELECT_ALL,
                new AddressRowMapper());
	}

	@Override
	public long getIdByAddress(Address address) throws NoSuchModelException {
		long addressId = (long)jdbcTemplate.queryForObject(
				SQL_SELECT_ID_BY_ADDRESS, new Object[] { address.getCity(), address.getStreet(), address.getHouseNumber(), address.getHouseNumber() }, Long.class);
		if (addressId == 0) {
			throw new NoSuchModelException();
		}

		return addressId;
	}

}
