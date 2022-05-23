package dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.microsoft.sqlserver.jdbc.SQLServerException;

import model.City;
import model.Employee;
import model.IStoneUnit;
import model.Location;
import model.StoneMaterial;
import model.Remains;
import model.Shape;
import model.Stone;
import model.CuttableStone;
import model.StoneProduct;
import model.StoneType;
import model.StoneUnit;
import model.StoneUnitStatuses;
import model.Supplier;

public class StoneDAO implements IStoneDAO{

	
	//TODO: Finish when the database is implemented.
	@Override
	public ArrayList<IStoneUnit> getAll() throws SQLException {
		
		String query = "SELECT * FROM SelectAllStoneUnits";
		PreparedStatement statement = DBConnection.getConnection().prepareStatement(query);
		ResultSet rs = statement.executeQuery();

		return getStoneUnits(rs);
	}
	
	public ArrayList<IStoneUnit> getStoneChildren(CuttableStone stone) throws SQLException {
		
		String query = "SELECT SelectAllStoneUnits.* FROM CuttableStone\r\n"
				+ "JOIN SelectAllStoneUnits ON CuttableStone.StoneUnitID = SelectAllStoneUnits.StoneUnitID\r\n"
				+ "WHERE StoneID = ?";
		PreparedStatement statement = DBConnection.getConnection().prepareStatement(query);
		statement.setInt(1, stone.getId());
		ResultSet rs = statement.executeQuery();

		return getStoneUnits(rs);
	}

	@Override
	public IStoneUnit getStoneUnitByID(int id) throws SQLException {
		String query = "SELECT * FROM SelectAllStoneUnits WHERE StoneUnitID=?";
		PreparedStatement statement = DBConnection.getConnection().prepareStatement(query);
		statement.setInt(1, id);
		ResultSet rs = statement.executeQuery();
		if(rs.next())
			return getStoneUnit(rs);
		return null;
	}

	@Override
	public ArrayList<IStoneUnit> getStoneUnitsByStoneMaterial(StoneMaterial stoneMaterial) throws SQLException {
		String query = "SELECT * FROM SelectAllStoneUnits WHERE StoneMaterialID=?";
		PreparedStatement statement = DBConnection.getConnection().prepareStatement(query);
		statement.setInt(1, stoneMaterial.getId());
		ResultSet rs = statement.executeQuery();

		return getStoneUnits(rs);
	}

	@Override
	public ArrayList<IStoneUnit> getStoneUnitsByStoneType(StoneType stoneType) throws SQLException {
		String query = "SELECT * FROM SelectAllStoneUnits WHERE StoneTypeID=?";
		PreparedStatement statement = DBConnection.getConnection().prepareStatement(query);
		statement.setInt(1, stoneType.getId());
		ResultSet rs = statement.executeQuery();

		return getStoneUnits(rs);
	}

	@Override
	public boolean createStone(IStoneUnit stone) throws SQLException {
		boolean success = false;
		Connection dbConnection = DBConnection.getConnection();
		try {
			dbConnection.setAutoCommit(false);
			
			String query = "INSERT INTO StoneUnit(Width, Weight, Description, Status, StoneType, CreatedDate, Origin, Updates, StoneTypeID, LocationID, SupplierID, EmployeeID)\r\n"
					+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?);" +
					"SELECT SCOPE_IDENTITY() AS generatedID;";
			PreparedStatement statement = dbConnection.prepareStatement(query);
			
			StoneUnit stoneUnit = (StoneUnit)stone;
			statement.setInt(1, (int)stoneUnit.getWidth());
			statement.setInt(2, (int)stoneUnit.getWeight());
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

			ResultSet rs = statement.executeQuery();

			int generatedID = 0;

			if (rs.next()) 
				generatedID = rs.getInt("generatedID");
			

			if (stone instanceof StoneProduct) {
				query = "INSERT INTO Stone (StoneID, TotalSize) VALUES (?,?);"
						+ "INSERT INTO StoneProduct (StoneID, Price, OrderID) VALUES (?,?,?);";

				statement.setInt(1, generatedID);
				statement.setInt(2, (int) ((Stone)stone).getTotalSize());

			}
			if (stone instanceof CuttableStone) {
				query = "INSERT INTO Equipment (productID, type, description) VALUES (?, ?,?); ";

			}
			if (stone instanceof Remains) {
				query = "INSERT INTO GunReplica (productID, calibre, material) VALUES (?, ?,?); ";

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
	public boolean updateStone(IStoneUnit stone) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteStone(IStoneUnit stone) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
	//Helper Methods

	public static IStoneUnit getStoneUnit(ResultSet resultSet)  throws SQLException {
		int id = resultSet.getInt("StoneUnitID");
		String stoneKind = resultSet.getString("StoneType");
		String origin = resultSet.getString("Origin");
		Double width = resultSet.getDouble("Width");
		Double weight = resultSet.getDouble("Weight");
		String description = resultSet.getString("Description");
		Date createdDate = resultSet.getDate("CreatedDate");
		String updates = resultSet.getString("Updates");
		StoneUnitStatuses status = StoneUnitStatuses.GetStatusByID(resultSet.getInt("Status"));
		//other DAOs access
		Location location = getLocation(resultSet);
		StoneType stoneType = null;// ACCESS TO DAO - MaterialTypeFactory.getStoneType(resultSet);
		Supplier supplier = null;//ACCESS TO DAO(Supplier)PersonDAO.buildPerson(resultSet);
		Employee employee = null;//ACCESS TO DAO(Employee)PersonDAO.buildPerson(resultSet);
		

		if (stoneKind.equals("Remains")) {
			int pieces = resultSet.getInt("Pieces");
			return new Remains(id, stoneType, origin, supplier, width, weight, description, createdDate, location, employee, status,
					pieces);
		}

		if (stoneKind.equals("CuttableStone")) {
			Shape shape = getShape(resultSet);
			double totalSize = resultSet.getInt("TotalSize");
			CuttableStone cuttableStone = new CuttableStone(id, stoneType, origin, supplier, width, weight, description, createdDate, location, employee, status,
					shape, totalSize);
			cuttableStone.setUpdates(updates);
			return cuttableStone;
		}

		if (stoneKind.equals("StoneProduct")) {
			Shape shape = getShape(resultSet);
			double totalSize = resultSet.getInt("TotalSize");
			float price = resultSet.getInt("price");
			int orderID = resultSet.getInt("OrderID");
			StoneProduct stoneProduct = new StoneProduct(id, stoneType, origin, supplier, width, weight, description, createdDate, location, employee,
					status, shape, totalSize, price, orderID);
			stoneProduct.setUpdates(updates);
			return stoneProduct;
		}
		return null;
	}
	
	
	public static ArrayList<IStoneUnit> getStoneUnits(ResultSet resultSet) throws SQLException{
		ArrayList<IStoneUnit> stoneUnits = new ArrayList<IStoneUnit>();
		while(resultSet.next()) {
			stoneUnits.add(getStoneUnit(resultSet));
		}
		return stoneUnits;
	}
	
	
	public static Location getLocation(ResultSet resultSet) throws SQLException{
		return new Location(resultSet.getInt("LocationID"), resultSet.getString("LocationName"),
				resultSet.getString("Address"), getCity(resultSet));
	}

	public static City getCity(ResultSet resultSet) throws SQLException {
		return new City(resultSet.getInt("CityID"), resultSet.getString("CityName"), resultSet.getString("Zipcode"),
				resultSet.getString("Country"));
	}
	
	public static Shape getShape(ResultSet resultSet) throws SQLException {
		
		return null;
	}
	
}
