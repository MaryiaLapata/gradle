package com.epam.cdp.userManagement.controller;

import static org.junit.Assert.*;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.epam.cdp.userManagement.UserManagementApp;
import com.epam.cdp.userManagement.model.Address;
import com.epam.cdp.userManagement.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserManagementApp.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@PropertySource("classpath:application-test.yml")
@ActiveProfiles("test")
public class UserControllerTest {

	@Autowired
	private TestRestTemplate restTemplate;
	
	@LocalServerPort
	private int port;

	HttpHeaders headers = new HttpHeaders();
	
	@Test
	public void testGetUser() throws JSONException{
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/users/1"),
				HttpMethod.GET, entity, String.class);

		String expected = "{id:1,firstName:Martin,lastName:Michael,email:Martin.Machael@kronos.com,phone:null,address:{id:2,city:Minsk,street:Staraya,houseNumber:10,flatNumber:115},groupList:null,permissionList:null}";

		JSONAssert.assertEquals(expected, response.getBody(), false);
	}
	
	@Test
	public void testAddUser() throws JSONException {
		User user = new User("FirstNameITest", "lastNameITest", "email@test.com", "1234567890", new Address("Testcity", "Teststreet", 1, 1));
		
		HttpEntity<User> entity = new HttpEntity<User>(user, headers);

		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/users"),
				HttpMethod.POST, entity, String.class);
		
		String userId = response.getBody();
		ResponseEntity<String> newresponse = restTemplate.exchange(
				createURLWithPort("/users/" + userId),
				HttpMethod.GET, entity, String.class);

		assertTrue(newresponse.getBody().contains("lastNameITest"));
	}

	
	@Test
	public void testDeleteUser(){
		HttpEntity<User> entity = new HttpEntity<User>(null, headers);

		restTemplate.exchange(createURLWithPort("/users/1"), HttpMethod.DELETE, entity, String.class);
		String newresponse = restTemplate.getForObject(createURLWithPort("/users/1"),
                String.class);
		assertTrue(newresponse.contains("org.springframework.dao.EmptyResultDataAccessException"));
	}
	
	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}

}
