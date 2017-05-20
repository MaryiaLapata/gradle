package com.epam.cdp.userManagement.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


import org.springframework.stereotype.Repository;

import com.epam.cdp.userManagement.dao.AddressRepository;
import com.epam.cdp.userManagement.exception.NoSuchModelException;
import com.epam.cdp.userManagement.model.Address;

@Repository
public class AddressRepositoryImpl implements AddressRepository {

	private String HQL_SELECT = "SELECT address FROM Address address";
	private String HQL_SELECT_ID_BY_ADDRESS = "SELECT address.id FROM Address address WHERE address.city=:city AND address.street=:street AND address.house_number=:houseNumber AND address.flat_number=:flatNumber";

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public long create(Address entity) {
		entity.setId(0);
		entityManager.persist(entity);
		entityManager.flush();
		return entity.getId();
	}

	@Override
	public Address getById(long id) {
		return entityManager.find(Address.class, id);
	}

	@Override
	public Address update(Address entity) {
		Address address = getById(entity.getId());
		if (address == null) {
			return null;
		}
		return entityManager.merge(entity);
	}

	@Override
	public void delete(long id) throws NoSuchModelException {
		Address address = getById(id);
		if (address == null) {
			throw new NoSuchModelException(Address.class, id);
		}
		entityManager.remove(address);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Address> getAll() {
		return (List<Address>) entityManager.createQuery(HQL_SELECT).getResultList();
	}

	@Override
	public long getIdByAddress(Address address) throws NoSuchModelException {
		Query query = entityManager.createQuery(HQL_SELECT_ID_BY_ADDRESS);
		query.setParameter("city", address.getCity());
		query.setParameter("street", address.getStreet());
		query.setParameter("houseNumber", address.getHouseNumber());
		query.setParameter("flatNumber", address.getFlatNumber());
		return (long) query.getSingleResult();
	}

}
