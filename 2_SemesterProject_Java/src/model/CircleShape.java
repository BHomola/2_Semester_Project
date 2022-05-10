package model;

public class CircleShape extends Shape{

	private double diameter;

	public CircleShape(double diameter) {
		super();
		this.diameter = diameter;
	}

	public double getDiameter() {
		return diameter;
	}

	public void setDiameter(double diameter) {
		this.diameter = diameter;
	}
	
}
