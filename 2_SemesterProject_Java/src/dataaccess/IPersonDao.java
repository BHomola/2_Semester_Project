package dataaccess;

import java.sql.SQLException;
import java.util.ArrayList;

import model.Person;

public interface IPersonDao {
	
	public ArrayList<Person> getAll() throws SQLException;
	public ArrayList<Person> getAllSupplier() throws SQLException;
	public ArrayList<Person> getAllEmployee() throws SQLException;
	public ArrayList<Person> getAllCustomer() throws SQLException;
	public Person getByID(int id) throws SQLException;
	public int createPerson(Person person) throws SQLException;
	public boolean updatePerson(Person person) throws SQLException;
	public boolean deletePerson(Person person) throws SQLException;
}
