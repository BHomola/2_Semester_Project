package model;

import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.Order;

public class Customer extends Person {

	private double discount;
	private boolean isPremium;
	private boolean isCompany;
	private ArrayList<Order> orders;
	private int lastOrderID;
	private double totalsSpends;

	public Customer(int id, String name, String address, City city, String phoneNumber, String email, Date dateOfBirth,
			int age, String description, double discount, boolean isPremium, boolean isCompany, double totalsSpends,
			String note) {
		super(id, name, address, city, phoneNumber, email, dateOfBirth, age, description, note);
		this.discount = discount;
		this.isPremium = isPremium;
		this.isCompany = isCompany;
		this.lastOrderID = 0;
		this.totalsSpends = totalsSpends;
		setOrders(new ArrayList<Order>());
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

	public int getLastOrderID() {
		return lastOrderID;
	}

	public void setLastOrderID(int lastOrderID) {
		this.lastOrderID = lastOrderID;
	}

	public double getTotalsSpends() {
		return totalsSpends;
	}

	public void setTotalsSpends(double totalsSpends) {
		this.totalsSpends = totalsSpends;
	}

	public ArrayList<Order> getOrders() {
		return orders;
	}

	public void setOrders(ArrayList<Order> orders) {
		this.orders = orders;
	}

	@Override
	public String toString() {
		return "Customer [discount=" + discount + ", isPremium=" + isPremium + ", isCompany=" + isCompany + ", orders="
				+ orders + ", lastOrderID=" + lastOrderID + ", totalsSpends=" + totalsSpends + "]" + super.toString();
	}


}
