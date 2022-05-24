import model.StoneMaterial;

import java.sql.SQLException;

import dataaccess.DBConnection;
import dataaccess.OrderDAO;
import model.Stone;

public class main {

	public static void main(String[] args) {
		OrderDAO oDAO = new OrderDAO();
		try {
			System.out.println(oDAO.getAll());
			//DBConnection.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
