package controller;

import java.sql.SQLException;
import java.util.ArrayList;

import dataaccess.IShapeDAO;
import dataaccess.JSONShapeDAO;
import model.JSONShape;
import model.Shape;

public class JSONShapeController {

	IShapeDAO shapeDAO = new JSONShapeDAO();
	
	public ArrayList<Shape> getAllShapes() throws SQLException {
		return shapeDAO.getAllShapes();
	}
	
	public Shape getByID(int id) throws SQLException {
		return shapeDAO.getById(id);
	}
	
	public int createShape(Shape shape, int id) throws SQLException {
		return shapeDAO.createShape(shape, id);
	}
	
	public boolean updateShape(Shape shape) throws SQLException {
		return shapeDAO.updateShape(shape);
	}
	
	public boolean deleteShape(Shape shape) throws SQLException {
		return shapeDAO.deleteShape(shape);
	}
	
}
