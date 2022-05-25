package Runner;
import java.sql.Date;
import java.sql.SQLException;

import controller.*;
import dataaccess.CityLocationDAO;
import dataaccess.DatabaseCheckThread;
import model.*;



public class main {

	public static void main(String[] args) {
		OrderController oDAO = new OrderController();
		StoneController sDAO = new StoneController();
		PersonController pDAO = new PersonController();
		StoneTypeMaterialController mDAO = new StoneTypeMaterialController();
		LocationCityController lDAO = new LocationCityController();

		try {
			DatabaseCheckThread thread = new DatabaseCheckThread();
			thread.start();

			
			for(IStoneUnit stone : sDAO.getAllStoneUnits()) {
				System.out.println(stone);
			}
			System.out.println("Done!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
