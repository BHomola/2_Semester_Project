package model;

import java.util.Date;

public class StoneProduct extends Stone {
	private double price;

	public StoneProduct(int id, Material material, String origin, Supplier supplier, double width, double weight,
			String description, Location location, Employee employee, StoneUnitStatuses status, Shape shape,
			double totalSize, Date birth, int orderID, double price) {
		super(id, material, origin, supplier, width, weight, description, location, employee, status, shape, totalSize,
				birth, orderID);
		this.price = price;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
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
