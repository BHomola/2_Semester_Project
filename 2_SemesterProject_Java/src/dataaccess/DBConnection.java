package dataaccess;

import java.sql.Connection;
import com.microsoft.sqlserver.jdbc.*;

public class DBConnection {
	
	
	public static Connection getConnection() throws SQLServerException {
		SQLServerDataSource ssds = new SQLServerDataSource();
		ssds.setUser("CSC-CSD-S212_10414384");
		/*
		 * Marek - "CSC-CSD-S212_10407562" 
		 * Daniel - "CSC-CSD-S212_10414383" 
		 * Catalin - "CSC-CSD-S212_10414384" 
		 * Bohus - "CSC-CSD-S212_10407557"
		 */
		ssds.setPassword("Password1!");
		ssds.setServerName("hildur.ucn.dk");
		ssds.setDatabaseName("CSC-CSD-S212_10414384");
		ssds.setEncrypt(false);
		return ssds.getConnection();
	}
}
