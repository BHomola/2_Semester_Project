package model;

import java.util.Date;

public class Employee extends Person{

	private String position;
	private double salary;
	private Date startDate;
	private Location location;
	private Login login;
	
	public Employee(int id, String name, String address, City city, String phoneNumber, String email, Date dateOfBirth,
			int age, String description, String note, String position, double salary, Date startDate,
			Location location, Login login) {
		
		super(id, name, address, city, phoneNumber, email, dateOfBirth, age, description, note);
		this.position = position;
		this.salary = salary;
		this.startDate = startDate;
		this.location = location;
		this.login = login;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}
	
	
	
}
