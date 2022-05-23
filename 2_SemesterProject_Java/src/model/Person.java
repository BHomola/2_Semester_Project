package model;

import java.util.ArrayList;
import java.util.Date;

public abstract class Person {

	private int id;
	private String name;
	private String address;
	private City city;
	private String phoneNumber;
	private String email;
	private Date dateOfBirth;
	private int age;
	private String description;
	private String note;
	
	public Person(int id, String name, String address, City city, String phoneNumber, String email, Date dateOfBirth,
			int age, String description, String note) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.city = city;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.dateOfBirth = dateOfBirth;
		this.age = age;
		this.description = description;
		this.note = note;
	}
	public Person(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
	
}
