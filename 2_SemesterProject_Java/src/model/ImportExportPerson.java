package model;

import java.util.ArrayList;
import java.util.Date;

public abstract class ImportExportPerson extends Person{

	private ArrayList<Order> orders;
	private int numberOfOrders;
	private Date dateOfLastOrder;
	private double totalSpend;
	
	public ImportExportPerson(int id, String name, String address, City city, String phoneNumber, String email, Date dateOfBirth,
			int age, String description) {
		
		super(id, name, address, city, phoneNumber, email, dateOfBirth, age, description);
		orders = new ArrayList<Order>();
		numberOfOrders = orders.size();
		dateOfLastOrder = null;
		totalSpend = 0;
	}
	
	public ArrayList<Order> getOrders() {
		return orders;
	}
	public void setOrders(ArrayList<Order> orders) {
		this.orders = orders;
	}
	public int getNumberOfOrders() {
		return numberOfOrders;
	}
	public void setNumberOfOrders(int numberOfOrders) {
		this.numberOfOrders = numberOfOrders;
	}
	public Date getDateOfLastOrder() {
		return dateOfLastOrder;
	}
	public void setDateOfLastOrder(Date dateOfLastOrder) {
		this.dateOfLastOrder = dateOfLastOrder;
	}
	public double getTotalSpend() {
		return totalSpend;
	}
	public void setTotalSpend(double totalSpend) {
		this.totalSpend = totalSpend;
	}
	
	
}
