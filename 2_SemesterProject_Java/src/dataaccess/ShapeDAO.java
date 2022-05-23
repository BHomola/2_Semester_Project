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
import model.StoneCuttable;
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
		
		
		return null;
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean updateShape(Shape shape) throws SQLException{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteShape(Shape shape) throws SQLException{
		// TODO Auto-generated method stub
		return false;
	}

}
