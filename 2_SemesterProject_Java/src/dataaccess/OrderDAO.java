package dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import model.OrderInfo;
import model.StoneProduct;

public class OrderDAO implements IOrderDAO {

	@Override
	public Collection<OrderInfo> getAll() throws SQLException {
		Connection con = DBConnection.getConnection();
		String sqlStatement = 
				  "SELECT * FROM StoneProduct "
				+ "JOIN VIEW_OrderInfo "
				+ "ON StoneProduct.OrderID = VIEW_OrderInfo.OrderID";
		Statement statement = con.createStatement();
		ResultSet resultSet = statement.executeQuery(sqlStatement);
		return buildOrders(resultSet);
	}
	
	public Collection<OrderInfo> getAllInfo() throws SQLException {
		Connection con = DBConnection.getConnection();
		String sqlStatement = "SELECT * FROM VIEW_OrderInfo ";
		Statement statement = con.createStatement();
		ResultSet resultSet = statement.executeQuery(sqlStatement);
		return getAllInfo(resultSet);
	}

	@Override
	public OrderInfo getByID(int id) throws SQLException {
		Connection con = DBConnection.getConnection();
		String sqlStatement = 
				  "SELECT * FROM StoneProduct "
				+ "JOIN VIEW_OrderInfo "
				+ "ON StoneProduct.OrderID = VIEW_OrderInfo.OrderID "
				+ "WHERE VIEW_OrderInfo.OrderID = ?";
		PreparedStatement pStatement = con.prepareStatement(sqlStatement);
		pStatement.setInt(1, id);
		ResultSet resultSet = pStatement.executeQuery();
		if (resultSet.next() == false)
			return getInfoByID(id);
		return buildOrder(resultSet);
	}
	
	private OrderInfo getInfoByID(int id) throws SQLException {
		Connection con = DBConnection.getConnection();
		String sqlStatement = 
				   "SELECT * FROM VIEW_OrderInfo "
				  +"WHERE VIEW_OrderInfo.OrderID = ?";
		PreparedStatement pStatement = con.prepareStatement(sqlStatement);
		pStatement.setInt(1, id);
		ResultSet resultSet = pStatement.executeQuery();
		if (resultSet.next() == false)
			return null;
		return buildOrder(resultSet);
	}

	public Collection<OrderInfo> getOrdersByCustomerID(int id) throws SQLException {
		Connection con = DBConnection.getConnection();
		String sqlStatement = 
				  "SELECT * FROM StoneProduct "
				+ "JOIN VIEW_OrderInfo "
				+ "ON StoneProduct.OrderID = VIEW_OrderInfo.OrderID "
				+ "WHERE VIEW_OrderInfo.CustomerID = ?";
		PreparedStatement pStatement = con.prepareStatement(sqlStatement);
		pStatement.setInt(1, id);
		ResultSet resultSet = pStatement.executeQuery();
		return buildOrders(resultSet);
	}
	
	public Collection<OrderInfo> getOrdersInfoByCustomerID(int id) throws SQLException {
		Connection con = DBConnection.getConnection();
		String sqlStatement = 
				  "SELECT * FROM VIEW_OrderInfo "
				+ "WHERE VIEW_OrderInfo.CustomerID = ?";
		PreparedStatement pStatement = con.prepareStatement(sqlStatement);
		pStatement.setInt(1, id);
		ResultSet resultSet = pStatement.executeQuery();
		return buildOrders(resultSet);
	}
	
	@Override
	public int createOrder(OrderInfo order) throws SQLException {
		int generatedID = -1;
//		if(order.getInvoice() == null)
//			return generatedID;
		
		Connection con = DBConnection.getConnection();
		String sqlStatement = "INSERT INTO OrderInfo(DeliveryStatus, DeliveryDate, Address, CityID,"
				+ " Deposit, IsPaid, CustomerNote, LocationID, CustomerID, OrderPrice, EmployeeID, Updates)"
				+ " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
				+ " SELECT SCOPE_IDENTITY() AS generatedID";
		PreparedStatement pStatement = con.prepareStatement(sqlStatement);
		pStatement.setInt(1, order.getDeliveryStatus().getID());
		pStatement.setDate(2, order.getDeliveryDate() == null ? 
				null : new java.sql.Date(order.getDeliveryDate().getTime()));
		pStatement.setString(3, order.getAddress());
		pStatement.setInt(4, order.getCity().getId());
		pStatement.setDouble(5, order.getDeposit());
		pStatement.setBoolean(6, order.isPaid());
		pStatement.setString(7, order.getCustomerNote());
		pStatement.setInt(8, order.getOffice().getId());
		pStatement.setInt(9, order.getCustomer().getId());
		pStatement.setDouble(10, order.getOrderPrice());
		pStatement.setInt(11, order.getEmployee().getId());
		pStatement.setString(12, order.getUpdates());
		ResultSet resultSet = pStatement.executeQuery();

		if (resultSet.next())
			generatedID = resultSet.getInt("generatedID");
		
//		sqlStatement = "INSERT INTO Invoice(OrderID, PaymentDate, VATratio, FinalPrice)"
//				+ " VALUES(?,?,?,?)";
//		pStatement = con.prepareStatement(sqlStatement);
//		pStatement.setInt(1, generatedID);
//		pStatement.setDate(2, order.getInvoice().getPaymentDate() == null ? 
//				null : new java.sql.Date(order.getInvoice().getPaymentDate().getTime()));
//		pStatement.setDouble(3, order.getInvoice().getVATratio());
//		pStatement.setDouble(4, order.getInvoice().getFinalPrice());
//		pStatement.execute();
		return generatedID;
	}
	
	public boolean createInvoice(int orderID, Invoice invoice) throws SQLException {
		Connection con = DBConnection.getConnection();
		String sqlStatement = "INSERT INTO Invoice(OrderID, PaymentDate, VATratio, FinalPrice)"
				+ " VALUES(?,?,?,?)";
		PreparedStatement pStatement = con.prepareStatement(sqlStatement);
		pStatement.setInt(1, orderID);
		pStatement.setDate(2, invoice.getPaymentDate() == null ? 
				null : new java.sql.Date(invoice.getPaymentDate().getTime()));
		pStatement.setDouble(3,invoice.getVATratio());
		pStatement.setDouble(4, invoice.getFinalPrice());
		return pStatement.execute();
	}

	@Override
	public boolean updateOrder(OrderInfo order) throws SQLException {
//		deleteOrder(order);
//		createOrder(order);
		Connection con = DBConnection.getConnection();
		String sqlStatement = "UPDATE OrderInfo"
				+ " SET DeliveryStatus = ?,"
				+ " DeliveryDate = ?,"
				+ " Address = ?,"
				+ " CityID = ?,"
				+ " Deposit = ?,"
				+ " IsPaid = ?,"
				+ " CustomerNote = ?,"
				+ " LocationID = ?,"
				+ " CustomerID = ?,"
				+ " OrderPrice = ?,"
				+ " EmployeeID = ?,"
				+ " Updates = ?"
				+ " WHERE OrderID = ?";
		PreparedStatement pStatement = con.prepareStatement(sqlStatement);
		pStatement.setInt(1, order.getDeliveryStatus().getID());
		pStatement.setDate(2, order.getDeliveryDate() == null ? 
				null : new java.sql.Date(order.getDeliveryDate().getTime()));
		pStatement.setString(3, order.getAddress());
		pStatement.setInt(4, order.getCity().getId());
		pStatement.setDouble(5, order.getDeposit());
		pStatement.setBoolean(6, order.isPaid());
		pStatement.setString(7, order.getCustomerNote());
		pStatement.setInt(8, order.getOffice().getId());
		pStatement.setInt(9, order.getCustomer().getId());
		pStatement.setDouble(10, order.getOrderPrice());
		pStatement.setInt(11, order.getEmployee().getId());
		pStatement.setString(12, order.getUpdates());
		pStatement.setInt(13, order.getId());
		int rowsAffected = pStatement.executeUpdate();
		if(rowsAffected == 1)
			return true;
		return false;
	}

	@Override
	public boolean deleteOrder(OrderInfo order) throws SQLException {
		Connection con = DBConnection.getConnection();
		String sqlStatement = "DELETE FROM OrderInfo"
				+ " WHERE OrderID = ?";
		PreparedStatement pStatement = con.prepareStatement(sqlStatement);
		pStatement.setInt(1, order.getId());
		int rowsAffected = pStatement.executeUpdate();
		if(rowsAffected == 1)
			return true;
		return false;
	}

	@SuppressWarnings("unchecked")
	public static OrderInfo buildOrder(ResultSet resultSet) throws SQLException {
		PersonDAO pDAO = new PersonDAO();
		StoneDAO sDAO = new StoneDAO();
		CityLocationDAO clDAO = new CityLocationDAO();
		int id = resultSet.getInt("OrderID");
		Customer customer = (Customer) pDAO.getByID(resultSet.getInt("CustomerID"));
		ArrayList<? extends IStoneUnit> products = sDAO.getStoneProductsByOrderID(id);
		double orderPrice = resultSet.getDouble("OrderPrice");
		Employee employee = (Employee) pDAO.getByID(resultSet.getInt("EmployeeID"));
		Location office = clDAO.getLocationByID(resultSet.getInt("LocationID"));
		Invoice invoice = getInvoice(resultSet);
		DeliveryStatuses deliveryStatus = DeliveryStatuses.GetStatusByID(resultSet.getInt("DeliveryStatus"));
		Date deliveryDate = resultSet.getDate("DeliveryDate");
		String address = resultSet.getString("Address");
		City city = clDAO.getCityByID(resultSet.getInt("CityID"));
		double deposit = resultSet.getDouble("Deposit");
		boolean isPaid = resultSet.getBoolean("IsPaid");
		String customerNote = resultSet.getString("CustomerNote");
		String updates = resultSet.getString("Updates");
		
		OrderInfo order = new OrderInfo(id, (Customer) customer, orderPrice, (Employee) employee, office, invoice,
				deliveryStatus, deliveryDate, address, city, deposit, isPaid, customerNote);
		order.setProducts((List<StoneProduct>) products);
		order.setUpdates(updates);
		return order;
	}

	public static List<OrderInfo> buildOrders(ResultSet resultSet) throws SQLException {
		ArrayList<OrderInfo> myList = new ArrayList<>();
		while (resultSet.next())
			myList.add(buildOrder(resultSet));
		return myList;
	}
	
	private static OrderInfo getInfo(ResultSet resultSet) throws SQLException {
		int id = resultSet.getInt("OrderID");
		Customer customer = new Customer(null, null, null, null, null, null, id, null, id, false, false, id, null);
		customer.setId(resultSet.getInt("CustomerID"));
		Date deliveryDate = resultSet.getDate("DeliveryDate");
		String address = resultSet.getString("Address");
		City city = new City(resultSet.getInt("CityID"), null, null, null);
		double orderPrice = resultSet.getDouble("OrderPrice");
		double deposit = resultSet.getDouble("Deposit");
		boolean isPaid = resultSet.getBoolean("IsPaid");
		DeliveryStatuses deliveryStatus = DeliveryStatuses.GetStatusByID(resultSet.getInt("DeliveryStatus"));
		Employee employee = new Employee(resultSet.getInt("EmployeeID"));
		Location office = new Location(resultSet.getInt("LocationID"), null, null, null);
		Invoice invoice = getInvoice(resultSet);
		return new OrderInfo(id, (Customer) customer, orderPrice, (Employee) employee, office, invoice,
				deliveryStatus, deliveryDate, address, city, deposit, isPaid, null);
	}
	
	public static  Collection<OrderInfo> getAllInfo(ResultSet resultSet) throws SQLException {
		ArrayList<OrderInfo> myList = new ArrayList<>();
		while(resultSet.next())
			myList.add(getInfo(resultSet));
		return myList;
	}

	public static Invoice getInvoice(ResultSet resultSet) throws SQLException {
		Date paymentDate = resultSet.getDate("PaymentDate");
		double VATratio = resultSet.getDouble("VATratio");
		double finalPrice = resultSet.getDouble("FinalPrice");
		return new Invoice(paymentDate, VATratio, finalPrice);
	}
}
