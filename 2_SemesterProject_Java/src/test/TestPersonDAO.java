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
import model.Person;

class TestPersonDAO {
	Customer customer, customerU;
	City city;
	Date date;
	CityLocationDAO clDAO;
	PersonDAO pDAO;

	@BeforeEach
	void setUp() throws Exception {
		pDAO = new PersonDAO();
		clDAO = new CityLocationDAO();
		String sdate = "1978-05-10";
		date = Date.valueOf(sdate);
		customer = new Customer("Alex", "Vesterbro 4", clDAO.getCityByID(2), "4579345", "test@test.com", date, 
				44, "Customer", 0.00, true, false, 0.00, "None");
	}

	@AfterEach
	void tearDown() throws Exception {
	}

/*	@Test
	void testCreatePersonCustomer() {
		try {
			assertEquals(1, pDAO.createPerson(customer));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	*/
	@Test
	void testUpdatePersonCustomer() throws SQLException {
		customerU = new Customer(1, "Alex", "Hasserisvej 432", clDAO.getCityByID(2), "4579345", "test@test.com", date, 
				44, "Customer", 0.00, true, false, 0.00, "None");
		assertTrue(pDAO.updatePerson(customerU));
	}

}
