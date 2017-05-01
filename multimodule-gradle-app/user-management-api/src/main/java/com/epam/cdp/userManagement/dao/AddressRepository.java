package com.epam.cdp.userManagement.dao;

import com.epam.cdp.userManagement.exception.NoSuchModelException;
import com.epam.cdp.userManagement.model.Address;

public interface AddressRepository extends EntityRepository<Address> {

	long getIdByAddress(Address address) throws NoSuchModelException;
}
