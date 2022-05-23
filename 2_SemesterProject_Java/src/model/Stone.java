package model;

import java.util.Date;

public abstract class Stone extends StoneUnit {
	private Shape shape;
	private double totalSize;
	private Date birthDate;
	private int orderID;

	public Stone(int id, Material material, String origin, Supplier supplier, double width, double weight,
			String description, Location location, Employee employee, StoneUnitStatuses status, Shape shape,
			double totalSize, Date birth, int orderID) {
		super(id, material, origin, supplier, width, weight, description, location, employee, status);
		this.shape = shape;
		this.totalSize = totalSize;
		this.birthDate = birth;
		this.setOrderID(orderID);
	}
	
	public Shape getShape() {
		return shape;
	}

	public void setShape(Shape shape) {
		this.shape = shape;
	}

	public double getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(double totalSize) {
		this.totalSize = totalSize;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birth) {
		this.birthDate = birth;
	}

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	@Override
	public String toString() {
		return "Stone [shape=" + shape + ", totalSize=" + totalSize + ", birthDate=" + birthDate + ", orderID="
				+ orderID + "]";
	}
	
	
}
