package dataaccess;

import java.sql.SQLException;
import java.util.Collection;

import model.Order;

public interface IOrderDAO {
	public abstract Collection<Order> getAll() throws SQLException;
	public abstract Order getByID(int id) throws SQLException;
	public abstract int createOrder(Order order) throws SQLException;
	public abstract boolean updateOrder(Order order) throws SQLException;
	public abstract boolean deleteOrder(Order order) throws SQLException;
}
