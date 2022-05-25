package controller;

import java.sql.SQLException;
import java.util.Collection;

import dataaccess.CityLocationDAO;
import dataaccess.ICityLocationDAO;
import model.City;
import model.Location;

public class LocationCityController {
	ICityLocationDAO dao = new CityLocationDAO();

	public Collection<City> getAllCities() throws SQLException {
		return dao.getAllCities();
	}


	public Collection<Location> getAllLocations() throws SQLException {
		return dao.getAllLocations();
	}


	public City getCityByID(int id) throws SQLException {
		return dao.getCityByID(id);
	}


	public Location getLocationByID(int id) throws SQLException {
		return dao.getLocationByID(id);
	}


	public int createCity(City city) throws SQLException {
		return dao.createCity(city);
	}


	public int createLocation(Location location) throws SQLException {
		return dao.createLocation(location);
	}


	public boolean updateCity(City city) throws SQLException {
		return dao.updateCity(city);
	}


	public boolean updateLocation(Location location) throws SQLException {
		return dao.updateLocation(location);
	}


	public boolean deleteCity(City city) throws SQLException {
		return dao.deleteCity(city);
	}


	public boolean deleteLocation(Location location) throws SQLException {
		return dao.deleteLocation(location);
	}

}
