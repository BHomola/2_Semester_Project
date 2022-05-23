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
import model.StoneCuttable;
import model.StoneProduct;
import model.StoneType;
import model.StoneUnit;
import model.StoneUnitStatuses;
import model.Supplier;

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
	public ArrayList<IStoneUnit> getByType(StoneType type) {
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
	
	
	
	//Helper Factory Methods

	public static IStoneUnit getStoneUnit(ResultSet resultSet) throws SQLException {
		int id = resultSet.getInt("StoneUnitID");
		String stoneKind = resultSet.getString("StoneType");
		String origin = resultSet.getString("Origin");
		Double width = resultSet.getDouble("Width");
		Double weight = resultSet.getDouble("Weight");
		String description = resultSet.getString("Description");
		Date birthDate = resultSet.getDate("CreatedDate");
		String updates = resultSet.getString("Updates");
		StoneUnitStatuses status = StoneUnitStatuses.GetStatusByID(resultSet.getInt("Status"));
		//other DAOs access
		Location location = getLocation(resultSet);
		StoneType stoneType = MaterialTypeFactory.getStoneType(resultSet);
		Supplier supplier = (Supplier)PersonDAO.buildPerson(resultSet);
		Employee employee = (Employee)PersonDAO.buildPerson(resultSet);
		

		if (stoneKind.equals("Remains")) {
			int pieces = resultSet.getInt("Pieces");
			return new Remains(id, stoneType, origin, supplier, width, weight, description, location, employee, status,
					pieces);
		}

		if (stoneKind.equals("StoneCuttable")) {
			Shape shape = getShape(resultSet);
			double totalSize = resultSet.getInt("TotalSize");
			StoneCuttable stoneCuttable = new StoneCuttable(id, stoneType, origin, supplier, width, weight, description, location, employee, status,
					shape, totalSize, birthDate);
				stoneCuttable.setUpdates(updates);
		}

		if (stoneKind.equals("StoneProduct")) {
			Shape shape = getShape(resultSet);
			double totalSize = resultSet.getInt("TotalSize");
			float price = resultSet.getInt("price");
			int orderID = resultSet.getInt("OrderID");
			StoneProduct stoneProduct = new StoneProduct(id, stoneType, origin, supplier, width, weight, description, location, employee,
					status, shape, totalSize, birthDate, price, orderID);
			stoneProduct.setUpdates(updates);
			return stoneProduct;
		}
		return null;
	}
	
	
	public ArrayList<IStoneUnit> getStoneUnits(ResultSet resultSet) throws SQLException{
		ArrayList<IStoneUnit> stoneUnits = new ArrayList<IStoneUnit>();
		while(resultSet.next()) {
			stoneUnits.add(getStoneUnit(resultSet));
		}
		
		return stoneUnits;
	}
	
	
	public static Location getLocation(ResultSet resultSet) throws SQLException {
		return new Location(resultSet.getInt("LocationID"), resultSet.getString("LocationName"),
				resultSet.getString("Address"), getCity(resultSet));
	}

	public static City getCity(ResultSet resultSet) throws SQLException {
		return new City(resultSet.getInt("CityID"), resultSet.getString("CityName"), resultSet.getString("Zipcode"),
				resultSet.getString("Country"));
	}
	
	public static Shape getShape(ResultSet resultSet) {
		
		return null;
	}
	
}
