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
import model.IStoneUnit;
import model.Invoice;
import model.Location;
import model.Order;
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
	public int createOrder(Order order) throws SQLException {
		int generatedID = -1;
		if(order.getInvoice() == null)
			return generatedID;
		
		Connection con = DBConnection.getConnection();
		String sqlStatement = "INSERT INTO OrderInfo(DeliveryStatus, DeliveryDate, Address, CityID,"
				+ " Deposit, IsPaid, CustomerNote, LocationID, PersonID, OrderPrice, EmployeeID, Updates)"
				+ " VALUES(?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement pStatement = con.prepareStatement(sqlStatement);
		pStatement.setInt(1, order.getDeliveryStatus().getID());
		pStatement.setDate(2, (java.sql.Date) order.getDeliveryDate());
		pStatement.setString(3, order.getAddress());
		pStatement.setInt(4, order.getCity().getId());
		pStatement.setDouble(5, order.getDeposit());
		pStatement.setBoolean(6, order.isPaid());
		pStatement.setString(7, order.getCustomerNote());
		pStatement.setInt(8, order.getOffice().getId());
		pStatement.setInt(9, order.getEmployee().getId());
		pStatement.setString(10, order.getUpdates());
		ResultSet resultSet = pStatement.executeQuery();

		if (resultSet.next())
			generatedID = resultSet.getInt("generatedID");
		
		sqlStatement = "INSERT INTO Invoice(OrderID, PaymentDate, VATratio, FinalPrice)"
				+ " VALUES(?,?,?,?)";
		pStatement = con.prepareStatement(sqlStatement);
		pStatement.setInt(0, generatedID);
		pStatement.setDate(1, (java.sql.Date) order.getInvoice().getPaymentDate());
		pStatement.setDouble(2, order.getInvoice().getVATratio());
		pStatement.setDouble(3, order.getInvoice().getFinalPrice());
		return generatedID;
	}

	@Override
	public boolean updateOrder(Order order) throws SQLException {
		deleteOrder(order);
		createOrder(order);
		return false;
	}

	@Override
	public boolean deleteOrder(Order order) throws SQLException {
		Connection con = DBConnection.getConnection();
		String sqlStatement = "DELETE FROM OrderInfo"
				+ " WHERE OrderID = ?";
		PreparedStatement pStatement = con.prepareStatement(sqlStatement);
		pStatement.setInt(0, order.getId());
		int rowsAffected = pStatement.executeUpdate();
		if(rowsAffected == 1)
			return true;
		return false;
	}

	@SuppressWarnings("unchecked")
	public static Order buildOrder(ResultSet resultSet) throws SQLException {
		int id = resultSet.getInt("OrderID");
		Person customer = PersonDAO.buildPerson(resultSet);
		ArrayList<? extends IStoneUnit> products = StoneDAO.getStoneUnits(resultSet);
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
		DeliveryStatuses deliveryStatus = DeliveryStatuses.GetStatusByID(resultSet.getInt("DeliveryStatus"));
		Order order = new Order(id, (Customer) customer, orderPrice, (Employee) employee, office, invoice,
				deliveryStatus, deliveryDate, address, city, deposit, isPaid, customerNote);
		order.setProducts((List<StoneProduct>) products);
		order.setUpdates(updates);
		return order;

	}

	public static List<Order> buildOrders(ResultSet resultSet) throws SQLException {
		ArrayList<Order> myList = new ArrayList<>();
		while (resultSet.next())
			myList.add(buildOrder(resultSet));
		return myList;
	}

	public static Invoice getInvoice(ResultSet resultSet) throws SQLException {
		Date paymentDate = resultSet.getDate("PaymentDate");
		double VATratio = resultSet.getDouble("VATratio");
		double finalPrice = resultSet.getDouble("FinalPrice");
		return new Invoice(paymentDate, VATratio, finalPrice);
	}
}
