package dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.CircleShape;
import model.ElipseShape;
import model.IStoneUnit;
import model.OtherShape;
import model.Remains;
import model.Shape;
import model.Stone;
import model.StoneUnit;

public class ShapeDAO implements IShapeDAO{

	public Shape buildShape(ResultSet resultSet) throws SQLException {
		String name = resultSet.getString("Name");
		int id = resultSet.getInt("ShapeID");
		String shapeType = resultSet.getString("ShapeType");
		
		if(shapeType.equals("CircleShape")) {
			double diameter = resultSet.getDouble("Diameter");
			return new CircleShape(name, id, diameter);
		}
		
		if(shapeType.equals("OtherShape")) {
			OtherShape otherShape = new OtherShape(name, id);
			
			return otherShape;
		}

		if(shapeType.equals("ElipseShape")) {
			double diameterX = resultSet.getDouble("DiameterX");
			double diameterY = resultSet.getDouble("DiameterY");
			return new ElipseShape(name, id, diameterX, diameterY);
		}
		
		return null;
	}
	
	public ArrayList<Shape> buildShapes(ResultSet resultSet) throws SQLException {
		
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
		
		Shape shape = null;
		String name = null;
		String shapeType = null;
		
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
	public int createShape(Shape shape) throws SQLException{
		
		Connection connection = DBConnection.getConnection();
		
		try {
			connection.setAutoCommit(false);
			String query = "INSERT INTO SHAPE (shapeId, name, shapeType) VALUES (?, ?, ?);"
					+ "SELECT SCOPE_IDENTITIY() AS generatedID;";
					
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setInt(1, shape.getId());
			statement.setString(2, shape.getName());
			if (shape instanceof CircleShape) statement.setString(3, "Circle");
			if (shape instanceof ElipseShape) statement.setString(3, "Elipse");
			if (shape instanceof OtherShape) statement.setString(3, "Other");
			
			ResultSet rs = statement.executeQuery();
			
			int generatedID = 0;
			
			if(rs.next()) generatedID = rs.getInt("generatedID");
			
			if (shape instanceof CircleShape) {
				query = "INSERT INTO CircleShape (shapeId, diameter) VALUES (?, ?);";
				statement = connection.prepareStatement(query);
				statement.setInt(1, generatedID);
				statement.setDouble(2, ((CircleShape) shape).getDiameter());
			}
			
			if (shape instanceof ElipseShape) {
				query = "INSERT INTO ElipseShape (shapeId, diameterX, diameterY) VALUES (?, ?, ?);";
				statement = connection.prepareStatement(query);
				statement.setInt(1, generatedID);
				statement.setDouble(2, ((ElipseShape) shape).getDiameterX());
				statement.setDouble(2, ((ElipseShape) shape).getDiameterY());
			}
			
			if (shape instanceof OtherShape) {
				query = "INSERT INTO ElipseShape (shapeId) VALUES (?);";
				statement = connection.prepareStatement(query);
				statement.setInt(1, generatedID);
				
				int coordinateX = 0;
				int coordinateY = 0;
				for(int i = 0; i < ((OtherShape) shape).getPoints().size(); i++) {
					coordinateX = (int)((OtherShape) shape).getPoints().get(i).getData().getX();
					coordinateX = (int)((OtherShape) shape).getPoints().get(i).getData().getY();
					query = "INSERT INTO ShapePoint (OrderIndex, X, Y, ShapeID) VALUES(?, ?, ?, ?)";
					statement = connection.prepareStatement(query);
					statement.setInt(1, i);
					statement.setInt(2, coordinateX);
					statement.setInt(3, coordinateY);
					statement.setInt(4, generatedID);
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

}
