package controller;

import java.sql.SQLException;
import java.util.ArrayList;

import dataaccess.IPersonDao;
import dataaccess.PersonDAO;
import model.Person;

public class PersonController {
	IPersonDao pDAO = new PersonDAO(); 

	public ArrayList<Person> getAll() throws SQLException {
		return pDAO.getAll();
	}
	
	public ArrayList<Person> getAllSupplier() throws SQLException {
		return pDAO.getAllSupplier();
	}

	public Person getByID(int id) throws SQLException {
		return pDAO.getByID(id); 
	}

	public int createPerson(Person person) throws SQLException {
		return pDAO.createPerson(person);
	}

	public boolean updatePerson(Person person) throws SQLException {
		return pDAO.updatePerson(person);
	}

	public boolean deletePerson(Person person) throws SQLException {
		return pDAO.deletePerson(person);
	}

}
