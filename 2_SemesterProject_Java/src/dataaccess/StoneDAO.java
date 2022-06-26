package dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.sql.Date;

import com.microsoft.sqlserver.jdbc.SQLServerException;

import model.City;
import model.Employee;
import model.IStoneUnit;
import model.Location;
import model.StoneMaterial;
import model.Remains;
import model.Shape;
import model.Stone;
import model.StoneCuttable;
import model.StoneProduct;
import model.StoneType;
import model.StoneUnit;
import model.StoneUnitStatuses;
import model.Supplier;

public class StoneDAO implements IStoneDAO {

	// TODO: Finish when the database is implemented.
	@Override
	public ArrayList<IStoneUnit> getAllStoneUnits() throws SQLException {

		// get all stones first
		String query = "SELECT * FROM [VIEW_STONES]";
		PreparedStatement statement = DBConnection.getConnection().prepareStatement(query);
		ResultSet rs = statement.executeQuery();

		ArrayList<IStoneUnit> stoneUnits = buildStoneUnitsPartial(rs);
		
		// assigning parents stones and child stones
		query = "SELECT * FROM StoneCuttable";
		statement = DBConnection.getConnection().prepareStatement(query);
		rs = statement.executeQuery();
		while (rs.next()) {
			int parentStoneID = rs.getInt("StoneID");
			int childStoneID = rs.getInt("StoneUnitID");

			for (IStoneUnit parentStoneUnit : stoneUnits) {
				if (((StoneUnit) parentStoneUnit).getId() == parentStoneID) {
					for (IStoneUnit childStoneUnit : stoneUnits) {
						if (((StoneUnit) childStoneUnit).getId() == childStoneID) {
							((StoneCuttable) parentStoneUnit).addStoneUnit(childStoneUnit);
						}
					}
				}
			}
		}
		
		return stoneUnits;
	}

	public ArrayList<IStoneUnit> getStoneChildren(StoneCuttable stone) throws SQLException {

		String query = "SELECT [VIEW_STONES].* FROM StoneCuttable\r\n"
				+ "JOIN [VIEW_STONES] ON StoneCuttable.StoneUnitID = [VIEW_STONES].StoneUnitID\r\n"
				+ "WHERE StoneID = ?";
		PreparedStatement statement = DBConnection.getConnection().prepareStatement(query);
		statement.setInt(1, stone.getId());
		ResultSet rs = statement.executeQuery();

		return buildStoneUnitsPartial(rs);
	}

	@Override
	public IStoneUnit getStoneUnitByID(int id) throws SQLException {
		String query = "SELECT * FROM [VIEW_STONES] WHERE StoneUnitID=?";
		PreparedStatement statement = DBConnection.getConnection().prepareStatement(query);
		statement.setInt(1, id);
		ResultSet rs = statement.executeQuery();
		IStoneUnit stoneUnit = null;
		if (rs.next()) {
			stoneUnit = buildStoneUnitFull(rs);
			if(stoneUnit instanceof StoneCuttable) {
				((StoneCuttable)stoneUnit).addStoneUnits(getStoneChildren((StoneCuttable)stoneUnit));
			}
			return stoneUnit;
		}
		return null;
	}

	@Override
	public ArrayList<IStoneUnit> getStoneUnitsByStoneMaterial(StoneMaterial stoneMaterial) throws SQLException {
		String query = "SELECT * FROM [VIEW_STONES] WHERE StoneMaterialID=?";
		PreparedStatement statement = DBConnection.getConnection().prepareStatement(query);
		statement.setInt(1, stoneMaterial.getId());
		ResultSet rs = statement.executeQuery();

		return buildStoneUnitsPartial(rs);
	}

	@Override
	public ArrayList<IStoneUnit> getStoneUnitsByStoneType(StoneType stoneType) throws SQLException {
		String query = "SELECT * FROM [VIEW_STONES] WHERE StoneTypeID=?";
		PreparedStatement statement = DBConnection.getConnection().prepareStatement(query);
		statement.setInt(1, stoneType.getId());
		ResultSet rs = statement.executeQuery();

		return buildStoneUnitsPartial(rs);
	}

	@Override
	public ArrayList<IStoneUnit> getStoneProductsByOrderID(int orderID) throws SQLException {
		ArrayList<IStoneUnit> stoneProducts = new ArrayList<IStoneUnit>();

		String query = "SELECT * FROM StoneProduct WHERE OrderID=?";
		PreparedStatement statement = DBConnection.getConnection().prepareStatement(query);
		statement.setInt(1, orderID);
		ResultSet rs = statement.executeQuery();

		while (rs.next()) {
			stoneProducts.add(getStoneUnitByID(rs.getInt("StoneID")));
		}

		return stoneProducts;
	}

	@Override
	public boolean createStone(IStoneUnit stone, IStoneUnit parentStone) throws SQLException {
		boolean success = false;
		Connection dbConnection = DBConnection.getConnection();
		try {
			dbConnection.setAutoCommit(false);

			String query = "INSERT INTO StoneUnit(Width, Weight, Description, Status, StoneType, CreatedDate, Origin, Updates, StoneTypeID, LocationID, SupplierID, EmployeeID)\r\n"
					+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?);" + "SELECT SCOPE_IDENTITY() AS generatedID;";
			PreparedStatement statement = dbConnection.prepareStatement(query);

			StoneUnit stoneUnit = (StoneUnit) stone;
			statement.setInt(1, (int) stoneUnit.getWidth());
			statement.setInt(2, (int) stoneUnit.getWeight());
			statement.setString(3, stoneUnit.getDescription());
			statement.setInt(4, stoneUnit.getStatus().getID());
			statement.setString(5, stoneUnit.getStoneKind());
			statement.setDate(6, stoneUnit.getCreatedDate());
			statement.setString(7, stoneUnit.getOrigin());
			statement.setString(8, stoneUnit.getUpdates());
			statement.setInt(9, stoneUnit.getStoneType().getId());
			statement.setInt(10, stoneUnit.getLocation().getId());
			statement.setInt(11, stoneUnit.getSupplier().getId());
			statement.setInt(12, stoneUnit.getEmployee().getId());

			ResultSet rs = statement.executeQuery();

			int generatedID = 0;

			if (rs.next())
				generatedID = rs.getInt("generatedID");

			statement = dbConnection.prepareStatement(query);
			if (stone instanceof StoneProduct) {
				query = "INSERT INTO Stone (StoneID, TotalSize) VALUES (?,?);\r\n"
						+ "INSERT INTO StoneProduct (StoneID, Price, OrderID) VALUES (?,?,?);";
				statement = dbConnection.prepareStatement(query);
				statement.setInt(1, generatedID);
				statement.setInt(2, (int) ((Stone) stone).getTotalSize());
				statement.setInt(3, generatedID);
				statement.setInt(4, (int) ((StoneProduct) stone).getPrice());
				statement.setInt(5, ((StoneProduct) stone).getOrderID());

			}
			if (stone instanceof StoneCuttable) {
				query = "INSERT INTO Stone (StoneID, TotalSize) VALUES (?,?);\r\n";
				statement = dbConnection.prepareStatement(query);
				statement.setInt(1, generatedID);
				statement.setInt(2, (int) ((Stone) stone).getTotalSize());

			}
			if (stone instanceof Remains) {
				query = "INSERT INTO Remains (RemainsID, Pieces) VALUES (?, ?); ";
				statement = dbConnection.prepareStatement(query);
				statement.setInt(1, generatedID);
				statement.setInt(2, ((Remains) stone).getPieces());
			}
			statement.executeUpdate();

			if (parentStone != null) {
				query = "INSERT INTO CuttableStone (StoneID, StoneUnitID) VALUES (?, ?); ";
				statement = dbConnection.prepareStatement(query);
				statement.setInt(1, ((StoneUnit) parentStone).getId());
				statement.setInt(2, generatedID);
				statement.executeUpdate();
			}

			dbConnection.commit();
			success = true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			dbConnection.rollback();
		} finally {
			dbConnection.setAutoCommit(true);
		}

		return success;
	}

	@Override
	public boolean updateStone(IStoneUnit stone) throws SQLException {
		boolean success = false;
		Connection dbConnection = DBConnection.getConnection();
		try {
			dbConnection.setAutoCommit(false);

			String query = "UPDATE StoneUnit SET Width =?, Weight =?, Description =?,Status=?,StoneType=?,CreatedDate=?, Origin=?, Updates=?, StoneTypeID=?, LocationID=?, SupplierID=?, EmployeeID=? WHERE StoneUnitID=?";
			PreparedStatement statement = dbConnection.prepareStatement(query);

			StoneUnit stoneUnit = (StoneUnit) stone;
			statement.setInt(1, (int) stoneUnit.getWidth());
			statement.setInt(2, (int) stoneUnit.getWeight());
			statement.setString(3, stoneUnit.getDescription());
			statement.setInt(4, stoneUnit.getStatus().getID());
			statement.setString(5, stoneUnit.getStoneKind());
			statement.setDate(6, (java.sql.Date) stoneUnit.getCreatedDate());
			statement.setString(7, stoneUnit.getOrigin());
			statement.setString(8, stoneUnit.getUpdates());
			statement.setInt(9, stoneUnit.getStoneType().getId());
			statement.setInt(10, stoneUnit.getLocation().getId());
			statement.setInt(11, stoneUnit.getSupplier().getId());
			statement.setInt(12, stoneUnit.getEmployee().getId());
			statement.setInt(13, stoneUnit.getId());
			int rowsAffected = statement.executeUpdate();

			if (stone instanceof StoneProduct) {
				query = "UPDATE Stone SET TotalSize=? WHERE StoneID=?; UPDATE StoneProduct SET Price=?, OrderID=? WHERE StoneID=?";
				statement = dbConnection.prepareStatement(query);
				statement.setInt(1, (int) ((Stone) stone).getTotalSize());
				statement.setInt(2, (int) ((Stone) stone).getId());
				statement.setInt(3, (int) ((StoneProduct) stone).getPrice());
				statement.setInt(4, ((StoneProduct) stone).getOrderID());
				statement.setInt(5, (int) ((StoneProduct) stone).getId());

			}
			if (stone instanceof StoneCuttable) {
				query = "UPDATE Stone SET TotalSize=? WHERE StoneID=?";
				statement = dbConnection.prepareStatement(query);
				statement.setInt(1, (int) ((Stone) stone).getTotalSize());
				statement.setInt(2, (int) ((Stone) stone).getId());

			}
			if (stone instanceof Remains) {
				query = "UPDATE Remains SET Pieces=? WHERE RemainsID=?; ";
				statement = dbConnection.prepareStatement(query);
				statement.setInt(1, ((Remains) stone).getPieces());
				statement.setInt(2, ((Remains) stone).getId());
			}
			statement.executeUpdate();
			dbConnection.commit();
			success = true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			dbConnection.rollback();
		} finally {
			dbConnection.setAutoCommit(true);
		}

		return success;
	}

	@Override
	public boolean deleteStone(IStoneUnit stone) throws SQLException {
		return deleteStone(((StoneUnit) stone).getId());
	}

	@Override
	public boolean deleteStone(int stoneID) throws SQLException {
		boolean success = false;
		try {
			String query = "DELETE FROM StoneUnit WHERE StoneUnitID=?";
			PreparedStatement statement = DBConnection.getConnection().prepareStatement(query);
			statement.setInt(1, stoneID);
			statement.executeUpdate();
			success = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return success;
	}

	// Helper Methods

	public static IStoneUnit buildStoneUnitFull(ResultSet resultSet) throws SQLException {
		int id = resultSet.getInt("StoneUnitID");
		String stoneKind = resultSet.getString("StoneType");
		String origin = resultSet.getString("Origin");
		Double width = resultSet.getDouble("Width");
		Double weight = resultSet.getDouble("Weight");
		String description = resultSet.getString("StoneDescription");
		Date createdDate = resultSet.getDate("CreatedDate");
		String updates = resultSet.getString("Updates");
		StoneUnitStatuses status = StoneUnitStatuses.GetStatusByID(resultSet.getInt("Status"));
		// other DAOs access
		Location location = CityLocationDAO.buildLocation(resultSet);
		StoneType stoneType = TypeMaterialDAO.buildType(resultSet);
		Supplier supplier = (Supplier) new PersonDAO().getByID(resultSet.getInt("SupplierID"));
		Employee employee = (Employee) new PersonDAO().getByID(resultSet.getInt("EmployeeID"));

		if (stoneKind.equals("Remains")) {
			int pieces = resultSet.getInt("Pieces");
			return new Remains(id, stoneType, origin, supplier, width, weight, description, createdDate, location,
					employee, status, pieces, updates);
		}

		if (stoneKind.equals("StoneCuttable")) {
			Shape shape = new ShapeDAO().getById(id);
			double totalSize = resultSet.getInt("TotalSize");
			StoneCuttable cuttableStone = new StoneCuttable(id, stoneType, origin, supplier, width, weight, description,
					createdDate, location, employee, status, shape, totalSize,updates);
			return cuttableStone;
		}

		if (stoneKind.equals("StoneProduct")) {
			Shape shape = new ShapeDAO().getById(id);
			double totalSize = resultSet.getInt("TotalSize");
			float price = resultSet.getInt("price");
			int orderID = resultSet.getInt("OrderID");
			StoneProduct stoneProduct = new StoneProduct(id, stoneType, origin, supplier, width, weight, description,
					createdDate, location, employee, status, shape, totalSize, price, orderID, updates);
			return stoneProduct;
		}
		return null;
	}

	public static IStoneUnit buildStoneUnitPartial(ResultSet resultSet) throws SQLException {
		int id = resultSet.getInt("StoneUnitID");
		String stoneKind = resultSet.getString("StoneType");
		String origin = resultSet.getString("Origin");
		Double width = resultSet.getDouble("Width");
		Double weight = resultSet.getDouble("Weight");
		String description = resultSet.getString("StoneDescription");
		Date createdDate = resultSet.getDate("CreatedDate");
		String updates = resultSet.getString("Updates");
		StoneUnitStatuses status = StoneUnitStatuses.GetStatusByID(resultSet.getInt("Status"));
		// other DAOs access
		Location location = CityLocationDAO.buildLocation(resultSet);
		StoneType stoneType = TypeMaterialDAO.buildType(resultSet);
		Supplier supplier = new Supplier(resultSet.getInt("SupplierID"));
		Employee employee = new Employee(resultSet.getInt("EmployeeID"));

		if (stoneKind.equals("Remains")) {
			int pieces = resultSet.getInt("Pieces");
			return new Remains(id, stoneType, origin, supplier, width, weight, description, createdDate, location,
					employee, status, pieces,updates);
		}

		if (stoneKind.equals("StoneCuttable")) {
			Shape shape = new ShapeDAO().getById(id);
			double totalSize = resultSet.getInt("TotalSize");
			StoneCuttable cuttableStone = new StoneCuttable(id, stoneType, origin, supplier, width, weight, description,
					createdDate, location, employee, status, shape, totalSize,updates);
			cuttableStone.setUpdates(updates);
			return cuttableStone;
		}

		if (stoneKind.equals("StoneProduct")) {
			Shape shape = new ShapeDAO().getById(id);
			double totalSize = resultSet.getInt("TotalSize");
			float price = resultSet.getInt("price");
			int orderID = resultSet.getInt("OrderID");
			StoneProduct stoneProduct = new StoneProduct(id, stoneType, origin, supplier, width, weight, description,
					createdDate, location, employee, status, shape, totalSize, price, orderID,updates);
			stoneProduct.setUpdates(updates);
			return stoneProduct;
		}
		return null;
	}

	
	public static ArrayList<IStoneUnit> buildStoneUnitsFull(ResultSet resultSet) throws SQLException {
		ArrayList<IStoneUnit> stoneUnits = new ArrayList<IStoneUnit>();
		while (resultSet.next()) {
			stoneUnits.add(buildStoneUnitFull(resultSet));
		}
		return stoneUnits;
	}
	public static ArrayList<IStoneUnit> buildStoneUnitsPartial(ResultSet resultSet) throws SQLException {
		ArrayList<IStoneUnit> stoneUnits = new ArrayList<IStoneUnit>();
		while (resultSet.next()) {
			stoneUnits.add(buildStoneUnitPartial(resultSet));
		}
		return stoneUnits;
	}


}
