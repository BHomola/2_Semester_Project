package dataaccess;

import java.sql.SQLException;
import java.util.ArrayList;

import model.Shape;

public interface IShapeDAO {

	public ArrayList<Shape> getAllShapes() throws SQLException;
	public Shape getById(int id) throws SQLException;
	public int createShape(Shape shape) throws SQLException;
	public boolean updateShape(Shape shape) throws SQLException;
	public boolean deleteShape(Shape shape) throws SQLException;
}
