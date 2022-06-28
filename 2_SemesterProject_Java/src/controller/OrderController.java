package controller;

import java.sql.SQLException;
import java.util.Collection;

import dataaccess.IOrderDAO;
import dataaccess.OrderDAO;
import model.OrderInfo;

public class OrderController {
	IOrderDAO oDAO = new OrderDAO();

	public Collection<OrderInfo> getAll() throws SQLException {
		return oDAO.getAll();
	}
	
	public Collection<OrderInfo> getAllInfo() throws SQLException {
		return oDAO.getAllInfo();
	}
	
	public OrderInfo getByID(int id) throws SQLException {
		return oDAO.getByID(id);
	}
	
	public Collection<OrderInfo> getOrdersByCustomerID(int id) throws SQLException {
		return oDAO.getOrdersByCustomerID(id);
	}
	
	public Collection<OrderInfo> getOrdersByEmployeeID(int id) throws SQLException {
		return oDAO.getOrdersByEmployeeID(id);
	}

	public Collection<OrderInfo> getOrdersInfoByCustomerID(int id) throws SQLException {
		return oDAO.getOrdersInfoByCustomerID(id);
	}
	
	public int createOrder(OrderInfo order) throws SQLException {
		return oDAO.createOrder(order);
	}

	public boolean updateOrder(OrderInfo order) throws SQLException {
		return oDAO.updateOrder(order);
	}

	public boolean deleteOrder(OrderInfo order) throws SQLException {
		return oDAO.deleteOrder(order);
	}

}
