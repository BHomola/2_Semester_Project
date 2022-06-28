package dataaccess;

import java.sql.SQLException;
import java.util.Collection;

import model.Login;

public interface ILoginDAO {
	public abstract int authentication(String username, String password) throws SQLException;
	public abstract Collection<Login> getAllLogins() throws SQLException;
	public abstract Login getLoginByID(int id) throws SQLException;
	public abstract boolean createLogin(Login login) throws SQLException;
	public abstract boolean updateLoginUsername(Login login) throws SQLException;
	public abstract boolean updateLoginPassword(Login login) throws SQLException;
	public abstract boolean deleteLogin(Login login) throws SQLException;
}
