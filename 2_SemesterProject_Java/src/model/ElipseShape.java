package model;

public class ElipseShape extends Shape{

	private double diameterX;
	private double diameterY;
	
	public ElipseShape(String name, int id, double diameterX, double diameterY, double height) {
		super(name, id, height);
		this.diameterX = diameterX;
		this.diameterY = diameterY;
	}
	
	public ElipseShape() {
		
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
	
	@Override
	public void calculateArea() {
		super.setArea(2*3.14*Math.sqrt((Math.pow(diameterX,2)+Math.pow(diameterY,2))/2));
	}
	
}
