package dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.awt.Point;

import model.CircleShape;
import model.ElipseShape;
import model.OtherShape;
import model.Shape;
import model.ShapePoint;


public class ShapeDAO implements IShapeDAO{

	public static Shape buildShape(ResultSet resultSet) throws SQLException {
		String name = resultSet.getString("Name");
		int id = resultSet.getInt("ShapeID");
		String shapeType = resultSet.getString("ShapeType");
		
		if(shapeType.equals("CircleShape")) {
			double diameter = resultSet.getDouble("Diameter");
			return new CircleShape(name, id, diameter);
		}
		
		if(shapeType.equals("OtherShape")) {
			OtherShape otherShape = new OtherShape(name, id);
			
			ArrayList<ShapePoint> shapePoints = getShapePoints(otherShape);
			otherShape.setPoints(shapePoints);
			
			Point startingPoint = otherShape.getPoints().get(0).getData();
			otherShape.addStartingPoint(startingPoint);
			
			for(int i = 1; i < (otherShape.getPoints().size())-1; i++) {
				otherShape.addPoint(otherShape.getPoints().get(i).getData());
			}
			
			Point lastPoint = otherShape.getPoints().get(otherShape.getPoints().size()-1).getData();
			otherShape.addLastPoint(lastPoint);
			
			return otherShape;
		}

		if(shapeType.equals("ElipseShape")) {
			double diameterX = resultSet.getDouble("DiameterX");
			double diameterY = resultSet.getDouble("DiameterY");
			return new ElipseShape(name, id, diameterX, diameterY);
		}
		
		return null;
	}
	
	public static ArrayList<Shape> buildShapes(ResultSet resultSet) throws SQLException {
		
		ArrayList<Shape> shapes = new ArrayList<Shape>();
		while (resultSet.next()) {
			shapes.add(buildShape(resultSet));
		}
		return shapes;
		
	}
	
	@Override
	public ArrayList<Shape> getAllShapes() throws SQLException{
		
		Connection connection = DBConnection.getConnection();
		
		String query = "SELECT *\r\n"
				+ "FROM ((((Shape \r\n"
				+ "FULL OUTER JOIN CircleShape ON Shape.ShapeID = CircleShape.ShapeID)\r\n"
				+ "FULL OUTER JOIN ElipseShape ON Shape.ShapeID = ElipseShape.ShapeID)\r\n"
				+ "FULL OUTER JOIN OtherShape ON Shape.ShapeID = OtherShape.ShapeID)\r\n"
				+ "FULL OUTER JOIN ShapePoint ON OtherShape.ShapeID = ShapePoint.ShapeID)\r\n";
		
		PreparedStatement statement = connection.prepareStatement(query);
		ResultSet resultSet = statement.executeQuery();
		if(resultSet.next() == false) return null;		
		return buildShapes(resultSet);
	}

	@Override
	public Shape getById(int id) throws SQLException{
		
		Connection connection = DBConnection.getConnection();
		
		String query = "SELECT *\r\n"
				+ "FROM ((((Shape \r\n"
				+ "FULL OUTER JOIN CircleShape ON Shape.ShapeID = CircleShape.ShapeID)\r\n"
				+ "FULL OUTER JOIN ElipseShape ON Shape.ShapeID = ElipseShape.ShapeID)\r\n"
				+ "FULL OUTER JOIN OtherShape ON Shape.ShapeID = OtherShape.ShapeID)\r\n"
				+ "FULL OUTER JOIN ShapePoint ON OtherShape.ShapeID = ShapePoint.ShapeID)\r\n"
				+ "WHERE Shape.ShapeID = ?;";
		
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setInt(1, id);
		ResultSet resultSet = statement.executeQuery();
		if(resultSet.next() == false) return null;		
		return buildShape(resultSet);
	}

	@Override
	public int createShape(Shape shape, int id) throws SQLException{
		
		Connection connection = DBConnection.getConnection();
		
		try {
			connection.setAutoCommit(false);
			String query = "INSERT INTO SHAPE (name, shapeType) VALUES (?, ?);";
					
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setString(1, shape.getName());
			if (shape instanceof CircleShape) statement.setString(2, "CircleShape");
			if (shape instanceof ElipseShape) statement.setString(2, "ElipseShape");
			if (shape instanceof OtherShape) statement.setString(2, "OtherShape");
			
			if (shape instanceof CircleShape) {
				query = "INSERT INTO CircleShape (shapeId, diameter) VALUES (?, ?);";
				statement = connection.prepareStatement(query);
				statement.setInt(1, id);
				statement.setInt(2, (int) ((CircleShape) shape).getDiameter());
			}
			
			if (shape instanceof ElipseShape) {
				query = "INSERT INTO ElipseShape (shapeId, diameterX, diameterY) VALUES (?, ?, ?);";
				statement = connection.prepareStatement(query);
				statement.setInt(1, id);
				statement.setInt(2, (int) ((ElipseShape) shape).getDiameterX());
				statement.setInt(3, (int) ((ElipseShape) shape).getDiameterY());
			}
			
			if (shape instanceof OtherShape) {
				query = "INSERT INTO ElipseShape (shapeId) VALUES (?);";
				statement = connection.prepareStatement(query);
				statement.setInt(1, id);
				
				int coordinateX = 0;
				int coordinateY = 0;
				for(int i = 0; i < ((OtherShape) shape).getPoints().size(); i++) {
					coordinateX = (int)((OtherShape) shape).getPoints().get(i).getData().getX();
					coordinateX = (int)((OtherShape) shape).getPoints().get(i).getData().getY();
					query = "INSERT INTO ShapePoint (OrderIndex, X, Y, ShapeID) VALUES(?, ?, ?, ?)";
					statement = connection.prepareStatement(query);
					statement.setInt(1 + (i*4), i);
					statement.setInt(2 + (i*4), coordinateX);
					statement.setInt(3 + (i*4), coordinateY);
					statement.setInt(4 + (i*4), id);
				}
			}
			
			statement.executeUpdate();
			
			connection.commit();
			} catch (SQLException e) {
			System.out.println(e.getMessage());
			connection.rollback();
			} finally {
			connection.setAutoCommit(true);
		}
		
		return shape.getId();
	}

	@Override
	public boolean updateShape(Shape shape) throws SQLException{
		
		boolean success = false;
		Connection connection = DBConnection.getConnection();
		try {
			String query = "UPDATE [dbo].[Shape]"
					+ "SET [name] = ?"
					+ "   ,[shapeType] = ?"
					+ "WHERE ShapeID = ?;";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, shape.getName());
			if (shape instanceof CircleShape) statement.setString(3, "Circle");
			if (shape instanceof ElipseShape) statement.setString(3, "Elipse");
			if (shape instanceof OtherShape) statement.setString(3, "Other");
			
			statement.executeUpdate();
			
			if (shape instanceof CircleShape) {
				query = "UPDATE [dbo].[CircleShape]"
						+ "SET [diameter] = ?"
						+ "WHERE shapeID = ?;";
				statement = connection.prepareStatement(query);
				statement.setDouble(1, ((CircleShape) shape).getDiameter());
				statement.setInt(2, shape.getId());
			}
			if (shape instanceof ElipseShape) {
				query = "UPDATE [dbo].[ElipseShape]"
						+ "SET [diameterX] = ?"
						+ "	  ,[diameterY] = ?"
						+ "WHERE shapeID = ?;";
				statement = connection.prepareStatement(query);
				statement.setDouble(1, ((ElipseShape) shape).getDiameterX());
				statement.setDouble(2, ((ElipseShape) shape).getDiameterX());
				statement.setInt(3, shape.getId());
			}
			
			int coordinateX = 0;
			int coordinateY = 0;
			if (shape instanceof OtherShape) {
				for(int i = 0; i < ((OtherShape) shape).getPoints().size(); i++) {
					coordinateX = (int)((OtherShape) shape).getPoints().get(i).getData().getX();
					coordinateX = (int)((OtherShape) shape).getPoints().get(i).getData().getY();	
					query = "UPDATE [dbo].[ShapePoint]"
							+ "SET [OrderIndex] = ?"
							+ "   ,[x] = ?"
							+ "   ,[Y] = ?"
							+ "WHERE shapeID = ?;";
					statement = connection.prepareStatement(query);
					statement.setDouble(1, i);
					statement.setDouble(2, coordinateX);
					statement.setDouble(3, coordinateY);
					statement.setInt(4, shape.getId());
				}
			}
			
			statement.executeUpdate();
			
			success = true;
			
			connection.commit();
			} catch (SQLException e) {
			System.out.println(e.getMessage());
			
		}
		return success;
	}

	@Override
	public boolean deleteShape(Shape shape) throws SQLException{
		
		boolean success = false;
		Connection connection = DBConnection.getConnection();
		
		try {
			String query = "DELETE FROM Shape WHERE Shape.ShapeID = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, shape.getId());
			statement.executeUpdate();
			success = true;
		}
		
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return success;
	}
	
	public static ShapePoint buildShapePoint(ResultSet resultSet) throws SQLException {
		Point point = new Point(0, 0);
		point.setLocation(resultSet.getInt("X"), resultSet.getInt("Y")); 
		ShapePoint shapePoint = new ShapePoint(point);
		return shapePoint;
	}
	
	public static ArrayList<ShapePoint> buildShapePoints(ResultSet resultSet) throws SQLException {
		
		ArrayList<ShapePoint> shapePoints = new ArrayList<ShapePoint>();
		while (resultSet.next()) {
			shapePoints.add(buildShapePoint(resultSet));
		}
		return shapePoints;
	}
	
	public static ArrayList<ShapePoint> getShapePoints (Shape shape) throws SQLException{
		
		Connection connection = DBConnection.getConnection();
		
		String query = "SELECT *\r\n"
				+ "FROM ShapePoint \r\n"
				+ "FULL OUTER JOIN OtherShape ON ShapePoint.ShapeID = OtherShape.ShapeID \r\n"
				+ "WHERE ShapePoint.ShapeID = ? \r\n"
				+ "ORDER BY ShapePoint.OrderIndex ASC;";
			
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setInt(1, shape.getId());
		ResultSet resultSet = statement.executeQuery();
		if(resultSet.next() == false) return null;		
		return buildShapePoints(resultSet);
	}

}
