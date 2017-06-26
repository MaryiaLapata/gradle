package com.epam.cdp.userManagement.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.epam.cdp.userManagement.dao.AddressRepository;
import com.epam.cdp.userManagement.dao.mongo.AddressRepoMongo;
import com.epam.cdp.userManagement.exception.NoSuchModelException;
import com.epam.cdp.userManagement.model.Address;

//@Repository
public class AddressRepositoryImpl implements AddressRepository {

	private String HQL_SELECT = "SELECT address FROM Address address";
	private String HQL_SELECT_ID_BY_ADDRESS = "SELECT address.id FROM Address address WHERE address.city=:city AND address.street=:street AND address.house_number=:houseNumber AND address.flat_number=:flatNumber";

	@Autowired
	public AddressRepoMongo addressrepo;


	@Override
	public String create(Address entity) {
		Address address = addressrepo.save(entity);
		return address.getId();
	}

	@Override
	public Address getById(String string) {
		return addressrepo.findOne(string);
		//return entityManager.find(Address.class, string);
	}

	@Override
	public Address update(Address entity) {
		if (!addressrepo.exists(entity.getId())) {
			return null;
		}
		return addressrepo.save(entity);
	}

	@Override
	public void delete(String id) throws NoSuchModelException {
		if (!addressrepo.exists(id)) {
			throw new NoSuchModelException(Address.class, id);
		}
		addressrepo.delete(id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Address> getAll() {
		//return (List<Address>) entityManager.createQuery(HQL_SELECT).getResultList();
		return addressrepo.findAll();
	}

	@Override
	public String getIdByAddress(Address address) throws NoSuchModelException {
		/*Query query = entityManager.createQuery(HQL_SELECT_ID_BY_ADDRESS);
		query.setParameter("city", address.getCity());
		query.setParameter("street", address.getStreet());
		query.setParameter("houseNumber", address.getHouseNumber());
		query.setParameter("flatNumber", address.getFlatNumber());
		return (long) query.getSingleResult();*/
		
		return address.getId();
	}

}
