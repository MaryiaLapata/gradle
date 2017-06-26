package com.epam.cdp.userManagement;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.epam.cdp.userManagement.dao.mongo.AddressRepoMongo;
import com.epam.cdp.userManagement.model.Address;
import com.epam.cdp.userManagement.model.User;

@SpringBootApplication
@ConfigurationProperties
//@EnableConfigurationProperties(ApplicationProperties.class)
//@Import(ApplicationConfig.class)
@EnableMongoRepositories
public class UserManagementApp implements CommandLineRunner{
	
	@Autowired
	public AddressRepoMongo addressrepo;

	
	public UserManagementApp() {
        System.out.println("constructor of App");
    }

	public static void main(String[] args) {
		SpringApplication.run(UserManagementApp.class, args);
		/*new SpringApplicationBuilder(UserManagementApp.class)
		.listeners(new ApplicationStartingListener(), new HelloApplicationListener())
		.run(args);*/
	}

	@Override
	public void run(String... args) throws Exception {
//	addressrepo.deleteAll();
		//addressrepo.save(new Address("1", "NY", "Baker street", 25, 6));
		//addressrepo.save(new Address("2", "NY", "Checken street", 23, 2));
		
		System.out.println("Customers found with findAll():");
		System.out.println("-------------------------------");
		for (Address customer : addressrepo.findAll()) {
			System.out.println(customer);
		}
		System.out.println();

		// fetch an individual customer
		System.out.println("Customer found with findByCity('NY'):");
		System.out.println("--------------------------------");
		System.out.println(addressrepo.findByCity("NY"));

		System.out.println("Customers found with findByStreet('Checken street'):");
		System.out.println("--------------------------------");
		for (Address customer : addressrepo.findByStreet("Checken street")) {
			System.out.println(customer);
		}
	}
	
	/*@PostConstruct
	public void init() {
		System.out.println(properties.toString());
	}*/

}
