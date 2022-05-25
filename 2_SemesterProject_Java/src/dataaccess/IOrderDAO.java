package dataaccess;

import java.sql.SQLException;
import java.util.Collection;

import model.OrderInfo;

public interface IOrderDAO {
	public abstract Collection<OrderInfo> getAll() throws SQLException;
	public abstract OrderInfo getByID(int id) throws SQLException;
	public abstract Collection<OrderInfo> getOrdersByCustomerID(int id) throws SQLException;
	public abstract Collection<OrderInfo> getAllInfo() throws SQLException;
	public abstract Collection<OrderInfo> getOrdersInfoByCustomerID(int id) throws SQLException;
	public abstract int createOrder(OrderInfo order) throws SQLException;
	public abstract boolean updateOrder(OrderInfo order) throws SQLException;
	public abstract boolean deleteOrder(OrderInfo order) throws SQLException;
}
