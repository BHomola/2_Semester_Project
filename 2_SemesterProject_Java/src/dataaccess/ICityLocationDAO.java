package dataaccess;

import java.sql.SQLException;
import java.util.Collection;

import model.Location;
import model.City;

public interface ICityLocationDAO {
	public abstract Collection<City> getAllCities() throws SQLException;
	public abstract Collection<Location> getAllLocations() throws SQLException;
	public abstract City getCityByID(int id) throws SQLException;
	public abstract Collection<City> getCityByZipCode(String zipCode) throws SQLException;
	public abstract Location getLocationByID(int id) throws SQLException;
	public abstract int createCity(City city) throws SQLException;
	public abstract int createLocation(Location location) throws SQLException;
	public abstract boolean updateCity(City city) throws SQLException;
	public abstract boolean updateLocation(Location location) throws SQLException;
	public abstract boolean deleteCity(City city) throws SQLException;
	public abstract boolean deleteLocation(Location location) throws SQLException;
}
