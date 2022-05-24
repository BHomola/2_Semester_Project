package test;

import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dataaccess.OrderDAO;
import dataaccess.StoneDAO;
import model.Customer;
import model.DeliveryStatuses;
import model.OrderInfo;

class TestOrderDAO {
	OrderDAO oDAO;
	OrderInfo order;
	StoneDAO sDAO;
	@BeforeEach
	void setUp() {
		//PersonDAO pDAO = new PersonDAO();
		oDAO = new OrderDAO();
//		Customer customer = new Customer(0, null, null, null, null, null, null, 0, null, 0, false, false, 0, null, 0, null);
		//City city
		order = new OrderInfo(0, null, 0, null, null, null, DeliveryStatuses.ACCEPTED, null, null, null, 0, false, null);
	}
	
	@Test
	void test() {
		try {
			oDAO.createOrder(order);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
