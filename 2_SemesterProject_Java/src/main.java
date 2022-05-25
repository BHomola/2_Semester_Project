import java.sql.SQLException;

import dataaccess.DBConnection;
import dataaccess.OrderDAO;
import dataaccess.PersonDAO;
import dataaccess.ShapeDAO;
import dataaccess.StoneDAO;
import model.IStoneUnit;
import model.OtherShape;
import model.Shape;
import model.ShapePoint;

import java.awt.Point;


public class main {

	public static void main(String[] args) {
		OrderDAO oDAO = new OrderDAO();
		StoneDAO sDAO = new StoneDAO();
		PersonDAO pDAO = new PersonDAO();
		ShapeDAO shapeDAO = new ShapeDAO();
		try {
			//starting thread that checks for database connection
			DatabaseCheckThread thread = new DatabaseCheckThread();
			thread.start();

//			System.out.println(oDAO.getByID(1));
			
			Point p1 = new Point(0, 0);
			Point p2 = new Point(5, 5);
			Point p3 = new Point(4, 20);
			ShapePoint sp1 = new ShapePoint(p1);
			ShapePoint sp2 = new ShapePoint(p2);
			ShapePoint sp3 = new ShapePoint(p3);
			OtherShape otherShape = new OtherShape("other1", 1);
			otherShape.addStartingPoint(p1);
			otherShape.addPoint(p2);
			otherShape.addLastPoint(p3);

			shapeDAO.createShape(otherShape, 1);
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
