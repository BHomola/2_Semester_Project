package model;

import java.util.Date;

public class StoneProduct extends Stone {
	private float price;
	private int orderID;

	public StoneProduct(int id, StoneType stoneType, String origin, Supplier supplier, double width, double weight,
			String description, Date createdDate, Location location, Employee employee, StoneUnitStatuses status, Shape shape,
			double totalSize, float price, int orderID) {
		super(id, stoneType, origin, supplier, width, weight, description, createdDate, location, employee, status, shape, totalSize);
		this.price = price;
		this.orderID = orderID;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}
	
	
	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	@Override
	public int getPieces() {
		return 1;
	}

	@Override
	public String toString() {
		return "StoneProduct [price=" + price + "]";
	}

	
}
