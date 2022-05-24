package dataaccess;

import java.util.ArrayList;

import model.Person;

public interface IPersonDao {
	
	public ArrayList<Person> getAll() throws Exception;
	public Person getByID(int id) throws Exception;
	public int createPerson(Person person) throws Exception;
	public boolean updatePerson(Person person) throws Exception;
	public boolean deletePerson(Person person) throws Exception;
}
