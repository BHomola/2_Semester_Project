package model;

public class StoneProduct extends StoneUnit {
	private Shape shape;
	private double totalSize;
	
	public StoneProduct(int id, Material material, String origin, String supplier, double width, double weight,
			String description, Location location, Employee employee, StoneUnitStatuses status, Shape shape,
			double totalSize) {
		super(id, material, origin, supplier, width, weight, description, location, employee, status);
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
		return "StoneProduct [shape=" + shape + ", totalSize=" + totalSize + "]";
	}

	@Override
	public int getPieces() {
		return 1;
	}
	
}
