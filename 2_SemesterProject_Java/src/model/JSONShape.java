package model;

import java.awt.Point;
import java.util.ArrayList;

//JSON Struct for seriliazing and deserializing shapes
public class JSONShape {
	public int id;
	public String name;
	public String shapeType;
	public double diameterX;
	public double diameterY;
	public Point[] points;
	public double diameter;
	
}
