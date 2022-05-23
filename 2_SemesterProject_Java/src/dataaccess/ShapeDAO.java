package dataaccess;

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
		String name = resultSet.getString("");
		int id = resultSet.getInt("");
		StoneCuttable stoneCuttable = (StoneCuttable) StoneDAO.getStoneUnit(resultSet);
		
		if(stoneCuttable.getShape() instanceof CircleShape) {
			double diameter = resultSet.getDouble("");
			return new CircleShape(name, id, diameter);
		}
		
		if(stoneCuttable.getShape() instanceof OtherShape) {
			return new OtherShape(name, id);
		}

		if(stoneCuttable.getShape() instanceof ElipseShape) {
			double diameterX = resultSet.getDouble("");
			double diameterY = resultSet.getDouble("");
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Shape getById() throws SQLException{
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Shape getByOrderId() throws SQLException{
		// TODO Auto-generated method stub
		return null;
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
