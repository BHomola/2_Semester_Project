package model;

import java.util.Date;

public class Customer extends ImportExportPerson{

	private double discount;
	private boolean isPremium;
	private boolean isCompany;
	
	public Customer(int id, String name, String address, City city, String phoneNumber, String email, Date dateOfBirth,
			int age, String description, double discount, boolean isPremium, boolean isCompany) {
		super(id, name, address, city, phoneNumber, email, dateOfBirth, age, description);
		this.discount = discount;
		this.isPremium = isPremium;
		this.isCompany = isCompany;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public boolean isPremium() {
		return isPremium;
	}

	public void setPremium(boolean isPremium) {
		this.isPremium = isPremium;
	}

	public boolean isCompany() {
		return isCompany;
	}

	public void setCompany(boolean isCompany) {
		this.isCompany = isCompany;
	}
	
}
