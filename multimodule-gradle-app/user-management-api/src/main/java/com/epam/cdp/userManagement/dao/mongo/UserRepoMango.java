package com.epam.cdp.userManagement.dao.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.epam.cdp.userManagement.model.User;

public interface UserRepoMango  extends MongoRepository<User, String>{

	
}
