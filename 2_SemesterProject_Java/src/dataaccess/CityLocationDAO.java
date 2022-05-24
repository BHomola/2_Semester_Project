package dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.microsoft.sqlserver.jdbc.SQLServerException;

import model.City;
import model.Location;

public class CityLocationDAO implements ICityLocationDAO{
	
	public static City buildCity(ResultSet rs) throws SQLException{
		int id = rs.getInt("CityID");
		String zipcode = rs.getString("Zipcode");
		String name = rs.getString("CityName");
		String country = rs.getString("Country");
		City city = new City(id, zipcode, name, country);
		return city;
	}
	
	public static Location buildLocation(ResultSet rs)throws SQLException{
		int id = rs.getInt("LocationID");
		String name = rs.getString("LocationName");
		String address = rs.getString("Address");
		int cityID = rs.getInt("CityID");
		Location location = new Location(id, name, address, cityID);
		return location;
	}
	
	public static List<City> buildCities(ResultSet rs) throws SQLException{
		ArrayList<City> cityList = new ArrayList<>();
		while(rs.next())
			cityList.add(buildCity(rs));
		return cityList;
	}
	
	public static List<Location> buildLocations(ResultSet rs) throws SQLException{
		ArrayList<Location> locationList = new ArrayList<>();
		while(rs.next())
			locationList.add(buildLocation(rs));
		return locationList;
	}
	
	@Override
	public Collection<City> getAllCities() throws SQLException {
		Connection con = DBConnection.getConnection();
		String sqlStatement = "SELECT * FROM City";
		PreparedStatement pStatement = con.prepareStatement(sqlStatement);
		ResultSet resultSet = pStatement.executeQuery();
		if (resultSet.next() == false)
			return null;
		return buildCities(resultSet);
	}

	@Override
	public Collection<Location> getAllLocations() throws SQLException {
		Connection con = DBConnection.getConnection();
		String sqlStatement = "SELECT * FROM StoreLocation";
		PreparedStatement pStatement = con.prepareStatement(sqlStatement);
		ResultSet resultSet = pStatement.executeQuery();
		if (resultSet.next() == false)
			return null;
		return buildLocations(resultSet);
	}

	@Override
	public City getCityByID(int id) throws SQLException {
		Connection con = DBConnection.getConnection();
		String sqlStatement = " SELECT *  FROM City WHERE CityID = ?";
		PreparedStatement pStatement = con.prepareStatement(sqlStatement);
		pStatement.setInt(1, id);
		ResultSet resultSet = pStatement.executeQuery();
		while (resultSet.next()) {
			return buildCity(resultSet);
		}
		return null;
	}

	@Override
	public Location getLocationByID(int id) throws SQLException {
		Connection con = DBConnection.getConnection();
		String sqlStatement = " SELECT *  FROM StoreLocation "
				+ "WHERE LocationID = ?";
		PreparedStatement pStatement = con.prepareStatement(sqlStatement);
		pStatement.setInt(1, id);
		ResultSet resultSet = pStatement.executeQuery();
		while (resultSet.next()) {
			return buildLocation(resultSet);
		}
		return null;
	}

	@Override
	public int createCity(City city) throws SQLException {
		Connection con = DBConnection.getConnection();
		String sqlStatement = "INSERT INTO City(Zipcode, CityName, Country)"
				+ "VALUES(?,?,?)"
				+ "SELECT SCOPE_IDENTITY() AS generatedID;";
		PreparedStatement pStatement = con.prepareStatement(sqlStatement);
		pStatement.setString(1, city.getZipCode());
		pStatement.setString(2, city.getCityName());
		pStatement.setString(3, city.getCountry());
		ResultSet resultSet = pStatement.executeQuery();
		int generatedID = 0;
		if (resultSet.next())
			generatedID = resultSet.getInt("generatedID");
		return generatedID;
	}

	@Override
	public int createLocation(Location location) throws SQLException {
		Connection con = DBConnection.getConnection();
		String sqlStatement = "INSERT INTO StoreLocation(LocationName, Address, CityID)"
				+ "VALUES(?,?,?)"
				+ "SELECT SCOPE_IDENTITY() AS generatedID;";
		PreparedStatement pStatement = con.prepareStatement(sqlStatement);
		pStatement.setString(1, location.getLocationName());
		pStatement.setString(2, location.getAddress());
		pStatement.setInt(3, location.getCity());
		ResultSet resultSet = pStatement.executeQuery();
		int generatedID = 0;
		if (resultSet.next())
			generatedID = resultSet.getInt("generatedID");
		return generatedID;
	}

	@Override
	public boolean updateCity(City city) throws SQLException {
		Connection con = DBConnection.getConnection();
		boolean success = false;
		try {
		String sqlStatement = "UPDATE City SET Zipcode = ?, CityName = ?,  Country = ? WHERE CityID = ? ";
		PreparedStatement pStatement = con.prepareStatement(sqlStatement);
		pStatement.setString(1, city.getZipCode());
		pStatement.setString(2, city.getCityName());
		pStatement.setString(3, city.getCountry());
		pStatement.setInt(4, city.getId());
		pStatement.executeUpdate();
		success = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return success;
	}

	@Override
	public boolean updateLocation(Location location) throws SQLException {
		Connection con = DBConnection.getConnection();
		boolean success = false;
		try {
		String sqlStatement = "UPDATE StoreLocation SET LocationName = ?,  Address = ? WHERE LocationID = ? ";
		PreparedStatement pStatement = con.prepareStatement(sqlStatement);
		pStatement.setString(1, location.getLocationName());
		pStatement.setString(2, location.getAddress());
		pStatement.setInt(3, location.getId());
		pStatement.executeUpdate();
		success = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return success;
	}

	@Override
	public boolean deleteCity(City city) throws SQLException {
		Connection con = DBConnection.getConnection();
		boolean success = false;
		try {
		String sqlStatement = "DELETE FROM City "
				+ "WHERE CityID = ?";
		PreparedStatement pStatement = con.prepareStatement(sqlStatement);
		pStatement.setInt(1, city.getId());
		pStatement.executeUpdate();
		success = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return success;
	}

	@Override
	public boolean deleteLocation(Location location) throws SQLException {
		Connection con = DBConnection.getConnection();
		boolean success = false;
		try {
		String sqlStatement = "DELETE FROM StoreLocation "
				+ "WHERE LocationID = ?";
		PreparedStatement pStatement = con.prepareStatement(sqlStatement);
		pStatement.setInt(1, location.getId());
		pStatement.executeUpdate();
		success = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return success;
	}

}
