package com.quantiphi.employee.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="address")
public class Address implements Serializable{
	@Id
	@Column(name = "address_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int addressId;
	@Column(name = "building_number")
	private int buildingNumber;
	private String street;
	private String city;
	@Column(name = "zip_code")
	private int zipCode;
	
	public Address() {
		super();
	}
	
	
	public Address(int buildingNumber, String street, String city, int zipCode) {
		this.buildingNumber = buildingNumber;
		this.street = street;
		this.city = city;
		this.zipCode = zipCode;
	}


	public int getAddressId() {
		return addressId;
	}
	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}
	public int getBuildingNumber() {
		return buildingNumber;
	}
	public void setBuildingNumber(int buildingNumber) {
		this.buildingNumber = buildingNumber;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getZipCode() {
		return zipCode;
	}
	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}
	
	@Override
	public String toString() {
		return "Address [addressId=" + addressId + ", buildingNumber=" + buildingNumber + ", street=" + street
				+ ", city=" + city + ", zipCode=" + zipCode + "]";
	}
	
}
