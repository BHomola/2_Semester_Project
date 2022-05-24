package dataaccess;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import model.*;

import com.google.gson.*;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class JSONShapeDAO implements IShapeDAO {
	private String fileName = "defaultShapes.json";

	@Override
	public ArrayList<Shape> getAllShapes() throws SQLException {
		ArrayList<Shape> shapes = new ArrayList<Shape>();
		JSONShape[] jsonShapes = loadFile();
		for(int i = 0; i < jsonShapes.length; i++) {
			shapes.add(convertJSONShapetoShape(jsonShapes[i]));
		}
		return shapes;
	}

	@Override
	public Shape getById(int id) throws SQLException {

		for(Shape shape : getAllShapes()) {
			if(shape.getId() == id)
				return shape;
		}
		return null;
	}

	@Override
	public int createShape(Shape shape, int id) throws SQLException {
		boolean success = false;
		JSONShape[] jsonShapes = loadFile();
		if(jsonShapes != null) {
			JSONShape[] newJsonShapes = new JSONShape[jsonShapes.length + 1];
			for(int i = 0; i < newJsonShapes.length; i++) {
				
				if(i == newJsonShapes.length-1) {
					newJsonShapes[i] = convertShapeTOJSONShape(shape);
					newJsonShapes[i].id = i;
					continue;
				}
				newJsonShapes[i] = jsonShapes[i];
				newJsonShapes[i].id = i;

			}
			if(saveToFile(newJsonShapes))
				success = true;
		}
		return success == true ? 1 : 0;
	}

	@Override
	public boolean updateShape(Shape shape) throws SQLException {
		boolean success = false;
		ArrayList<Shape> shapes = getAllShapes();
		for(int i = 0; i< shapes.size(); i++) {
			if(shapes.get(i).getId() == shape.getId()) {
				shapes.set(i, shape);
			}
		}
		
		JSONShape[] jsonShapes = convertArrayListToJSONShapes(shapes);
		if(saveToFile(jsonShapes))
			success = true;
		
		return success;
	}

	@Override
	public boolean deleteShape(Shape shape) throws SQLException {
		boolean success = false;
		ArrayList<Shape> shapes = getAllShapes();
		for(int i = 0; i< shapes.size(); i++) {
			if(shapes.get(i).getId() == shape.getId()) {
				shapes.remove(i);
			}
		}
		
		JSONShape[] jsonShapes = convertArrayListToJSONShapes(shapes);
		if(saveToFile(jsonShapes))
			success = true;
		
		return success;
	}

	// Helper methods

	private JSONShape convertShapeTOJSONShape(Shape shape) {
		JSONShape jsonShape = new JSONShape();
		if (shape instanceof CircleShape) {
			jsonShape.name = shape.getName();
			jsonShape.id = shape.getId();
			jsonShape.shapeType = "CircleShape";
			jsonShape.diameter = ((CircleShape) shape).getDiameter();
		}
		if (shape instanceof ElipseShape) {
			jsonShape.name = shape.getName();
			jsonShape.id = shape.getId();
			jsonShape.shapeType = "ElipseShape";
			jsonShape.diameterX = ((ElipseShape) shape).getDiameterX();
			jsonShape.diameterY = ((ElipseShape) shape).getDiameterY();
		}
		if (shape instanceof OtherShape) {
			jsonShape.name = shape.getName();
			jsonShape.id = shape.getId();
			jsonShape.shapeType = "OtherShape";
			ArrayList<ShapePoint> shapePoints = ((OtherShape) shape).getPoints();
			Point[] points = new Point[shapePoints.size()];
			for (int i = 0; i < shapePoints.size(); i++) {
				points[i] = shapePoints.get(i).getData();
			}
			jsonShape.points = points;
		}

		return jsonShape;
	}

	private Shape convertJSONShapetoShape(JSONShape jsonShape) {
		Shape shape = null;
		if (jsonShape.shapeType.equals("CircleShape")) {
			shape = new CircleShape(jsonShape.name, jsonShape.id, jsonShape.diameter);
		}
		if (jsonShape.shapeType.equals("ElipseShape")) {
			shape = new ElipseShape(jsonShape.name, jsonShape.id, jsonShape.diameterX, jsonShape.diameterY);
		}
		if (jsonShape.shapeType.equals("OtherShape")) {
			shape = new OtherShape(jsonShape.name, jsonShape.id);
			ArrayList<ShapePoint> shapePoints = new ArrayList<ShapePoint>();
			for (int i = 0; i < jsonShape.points.length; i++) {
				if (i == 0) { // add starting point
					((OtherShape) shape).addStartingPoint(jsonShape.points[i]);
					continue;
				}
				if (i == jsonShape.points.length - 1) { // add last point
					((OtherShape) shape).addLastPoint(jsonShape.points[i]);
					continue;
				}
				((OtherShape) shape).addPoint(jsonShape.points[i]);
			}
		}

		return shape;
	}
	
	private JSONShape[] convertArrayListToJSONShapes(ArrayList<Shape> shapes) {
		JSONShape[] jsonShapes = new JSONShape[shapes.size()];
		for(int i = 0; i < shapes.size(); i++) {
			jsonShapes[i] = convertShapeTOJSONShape(shapes.get(i));
			jsonShapes[i].id = i;
		}
		return jsonShapes;
	}

	private boolean saveToFile(JSONShape[] jsonShapes) {
		boolean success = false;

		Gson gson = new Gson();
		try {
			File jsonFile = new File(fileName);
			jsonFile.createNewFile();
			FileWriter jsonWriter = new FileWriter(fileName);
			String finalJson = gson.toJson(jsonShapes);
			jsonWriter.write(finalJson);
			jsonWriter.close();
			System.out.println(fileName + " was successfully saved.");
			success = true;
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

		return success;
	}

	private JSONShape[] loadFile() {
		Gson gson = new Gson();
		JSONShape[] loadedShapes = null;
		try {
			File jsonFile = new File(fileName);
			Scanner myReader = new Scanner(jsonFile);
			String jsonData = "";
			while (myReader.hasNextLine()) {
				jsonData += myReader.nextLine();
			}
			myReader.close();

			// Parse JSON back to array of shapes
			loadedShapes = gson.fromJson(jsonData, JSONShape[].class);

			System.out.println(loadedShapes[0].name);

		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
			return null;
		}

		return loadedShapes;
	}

}
