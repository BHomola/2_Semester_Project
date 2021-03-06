package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class OtherShape extends Shape{
	
	private ArrayList<ShapePoint> points;
	
	
	public OtherShape(String name, int id) {
		super(name, id);
		points = new ArrayList<ShapePoint>();
	}
	
	public OtherShape(String name, ArrayList<ShapePoint> points){
		super(name);
		this.points = points;
	}
	public OtherShape(String name){
		super(name);
		points = new ArrayList<ShapePoint>();
	}
	
	public OtherShape() {
		points = new ArrayList<ShapePoint>();
	}

	public ArrayList<ShapePoint> getPoints() {
		return points;
	}

	public void setPoints(ArrayList<ShapePoint> points) {
		this.points = points;
	}
	
	public void addBetween() {
		//TO DO LATER
	}
	
	public void addPoint(Point point) {
		ShapePoint ap = new ShapePoint(point);
		points.add(ap);
		ap.setPrevious(points.get(points.size()-2));
		points.get(points.size()-2).setNext(ap);
		
	}
	
	public void addStartingPoint(Point point) {
		ShapePoint ap = new ShapePoint(point);
		points.add(ap);
	}
	
	public void addLastPoint(Point point) {
		ShapePoint ap = new ShapePoint(point);
		points.add(ap);
		points.get(0).setPrevious(ap);
		ap.setNext(points.get(0));
	}
	
	@Override
	public double calculateArea() {
		double area = 0;
		int arraySize = getPoints().size();
		
		double[] coordinateX = new double[arraySize];
		double[] coordinateY = new double[arraySize];
		for(int i =0; i < getPoints().size(); i++) {
			coordinateX[i] = getPoints().get(i).getData().getX();
			coordinateY[i] = getPoints().get(i).getData().getY();
			
		}
		
		for(int i = 0; i < arraySize-1; i++) {
			
	        area += (coordinateX[i] * coordinateY[i+1]) - (coordinateX[i+1] * coordinateY[i]);
		}
		area += (Math.abs(area + (coordinateX[0] * coordinateY[arraySize-2]) - (coordinateX[arraySize-2] * coordinateY[0]))) /2;
		return area;
	}
	
	@Override
	public String toString() {
		return super.getName();
	}
}

