package test;

import static org.junit.Assert.assertEquals;
import java.sql.SQLException;
import org.junit.jupiter.api.Test;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import dataaccess.DBConnection;

class TestConnectionRunner {
	
	@Test
	
	void test() throws SQLException {
		try {
			DBConnection.getConnection().isValid(0);
		} catch (SQLServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(true, DBConnection.getConnection().isValid(0));
	}

}
