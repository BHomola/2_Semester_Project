package model;

public class ElipseShape extends Shape{

	private double diameterX;
	private double diameterY;
	
	public ElipseShape(double diameterX, double diameterY) {
		super();
		this.diameterX = diameterX;
		this.diameterY = diameterY;
	}
	
	public double getDiameterX() {
		return diameterX;
	}
	
	public void setDiameterX(double diameterX) {
		this.diameterX = diameterX;
	}
	
	public double getDiameterY() {
		return diameterY;
	}
	
	public void setDiameterY(double diameterY) {
		this.diameterY = diameterY;
	}
	
}
