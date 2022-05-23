package dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import model.City;
import model.Customer;
import model.DeliveryStatuses;
import model.Employee;
import model.Invoice;
import model.Location;
import model.Order;
import model.OrderLine;
import model.Person;
import model.StoneProduct;

public class OrderDAO implements IOrderDAO {

	@Override
	public Collection<Order> getAll() throws SQLException {
		Connection con = DBConnection.getConnection();
		String sqlStatement = "";
		PreparedStatement pStatement = con.prepareStatement(sqlStatement);
		pStatement.setInt(1, 0);
		ResultSet resultSet = pStatement.executeQuery();
		if (resultSet.next() == false)
			return null;
		return buildOrders(resultSet);
	}

	@Override
	public Order getByID(int id) throws SQLException {
		Connection con = DBConnection.getConnection();
		String sqlStatement = "";
		PreparedStatement pStatement = con.prepareStatement(sqlStatement);
		pStatement.setInt(1, 0);
		ResultSet resultSet = pStatement.executeQuery();
		if (resultSet.next() == false)
			return null;
		return buildOrder(resultSet);
	}

	@Override
	public int createOrder(Order order) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean updateOrder(Order order) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteOrder(Order order) {
		// TODO Auto-generated method stub
		return false;
	}

	public static Order buildOrder(ResultSet resultSet) throws SQLException {
		int id = resultSet.getInt("OrderID");
		Person customer = PersonDAO.buildPerson(resultSet);
		ArrayList<OrderLine> orderLines = (ArrayList<OrderLine>) buildOrderLines(resultSet);
		double orderPrice = resultSet.getDouble("OrderPrice");
		Person employee = PersonDAO.buildPerson(resultSet);
		Location office = StoneDAO.getLocation(resultSet);
		Invoice invoice = getInvoice(resultSet);
		Date deliveryDate = resultSet.getDate("DeliveryDate");
		String address = resultSet.getString("Address");
		City city = StoneDAO.getCity(resultSet);
		double deposit = resultSet.getDouble("Deposit");
		boolean isPaid = resultSet.getBoolean("IsPaid");
		String customerNote = resultSet.getString("CustomerNoter");
		String updates = resultSet.getString("Updates");
		DeliveryStatuses deliveryStatus = null;
		switch (resultSet.getString("deliveryStatus")) {
		case "accepted":
			deliveryStatus = DeliveryStatuses.ACCEPTED;
		case "inProccess":
			deliveryStatus = DeliveryStatuses.INPROCCESS;
		case "shipping":
			deliveryStatus = DeliveryStatuses.SHIPPING;
		case "delivered":
			deliveryStatus = DeliveryStatuses.DELIVERED;
		default:
			deliveryStatus = DeliveryStatuses.OTHER;
		}
		Order order = new Order(id, (Customer) customer, orderPrice, (Employee) employee, office, invoice,
				deliveryStatus, deliveryDate, address, city, deposit, isPaid, customerNote);
		order.setOrderLines(orderLines);
		order.setUpdates(updates);
		return order;

	}

	public static List<Order> buildOrders(ResultSet resultSet) throws SQLException {
		ArrayList<Order> myList = new ArrayList<>();
		while (resultSet.next())
			myList.add(buildOrder(resultSet));
		return myList;
	}

	@SuppressWarnings("static-access")
	public static OrderLine buildOrderLine(ResultSet resultSet) throws SQLException {
		return new OrderLine((StoneProduct) new StoneDAO().buildStone(resultSet));
	}

	public static List<OrderLine> buildOrderLines(ResultSet resultSet) throws SQLException {
		ArrayList<OrderLine> myList = new ArrayList<>();
		while (resultSet.next())
			myList.add(buildOrderLine(resultSet));
		return myList;
	}

	public static Invoice getInvoice(ResultSet resultSet) throws SQLException {
		Date paymentDate = resultSet.getDate("PaymentDate");
		double VATratio = resultSet.getDouble("VATratio");
		double finalPrice = resultSet.getDouble("FinalPrice");
		return new Invoice(paymentDate, VATratio, finalPrice);
	}
}