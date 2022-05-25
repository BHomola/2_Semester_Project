package model;

import java.sql.Date;

public abstract class Stone extends StoneUnit {
	private Shape shape;
	private double totalSize;


	public Stone(int id, StoneType stoneType, String origin, Supplier supplier, double width, double weight,
			String description, Date createdDate, Location location, Employee employee, StoneUnitStatuses status, Shape shape,
			double totalSize) {
		super(id, stoneType, origin, supplier, width, weight, description, createdDate, location, employee, status);
		this.shape = shape;
		this.totalSize = totalSize;
	}
	
	public Stone(StoneType stoneType, String origin, Supplier supplier, double width, double weight, String description,
			Date createdDate, Location location, Employee employee, StoneUnitStatuses status, Shape shape,
			double totalSize) {
		super( stoneType, origin, supplier, width, weight, description, createdDate, location, employee, status);
		this.shape = shape;
		this.totalSize = totalSize;
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


	@Override
	public String toString() {
		return "Stone [shape=" + shape + ", totalSize=" + totalSize +"] " + super.toString();
	}
	
	
}
