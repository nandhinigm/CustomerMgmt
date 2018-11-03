package com.nab.customer.customermgmt.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "Customer")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String firstname;	
	private String middlename;
	private String lastname;
	private String initials;
	private String title;
	private String address;
	private String suburb;
	private String city;
	private String state;
	private String country;
	private String zipcode;
	private String sex;
	private String maritalstatus;
	private int creditrating;
	private boolean isnabcustomer;
	
	public Customer() {
		
	}
	
	public Customer(Long id, String firstname, String middlename, String lastname, String initials, String title,
			String address, String suburb, 	String city, String state, String country, String zipcode, String sex,
			String maritalstatus, int creditrating, boolean isnabcustomer) {
		setId(id);
		setFirstname(firstname);
		setMiddlename(middlename);
		setLastname(lastname);
		setInitials(initials);
		setTitle(title);
		setAddress(address);
		setSuburb(suburb);
		setCity(city);
		setState(state);
		setCountry(country);
		setZipcode(zipcode);
		setSex(sex);
		setMaritalstatus(maritalstatus);
		setCreditrating(creditrating);
		setIsnabcustomer(isnabcustomer);
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getMiddlename() {
		return middlename;
	}
	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getInitials() {
		return initials;
	}
	public void setInitials(String initials) {
		this.initials = initials;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getSuburb() {
		return suburb;
	}
	public void setSuburb(String suburb) {
		this.suburb = suburb;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getMaritalstatus() {
		return maritalstatus;
	}
	public void setMaritalstatus(String maritalstatus) {
		this.maritalstatus = maritalstatus;
	}
	public int getCreditrating() {
		return creditrating;
	}
	public void setCreditrating(int creditrating) {
		this.creditrating = creditrating;
	}
	public boolean getIsnabcustomer() {
		return isnabcustomer;
	}
	public void setIsnabcustomer(boolean isnabcustomer) {
		this.isnabcustomer = isnabcustomer;
	}
	@Override
	public String toString() {
		return new String("id: " + id + " firstname: " + firstname + " middlename: " + middlename + " lastname: " + lastname + " initials: " + initials
				+ " title: " + title + " address: " + address + " suburb: " + suburb + " city: " + city + " state: " + state + " country: "
				+ country + " zipcode: " + zipcode + " sex: " + sex + " maritalstatus: " + maritalstatus + " creditrating: " + " isnabcustomer: "
				+ isnabcustomer);
	}
}
