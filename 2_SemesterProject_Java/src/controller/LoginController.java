package controller;

import java.sql.SQLException;
import java.util.Collection;

import javax.naming.SizeLimitExceededException;

import dataaccess.ILoginDAO;
import dataaccess.LoginDAO;
import model.Login;

public class LoginController {
	ILoginDAO dao = new LoginDAO();
	
	public int authentication(String username, String password) throws SQLException {
		return dao.authentication(username, password);
	}
	
	public Collection<Login> getAllLogins() throws SQLException {
		return dao.getAllLogins();
	}
	
	public Login getLoginByID(int id) throws SQLException {
		return dao.getLoginByID(id);
	}
	
	public boolean createLogin(Login login) throws SQLException {
		return dao.createLogin(login);
	}
	
	public boolean updateLoginUsername(Login login) throws SQLException {
		return dao.updateLoginUsername(login);
	}
	
	public boolean updateLoginPassword(Login login) throws SQLException {
		return dao.updateLoginPassword(login);
	}
	
	public boolean deleteLogin(Login login) throws SQLException {
		return dao.deleteLogin(login);
	}
 }
