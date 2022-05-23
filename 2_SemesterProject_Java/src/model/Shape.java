package model;

public abstract class Shape {
	
	private String name;
	private int id;
	private double area;
	private double height;
	

	public Shape(String name, int id, double height) {
		super();
		this.name = name;
		this.id = id;
		this.height = height;
		area = 0;
	}
	
	public Shape() {
		
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public double getArea() {
		return area;
	}
	
	public void setArea(double area) {
		this.area = area;
	}
	
	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}
	
	public abstract void calculateArea();
	
}
