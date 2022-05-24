package dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import model.City;
import model.Customer;
import model.Employee;
import model.Person;
import model.Location;
import model.Login;
import model.Person;
import model.Supplier;

public class PersonDAO implements IPersonDao{


	public static Person buildPerson(ResultSet resultSet) throws SQLException{
		int id = resultSet.getInt("PersonID");
		String name = resultSet.getString("Name");
		String address = resultSet.getString("Address");
		String phoneNumber = resultSet.getString("PhoneNumber");
		String email = resultSet.getString("Email");
		String type = resultSet.getString("PersonType");
		Date dateOfBirth = resultSet.getDate("DateOfBirth"); //check if the import is correct
		int age  = resultSet.getInt("Age");
		String description = resultSet.getString("Description");
		City city = StoneDAO.getCity(resultSet);
		String note = resultSet.getString("Note");
		
		if(type.equals("Employee")) {
			String position = resultSet.getString("Position");
			String occupation = resultSet.getString("");
			double salary = resultSet.getDouble("Salary");
			Date startDate = resultSet.getDate("StartDate");
			Location location = null;
			Login login = null; //might not need it here
			return new Employee(id, name, address, city, phoneNumber, email, dateOfBirth, age, description, note, position, occupation, salary, startDate, location, login);
		}
		
		if(type.equals("Customer")) {
			double discount = resultSet.getDouble("discount");
			boolean isPremium = resultSet.getBoolean("IsPremium");
			boolean isCompany = resultSet.getBoolean("IsCompany");
			int ordersCount = resultSet.getInt("OrdersCount");
			int lastOrderID = resultSet.getInt("LastOrderId");
			double totalsSpends = resultSet.getDouble("totalSpends");
			Customer customer = new Customer(id, name, address, city, phoneNumber, email, dateOfBirth, age, description, discount, isPremium, isCompany, totalsSpends, note);
			customer.setLastOrderID(lastOrderID);
			return customer;
		}
		
		if(type.equals("Supplier")) {
			return new Supplier(id, name, address, city, phoneNumber, email, dateOfBirth, age, description, note); 
		}
			

		return null;
	}
	
	public static ArrayList<Person> buildMultiplePeople(ResultSet resultSet) throws SQLException{
		
		ArrayList<Person> people = new ArrayList<Person>();
		while (resultSet.next()) {
			people.add(buildPerson(resultSet));
		}
		return people;
	}

	@Override
	public ArrayList<Person> getAll() throws SQLException{
		Connection connection = DBConnection.getConnection();
		
		String query = "SELECT *\\r\\n"
				+ "FROM Person"
				+ "FULL OUTER JOIN Customer ON Person.PersonID = Customer.CustomerID)\r\n"
				+ "FULL OUTER JOIN Supplier ON Person.PersonID = Supplier.SupplierID)\r\n"
				+ "FULL OUTER JOIN Employee ON Person.PersonID = Employee.EmployeeID)\r\n";
				
		PreparedStatement statement = connection.prepareStatement(query);
		ResultSet resultSet = statement.executeQuery(query);
		if(resultSet.next() == false) return null;		
		return buildMultiplePeople(resultSet);
	}

	@Override
	public Person getByID(int id)  throws SQLException{
		
		Connection connection = DBConnection.getConnection();
		
		String query = "SELECT *\r\n" + "FROM Person\r\n"
				+ "FULL OUTER JOIN Supplier ON Supplier.SupplierID = Person.PersonID\r\n"
				+ "FULL OUTER JOIN Customer ON Customer.CustomerID = Person.PersonID\r\n"
				+ "FULL OUTER JOIN Employee ON Employee.EmployeeID = Person.PersonID\r\n"
				+ "WHERE Person.PersonID = ?";
		
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setInt(1, id);
		ResultSet resultSet = statement.executeQuery();
		if(resultSet.next() == false) return null;
		return buildPerson(resultSet);
	}

	@Override
	public int createPerson(Person person)  throws SQLException{
		
		Connection connection = DBConnection.getConnection();
		
		try {
			connection.setAutoCommit(false);
			String query = "INSERT INTO Person (PersonID, Name, Address, CityID, Note, PhoneNumber, Email, DateOfBirth, Age, Description, PersonType) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);"
					+ "SELECT SCOPE_IDENTITIY() AS generatedID;";
					
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setInt(1, person.getId());
			statement.setString(2, person.getName());
			statement.setString(3, person.getAddress());
			statement.setInt(4, person.getCity().getId());
			statement.setString(5, person.getNote());
			statement.setString(6, person.getPhoneNumber());
			statement.setString(7, person.getEmail());
			statement.setDate(8, (java.sql.Date) person.getDateOfBirth());
			statement.setInt(9, person.getAge());
			statement.setString(10, person.getDescription());
			if (person instanceof Customer) statement.setString(11, "Customer");
			if (person instanceof Supplier) statement.setString(11, "Supplier");
			if (person instanceof Employee) statement.setString(11, "Employee");
			
			ResultSet rs = statement.executeQuery();
			
			int generatedID = 0;
			
			if(rs.next()) generatedID = rs.getInt("generatedID");
			
			if (person instanceof Customer) {
				query = "INSERT INTO Customer (CustomerID, discount, IsPremium, IsCompany, totalSpends, OrdersCount, LastOrderID) VALUES (?, ?, ?, ?, ?, ?, ?)";
				statement = connection.prepareStatement(query);
				statement.setInt(1, ((Customer)person).getId());
				statement.setDouble(2, ((Customer)person).getDiscount());
				statement.setBoolean(3, ((Customer)person).isPremium());
				statement.setBoolean(4, ((Customer)person).isCompany());
				statement.setDouble(5, ((Customer)person).getTotalsSpends());
				statement.setInt(6, ((Customer)person).getOrders().size());
				statement.setInt(7, ((Customer)person).getLastOrderID());
			}
			
			if (person instanceof Supplier) {
				query = "INSERT INTO Supplier (SupplierID) VALUES(?)";
				statement = connection.prepareStatement(query);
				statement.setInt(1, ((Supplier)person).getId());
			}
			
			if (person instanceof Employee) {
				query = "INSERT INTO Employee (EmployeeID, Position, Salary, StartDate, LocationID) VALUES (?, ?, ?, ?, ?, ?, ?)";
				statement = connection.prepareStatement(query);
				statement.setInt(1, ((Employee)person).getId());
				statement.setString(2, ((Employee)person).getPosition());
				statement.setDouble(3, ((Employee)person).getSalary());
				statement.setDate(4, (java.sql.Date) ((Employee)person).getStartDate());
				statement.setInt(5, ((Employee)person).getLocation().getId());
			}
			
			statement.executeUpdate();
			
			connection.commit();
			} catch (SQLException e) {
			System.out.println(e.getMessage());
			connection.rollback();
			} finally {
			connection.setAutoCommit(true);
		}
		return person.getId();
	}

	@Override
	public boolean updatePerson(Person person)  throws SQLException{
		
		boolean success = false;
		Connection connection = DBConnection.getConnection();
		try {
			String query = "UPDATE [dbo].[Person]"
					+ "SET [name] = ?"
					+ "   ,[Address] = ?"
					+ "   ,[CityID] = ?"
					+ "	  ,[Note] = ?"
					+ "   ,[PhoneNumber] = ?"
					+ "   ,[Email] = ?"
					+ "   ,[DateOfBirth] = ?"
					+ "   ,[Age] = ?"
					+ "   ,[Description] = ?"
					+ "   ,[PersonType] = ?"
					+ "WHERE PersonID = ?;";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, person.getName());
			if (person instanceof Customer) statement.setString(3, "Customer");
			if (person instanceof Employee) statement.setString(3, "Employee");
			
			statement.executeUpdate();
			
			if (person instanceof Customer) {
				query = "UPDATE [dbo].[Customer]"
						+ "SET [discount] = ?"
						+ "    ,[IsPremium] = ?"
						+ "    ,[IsCompany] = ?"
			            + "    ,[totalSpends] = ?"
			            + "    ,[OrderCount] = ?"
			            + "    ,[LastOrderID] = ?"
			            + "WHERE CustomerID = ?";
				statement = connection.prepareStatement(query);
				statement.setDouble(1, ((Customer) person).getDiscount());
				statement.setBoolean(2, ((Customer) person).isPremium());
				statement.setBoolean(3, ((Customer) person).isCompany());
				statement.setDouble(4, ((Customer) person).getTotalsSpends());
				statement.setInt(5, ((Customer) person).getOrders().size());
				statement.setInt(6, ((Customer) person).getLastOrderID());
				statement.setInt(7, ((Customer) person).getId());
			}
			
			if (person instanceof Employee) {
				query = "UPDATE [dbo].[Employee]"
						+ "SET [Position] = ?"
						+ "    ,[Salary] = ?"
						+ "    ,[StartDate] = ?"
						+ "    ,[LocationID] = ?"
						+ "WHERE EmployeeID = ?";
				statement.setString(1, ((Employee) person).getPosition());
				statement.setDouble(2, ((Employee) person).getSalary());
				statement.setDate(3, (java.sql.Date) ((Employee) person).getStartDate());
				statement.setInt(4, ((Employee) person).getLocation().getId());
				statement.setInt(5, ((Employee) person).getId());
			}
			
			statement.executeUpdate();
			
			success = true;
			
			connection.commit();
			} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return success;
	}

	@Override
	public boolean deletePerson(Person person)  throws SQLException{
		boolean success = false;
		Connection connection = DBConnection.getConnection();
		
		try {
			String query = "DELETE FROM Person WHERE Person.PersonID = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, person.getId());
			statement.executeUpdate();
			success = true;
		}
		
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return success;
	}

}
