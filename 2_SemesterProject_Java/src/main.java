import java.sql.SQLException;

import dataaccess.OrderDAO;

public class main {

	public static void main(String[] args) {
		OrderDAO oDAO = new OrderDAO();
		try {
			
			//starting thread that checks for database connection
			DatabaseCheckThread thread = new DatabaseCheckThread();
			thread.start();
			
			
			System.out.println(oDAO.getAll());
			//DBConnection.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
