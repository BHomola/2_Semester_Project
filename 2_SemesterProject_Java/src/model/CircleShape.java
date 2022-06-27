package model;

public class CircleShape extends Shape{

	private double diameter;

	public CircleShape(String name, int id, double diameter) {
		super(name, id);
		this.diameter = diameter;
	}
	
	public CircleShape(double diameter) {
		this.diameter = diameter;
	}
	
	public CircleShape() {
		
	}

	public double getDiameter() {
		return diameter;
	}

	public void setDiameter(double diameter) {
		this.diameter = diameter;
	}
	
	@Override
	public double calculateArea() {
		double area = Math.PI * Math.pow(diameter/2, 2);
		return area;
	}
	
	@Override
	public String toString() {
		return super.getName();
	}
	
}
