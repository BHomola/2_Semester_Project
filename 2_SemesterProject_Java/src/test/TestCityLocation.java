package test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dataaccess.CityLocationDAO;
import model.City;
import model.Location;

class TestCityLocation {
	Location location, lUpdated;
	City city, cUpdated;
	CityLocationDAO clDAO;

	@BeforeEach
	void setUp() throws Exception {
		clDAO = new CityLocationDAO();
		city = new City("Chisinau", "MD-2000", "Moldova");
		location = new Location( "Main Storage", "Vesterbro 34", city);
	}
	

	@AfterEach
	void tearDown() throws Exception {
		
	}

	@Test
	void testCreateCity() {
		try {
			assertEquals(1, clDAO.createCity(city));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testCreateLocation() {
		try {
			assertEquals(1, clDAO.createLocation(location));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testUpdateCity() {
		cUpdated = new City(1, "Chisinau", "MD-2001", "Moldova");
		try {
			assertTrue(clDAO.updateCity(cUpdated));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test 
	void testUpdateLocation() {
		lUpdated = new Location(1, "Main Storage", "Vesterbro 35", cUpdated);
		try {
			assertTrue(clDAO.updateLocation(lUpdated));
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	void testGetCityByID() {
		try {
			assertEquals("Chisinau", clDAO.getCityByID(1).getCityName());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	void testGetLocationByID() {
		try {
			assertEquals("Main Storage", clDAO.getLocationByID(1).getLocationName());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	void testDeleteCity() {
		try {
			assertTrue(clDAO.deleteCity(cUpdated));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	void testDeleteLocation() {
		try {
			assertTrue(clDAO.deleteLocation(lUpdated));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
