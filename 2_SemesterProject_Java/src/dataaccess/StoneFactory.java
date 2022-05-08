package dataaccess;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import model.IStoneUnit;
import model.Location;
import model.Material;
import model.Remains;
import model.Shape;
import model.Status;
import model.Stone;

public class StoneFactory {
	private StoneFactory() {
	}
//INCOMPLETED
	public static IStoneUnit getStone(ResultSet resultSet) throws SQLException {
		int id = resultSet.getInt("RemainsID");
		Material material = MaterialTypeFactory.getMaterial(resultSet);
		String origin = resultSet.getString("Origin");
		String supplier = resultSet.getString("Supplier");
		Double width = resultSet.getDouble("Width");
		Double weight = resultSet.getDouble("Weight");
		String description = resultSet.getString("Description");
		Location location = LocationCityFactory.getLocation(resultSet);
		Status status = null;
		switch (resultSet.getString("Status")) {
		case "WIP":
			status = Status.WIP;
		case "available":
			status = Status.AVAILABLE;
		case "unavailable":
			status = Status.UNAVAILABLE;
		}
		try {
			int pieces = resultSet.getInt("Pieces");
			return new Remains(id, material, origin, supplier, width, weight, description, location, status, pieces);
		} catch (Exception e) {
			Shape shape = ShapeFactory.getShape(resultSet);
			double totalSize = resultSet.getDouble("TotalSize");
			Date birth = resultSet.getDate("Birth");
			return new Stone(id, material, origin, supplier, width, weight, description, location, status, shape,
					totalSize, birth);
		}
	}
	
	public static ArrayList<IStoneUnit> getMultipleStones(ResultSet resultSet) throws SQLException{
		ArrayList<IStoneUnit> stones = new ArrayList<IStoneUnit>();
		while (resultSet.next()) {
			stones.add(getStone(resultSet));
		}
		return stones;
	}
}
