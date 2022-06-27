package test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.sql.Date;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import dataaccess.CityLocationDAO;
import dataaccess.PersonDAO;
import model.City;
import model.Customer;
import model.Supplier;

class TestPersonDAOnew {
	Customer customer, customerU;
	City city;
	Date date;
	CityLocationDAO clDAO;
	PersonDAO pDAO;
	Supplier supplier;


	@BeforeEach
	void setUp() throws Exception {
		pDAO = new PersonDAO();
	}
	
	@Test
	void testGetAllSupplier() throws SQLException {
		int size = pDAO.getAllSupplier().size();
		assertEquals(10, size);
		
	}
}