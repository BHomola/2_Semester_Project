import java.sql.SQLException;

import dataaccess.DBConnection;
import dataaccess.OrderDAO;

public class main {

	public static void main(String[] args) {
		OrderDAO oDAO = new OrderDAO();
		try {
			//starting thread that checks for database connection
			DatabaseCheckThread thread = new DatabaseCheckThread();
			thread.start();
			thread.join();

			System.out.println(oDAO.getOrdersByCustomerID(50));
			System.out.println(oDAO.getByID(2));
			
		} catch (SQLException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
