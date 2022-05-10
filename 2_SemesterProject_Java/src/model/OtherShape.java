package model;

import java.awt.Point;
import java.util.ArrayList;

public class OtherShape extends Shape{
	
	private ArrayList<ShapePoint> points;
	
	public ArrayList<ShapePoint> getPoints() {
		return points;
	}

	public void setPoints(ArrayList<ShapePoint> points) {
		this.points = points;
	}

	public OtherShape() {
		points = new ArrayList<>();
	}
	
	
	public void addBetween() {
		//TO DO LATER
	}
	
	public void addPoint(Point p) {
		ShapePoint ap = new ShapePoint(p);
		points.add(ap);
		ap.setPrevious(points.get(points.size()-2));
		points.get(points.size()-2).setNext(ap);
		
	}
	
	public void addStartingPoint(Point p) {
	
		ShapePoint ap = new ShapePoint(p);
		points.add(ap);
	}
	
	public void lastPoint(Point p) {
		ShapePoint ap = new ShapePoint(p);
		points.add(ap);
		points.get(0).setPrevious(ap);
		ap.setNext(points.get(0));
	}
	
	public ShapePoint getArrayListPoint(int index) {
		return points.get(index);
	} 
	
}

