package dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import model.Login;

public class LoginDAO implements ILoginDAO {
	
	public static Login buildLogin(ResultSet rs) throws SQLException {
		String username = rs.getString("Username");
		String pass = rs.getString("Password");
		int id = rs.getInt("EmployeeID");
		Login login = new Login(username, pass, id);
		return login;
	}
	
	public static List<Login> buildLogins(ResultSet rs) throws SQLException {
		ArrayList<Login> loginList = new ArrayList<>();
		while(rs.next())
			loginList.add(buildLogin(rs));
		return loginList;
	}
	
	public String authentication(String username) throws SQLException {
		Connection con = DBConnection.getConnection();
		String sqlStatement = " SELECT Password FROM Login"
				+ " WHERE Username = ?";
		PreparedStatement pStatement = con.prepareStatement(sqlStatement);
		pStatement.setString(1, username);
		ResultSet rs = pStatement.executeQuery();
		if (rs.next() == false) {
			return null;
		}
		return rs.getString("Password");
	}
	
	public Collection<Login> getAllLogins() throws SQLException {
		Connection con =  DBConnection.getConnection();
		String sqlStatement = "SELECT * FROM Login";
		PreparedStatement pStatement = con.prepareStatement(sqlStatement);
		ResultSet rs = pStatement.executeQuery();
		if (rs.next() == false) {
			return null;
		}
		return buildLogins(rs);
		}
	
	public Login getLoginByID(int id) throws SQLException {
		Connection con =  DBConnection.getConnection();
		String sqlStatement = " SELECT * FROM Login "
				+ " WHERE EmployeeID = ? ";
		PreparedStatement pStatement = con.prepareStatement(sqlStatement);
		pStatement.setInt(1, id);
		ResultSet rs = pStatement.executeQuery();
		if (rs.next()) {
			return buildLogin(rs);
		}
		return null;
	}
	
	public boolean createLogin(Login login) throws SQLException {
		Connection con = DBConnection.getConnection();
		String sqlStatement = " INSERT INTO Login(Username, Password, EmployeeID) "
				+ " VALUES(?,?,?) ";
		PreparedStatement pStatement = con.prepareStatement(sqlStatement);
		pStatement.setString(1, login.getUsername());
		pStatement.setString(2, login.getPassword());
		pStatement.setInt(3, login.getEmployeeID());
		int rowsAff = pStatement.executeUpdate();
		if(rowsAff == 1)
			return true;
		return false;
		
	}
	
	public boolean updateLoginUsername(Login login) throws SQLException {
		Connection con = DBConnection.getConnection();
		boolean success = false;
		try {
		String sqlStatement = " UPDATE Login "
				+ " SET Username = ? WHERE EmployeeID = ?";
		PreparedStatement pStatement = con.prepareStatement(sqlStatement);
		pStatement.setString(1, login.getUsername());
		pStatement.setInt(2, login.getEmployeeID());
		pStatement.executeUpdate();
		success = true;
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return success;
	}
	
	public boolean updateLoginPassword(Login login) throws SQLException {
		Connection con = DBConnection.getConnection();
		boolean success = false;
		try {
		String sqlStatement = " UPDATE Login "
				+ " SET Password = ? WHERE EmployeeID = ?";
		PreparedStatement pStatement = con.prepareStatement(sqlStatement);
		pStatement.setString(1, login.getPassword());
		pStatement.setInt(2, login.getEmployeeID());
		pStatement.executeUpdate();
		success = true;
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return success;
	}
	
	public boolean deleteLogin(Login login) throws SQLException {
		Connection con = DBConnection.getConnection();
		boolean success = false;
		try {
		String sqlStatement = "DELETE FROM Login "
				+ "WHERE EmployeeID = ?";
		PreparedStatement pStatement = con.prepareStatement(sqlStatement);
		pStatement.setInt(1, login.getEmployeeID());
		pStatement.executeUpdate();
		success = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return success;
	}
	
	}

