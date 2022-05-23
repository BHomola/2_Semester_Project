package model;

public abstract class Shape {
	
	private String name;
	private int id;
	

	public Shape(String name, int id) {
		super();
		this.name = name;
		this.id = id;
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
	
	public abstract double calculateArea();
	
}
