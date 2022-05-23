package dataaccess;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import model.City;
import model.Employee;
import model.IStoneUnit;
import model.Location;
import model.Material;
import model.Remains;
import model.Shape;
import model.ShapeFactory;
import model.StoneCuttable;
import model.StoneProduct;
import model.StoneUnit;
import model.StoneUnitStatuses;
import model.Type;

public class StoneDAO implements IStoneDAO{

	
	//TODO: Finish when the database is implemented.
	@Override
	public ArrayList<IStoneUnit> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IStoneUnit getByID(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<IStoneUnit> getByMaterial(Material material) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<IStoneUnit> getByType(Type type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int createStone(IStoneUnit stone) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean updateStoneUnit(IStoneUnit stone) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteStone(IStoneUnit stone) {
		// TODO Auto-generated method stub
		return false;
	}

	public static IStoneUnit buildStone(ResultSet resultSet) throws SQLException {
		String stoneType = resultSet.getString("StoneType");
		Material material = MaterialTypeFactory.getMaterial(resultSet);
		String origin = resultSet.getString("Origin");
		String supplier = resultSet.getString("Supplier");
		Double width = resultSet.getDouble("Width");
		Double weight = resultSet.getDouble("Weight");
		String description = resultSet.getString("Description");
		int orderID = resultSet.getInt("orderID");
		Location location = getLocation(resultSet);
		Employee employee = PersonDAO.buildPerson(resultSet);
		Date birthDate = resultSet.getDate("birthDate");
		String updates = resultSet.getString("Updates");
		StoneUnitStatuses status = null;
		switch (resultSet.getString("Status")) {
		case "WIP":
			status = StoneUnitStatuses.WIP;
		case "available":
			status = StoneUnitStatuses.AVAILABLE;
		case "unavailable":
			status = StoneUnitStatuses.UNAVAILABLE;
		default:
			status = StoneUnitStatuses.OTHER;
		}

		if (stoneType.equals("Remains")) {
			int id = resultSet.getInt("RemainsID");
			int pieces = resultSet.getInt("Pieces");
			return new Remains(id, material, origin, supplier, width, weight, description, location, employee, status,
					pieces);
		}

		if (stoneType.equals("StoneCuttable")) {
			int id = resultSet.getInt("StoneID");
			Shape shape = ShapeFactory.getShape(resultSet);
			double totalSize = resultSet.getDouble("TotalSize");
			StoneCuttable stoneCuttable = new StoneCuttable(id, material, origin, supplier, width, weight, description, location, employee, status,
					shape, totalSize, birthDate, orderID);
				stoneCuttable.setUpdates(updates);
		}

		if (stoneType.equals("StoneProduct")) {
			int id = resultSet.getInt("StoneProductID");
			Shape shape = ShapeFactory.getShape(resultSet);
			double totalSize = resultSet.getDouble("TotalSize");
			double price = resultSet.getDouble("price");
			StoneProduct stoneProduct = new StoneProduct(id, material, origin, supplier, width, weight, description, location, employee,
					status, shape, totalSize, birthDate, orderID, price);
			stoneProduct.setUpdates(updates);
			return stoneProduct;
		}
		return null;
	}

	public static ArrayList<IStoneUnit> buildStonesLifeCycle(ResultSet resultSet, IStoneUnit obj) throws SQLException {
		ArrayList<IStoneUnit> stones = new ArrayList<IStoneUnit>();
		if (stones.size() == 0) {
			stones.add(buildStone(resultSet));
			buildStonesLifeCycle(resultSet, obj);
		}
		if (obj != null) {
			for (IStoneUnit ui : ((StoneCuttable) obj).getSubUnits())
				if (((StoneUnit) ui).getId() == resultSet.getInt("StoneID")) {
					((StoneCuttable) ui).getSubUnits().add(buildStone(resultSet));
					break;
				} else {
					buildStonesLifeCycle(resultSet, ui);
				}
		}
		while (resultSet.next()) {
			for (int i = 0; i < stones.size(); i++) {
				if (((StoneUnit) stones.get(i)).getId() == resultSet.getInt("StoneID")) {
					((StoneCuttable) stones.get(i)).getSubUnits().add(buildStone(resultSet));
				} else {
					buildStonesLifeCycle(resultSet, stones.get(i));
				}
				stones.add(buildStone(resultSet));
			}
		}
		return stones;
	}

	public static ArrayList<StoneProduct> buildStoneProducts(ResultSet resultSet) throws SQLException {
		ArrayList<StoneProduct> myList = new ArrayList<>();
		while (resultSet.next()) {
			myList.add((StoneProduct) buildStone(resultSet));
		}
		return myList;
	}
	
	public static Location getLocation(ResultSet resultSet) throws SQLException {
		return new Location(resultSet.getInt("LocationID"), resultSet.getString("LocationName"),
				resultSet.getString("Address"), getCity(resultSet));
	}

	public static City getCity(ResultSet resultSet) throws SQLException {
		return new City(resultSet.getInt("CityID"), resultSet.getString("CityName"), resultSet.getString("Zipcode"),
				resultSet.getString("Country"));
	}

	@Override
	public IStoneUnit getByID() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
