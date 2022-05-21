package dataaccess;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import model.Employee;
import model.IStoneUnit;
import model.Location;
import model.Material;
import model.Remains;
import model.Shape;
import model.StoneUnitStatuses;
import model.Stone;
import model.StoneUnit;

public class StoneFactory {
	private StoneFactory() {
	}
//UNCHEKCED
	public static IStoneUnit getStone(ResultSet resultSet) throws SQLException {
		String stoneType = resultSet.getString("StoneType");
		Material material = MaterialTypeFactory.getMaterial(resultSet);
		String origin = resultSet.getString("Origin");
		String supplier = resultSet.getString("Supplier");
		Double width = resultSet.getDouble("Width");
		Double weight = resultSet.getDouble("Weight");
		String description = resultSet.getString("Description");
		Location location = LocationCityFactory.getLocation(resultSet);
		Employee employee = PersonDAO.buildPerson(resultSet);
		StoneUnitStatuses status = null;
		switch (resultSet.getString("Status")) {
		case "WIP":
			status = StoneUnitStatuses.WIP;
		case "available":
			status = StoneUnitStatuses.AVAILABLE;
		case "unavailable":
			status = StoneUnitStatuses.UNAVAILABLE;
		}

		if (stoneType.equals("Remains")) {
			int id = resultSet.getInt("RemainsID");
			int pieces = resultSet.getInt("Pieces");
			return new Remains(id, material, origin, supplier, width, weight, description, location, employee, status, pieces);
		}

		if (stoneType.equals("Stone")) {
			int id = resultSet.getInt("StoneID");
			Shape shape = ShapeFactory.getShape(resultSet);
			double totalSize = resultSet.getDouble("TotalSize");
			Date birth = resultSet.getDate("Birth");
			int orderID = resultSet.getInt("orderID");
			return new Stone(id, material, origin, supplier, width, weight, description, location, employee, status, shape,
					totalSize, birth, orderID);
		}
		return null;
	}

	public static ArrayList<IStoneUnit> getMultipleStones(ResultSet resultSet, IStoneUnit obj) throws SQLException {
		ArrayList<IStoneUnit> stones = new ArrayList<IStoneUnit>();
		if (stones.size() == 0) {
			stones.add(getStone(resultSet));
			getMultipleStones(resultSet, obj);
		}
		if (obj != null) {
			for (IStoneUnit ui : ((Stone) obj).getSubUnits())
				if (((StoneUnit) ui).getId() == resultSet.getInt("StoneID")) {
					((Stone) ui).getSubUnits().add(getStone(resultSet));
					break;
				} else {
					getMultipleStones(resultSet, ui);
				}
		}
		while (resultSet.next()) {
			for (int i = 0; i < stones.size(); i++) {
				if (((StoneUnit) stones.get(i)).getId() == resultSet.getInt("StoneID")) {
					((Stone) stones.get(i)).getSubUnits().add(getStone(resultSet));
				} else {
					getMultipleStones(resultSet, stones.get(i));
				}
				stones.add(getStone(resultSet));
			}
		}
		return stones;
	}
}
