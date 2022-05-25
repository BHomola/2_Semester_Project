package test;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.SQLException;
import java.util.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dataaccess.CityLocationDAO;
import dataaccess.OrderDAO;
import dataaccess.PersonDAO;
import dataaccess.StoneDAO;
import model.City;
import model.Customer;
import model.DeliveryStatuses;
import model.Employee;
import model.Invoice;
import model.Location;
import model.OrderInfo;
import model.Person;

class TestOrderDAO {
	OrderDAO oDAO;
	StoneDAO sDAO;
	PersonDAO pDAO; 
	CityLocationDAO clDAO;
	
	OrderInfo order;
	@BeforeEach
	void setUp() {
		oDAO = new OrderDAO();
		sDAO = new StoneDAO();
		pDAO = new PersonDAO();
		clDAO = new CityLocationDAO();
		
		
		try {
			Person person = null;
			Employee employee = null;
			for (Person p : pDAO.getAll()) {
				if(p instanceof Customer) 
					person = p;
				if(p instanceof Employee)
					employee = (Employee) p;
				if(person != null && employee != null) 
					break;
				}
//			System.out.println(person);
//			System.out.println(employee);
			Location location = clDAO.getLocationByID(1);
			Invoice invoice = new Invoice(new Date(), 0.25);
			City city = clDAO.getCityByID(1);
			
			order = new OrderInfo(0, person, 0, employee, location, invoice, 
					DeliveryStatuses.RECEIVED, null, "TestAddress", city, 0, false, "TestCustomerNote");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	@AfterEach
	void tearDown() throws Exception {
	}
	
	@Test
	void test() {
		try {
			int genID = 0;
			assertNotEquals(-1, genID = oDAO.createOrder(order));
//			System.out.println(genID);
			assertEquals("TestAddress", oDAO.getByID(genID).getAddress());
			order.setId(genID);
			assertTrue(oDAO.deleteOrder(order));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
