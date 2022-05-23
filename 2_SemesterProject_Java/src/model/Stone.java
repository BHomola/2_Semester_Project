package model;

import java.util.Date;

public abstract class Stone extends StoneUnit {
	private Shape shape;
	private double totalSize;
	private Date birthDate;

	public Stone(int id, StoneType stoneType, String origin, Supplier supplier, double width, double weight,
			String description, Location location, Employee employee, StoneUnitStatuses status, Shape shape,
			double totalSize, Date birth) {
		super(id, stoneType, origin, supplier, width, weight, description, location, employee, status);
		this.shape = shape;
		this.totalSize = totalSize;
		this.birthDate = birth;
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

	@Override
	public String toString() {
		return "Stone [shape=" + shape + ", totalSize=" + totalSize + ", birthDate=" + birthDate +"]";
	}
	
	
}
