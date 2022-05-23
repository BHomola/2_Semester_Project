package model;

public class CircleShape extends Shape{

	private double diameter;

	public CircleShape(String name, int id, double height, double diameter) {
		super(name, id, height);
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
	public void calculateArea() {
		super.setArea(Math.PI * Math.pow(diameter/2, 2));
	}
	
}
