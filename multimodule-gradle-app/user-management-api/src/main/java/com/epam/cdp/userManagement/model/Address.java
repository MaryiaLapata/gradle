package com.epam.cdp.userManagement.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table
public class Address implements Serializable {

	private static final long serialVersionUID = 4986561174950596745L;
	@Id
	@Column(name = "address_id")
	@GeneratedValue
	private long id;
	@NotNull
	@Size(min = 2, max = 45)
	@Column
	private String city;
	@NotNull
	@Size(min = 2, max = 45)
	@Column
	private String street;
	@Min(1)
	@Column
	private int houseNumber;
	@Column
	private int flatNumber;

	public Address() {
	}

	public Address(long id) {
		this.id = id;
	}

	public Address(String city, String street, int houseNumber, int flatNumber) {
		this.city = city;
		this.street = street;
		this.houseNumber = houseNumber;
		this.flatNumber = flatNumber;
	}

	public Address(long id, String city, String street, int houseNumber, int flatNumber) {
		this.id = id;
		this.city = city;
		this.street = street;
		this.houseNumber = houseNumber;
		this.flatNumber = flatNumber;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public int getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(int houseNumber) {
		this.houseNumber = houseNumber;
	}

	public int getFlatNumber() {
		return flatNumber;
	}

	public void setFlatNumber(int flatNumber) {
		this.flatNumber = flatNumber;
	}

}
