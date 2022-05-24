import java.sql.SQLException;

import dataaccess.DBConnection;
import dataaccess.OrderDAO;
import dataaccess.PersonDAO;
import dataaccess.StoneDAO;
import model.IStoneUnit;

public class main {

	public static void main(String[] args) {
		OrderDAO oDAO = new OrderDAO();
		StoneDAO sDAO = new StoneDAO();
		PersonDAO pDAO = new PersonDAO();
		try {
			//starting thread that checks for database connection
			DatabaseCheckThread thread = new DatabaseCheckThread();
			thread.start();

//			System.out.println(pDAO.getByID(2));
//			System.out.println(oDAO.getOrdersByCustomerID(1));
//			System.out.println(oDAO.getByID(1));
//			System.out.println(oDAO.getByID(1).getOffice());
//			System.out.println(oDAO.getAll());
			
			for(IStoneUnit stone : sDAO.getAllStoneUnits()) {
				System.out.println(stone);
			}
			thread.join();
			
		} catch (SQLException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
