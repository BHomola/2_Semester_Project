package model;

import java.util.Date;

public class Customer extends Person{

	private double discount;
	private boolean isPremium;
	private boolean isCompany;
	private int ordersCount;
	private Date lastOrderID;
	private double totalsSpends;
	

	public Customer(int id, String name, String address, City city, String phoneNumber, String email, Date dateOfBirth,
			int age, String description, double discount, boolean isPremium, boolean isCompany, int ordersCount,
			Date lastOrderID, double totalsSpends, String note) {
		super(id, name, address, city, phoneNumber, email, dateOfBirth, age, description, note);
		this.discount = discount;
		this.isPremium = isPremium;
		this.isCompany = isCompany;
		this.ordersCount = ordersCount;
		this.lastOrderID = lastOrderID;
		this.totalsSpends = totalsSpends;
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


	public int getOrdersCount() {
		return ordersCount;
	}


	public void setOrdersCount(int ordersCount) {
		this.ordersCount = ordersCount;
	}


	public Date getLastOrderID() {
		return lastOrderID;
	}


	public void setLastOrderID(Date lastOrderID) {
		this.lastOrderID = lastOrderID;
	}


	public double getTotalsSpends() {
		return totalsSpends;
	}


	public void setTotalsSpends(double totalsSpends) {
		this.totalsSpends = totalsSpends;
	}
	
	
}
