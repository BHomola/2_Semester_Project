package test;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.SQLException;
import java.util.Date;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;

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

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TestOrderDAO {
	OrderDAO oDAO;
	StoneDAO sDAO;
	PersonDAO pDAO; 
	CityLocationDAO clDAO;
	
	OrderInfo order;
	int genID;
	int sum;
	
	@org.junit.jupiter.api.BeforeAll
	void setUp() {
		oDAO = new OrderDAO();
		sDAO = new StoneDAO();
		pDAO = new PersonDAO();
		clDAO = new CityLocationDAO();
		
		
		try {
			Person person = null;
			Employee employee = null;
			for (Person p : pDAO.getAll()) {
				if(p instanceof Customer && person == null) 
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
			
			sum = oDAO.getAllInfo().size();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Order(1)
	@Test
	void createTest() {
		genID = 0;
		try {
			assertNotEquals(-1, genID = oDAO.createOrder(order));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Order(2)
	@Test
	void getAllTest() {
		try {
			assertEquals(sum+1, oDAO.getAllInfo().size());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Order(3)
	@Test
	void getByIDTest() {
		try {
			assertEquals("TestAddress", oDAO.getByID(genID).getAddress());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Order(4)
	@Test
	void deleteTest() {
		order.setId(genID);
		try {
			assertTrue(oDAO.deleteOrder(order));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	

}
