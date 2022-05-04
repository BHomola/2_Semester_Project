package dataaccess;

import java.sql.Connection;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

public class DataAccess {
	public static Connection getConnection() throws SQLServerException {
		SQLServerDataSource ssds = new SQLServerDataSource();
		ssds.setUser("CSC-CSD-S212_10414384"); // CHANGE IT TO YOUR USER
		/*
		 * Marek - "CSC-CSD-S212_10407562" 
		 * Daniel - "CSC-CSD-S212_10414383" 
		 * Catalin - "CSC-CSD-S212_10414384" 
		 * Bohus - "CSC-CSD-S212_104"
		 */
		ssds.setPassword("Password1!");
		ssds.setServerName("hildur.ucn.dk");
		ssds.setDatabaseName("CSC-CSD-S212_10414384"); // CHANGE IT INTO YOUR DATABASE NAME!
		ssds.setEncrypt(false);
		return ssds.getConnection();
	}
}
