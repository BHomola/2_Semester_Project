package Runner;

import java.awt.Point;
import java.sql.Date;
import java.sql.SQLException;

import dataaccess.JSONShapeDAO;
import dataaccess.StoneDAO;
import dataaccess.TypeMaterialDAO;
import model.*;

public class StoneRunner {

	public static void main(String[] args) throws SQLException {
		
//		JSONShapeDAO jsonDao = new JSONShapeDAO();
//		
//		Shape shape = new ElipseShape("Default Elipse Shape",0,20,30);
//		OtherShape shape1 = new OtherShape("Rectangle",3);
//		shape1.addStartingPoint(new Point(0,0));
//		shape1.addPoint(new Point(30,0));
//		shape1.addPoint(new Point(20,20));
//		shape1.addLastPoint(new Point(0,20));
//		jsonDao.deleteShape(shape1);
		
		
		StoneDAO dao = new StoneDAO();
		TypeMaterialDAO typeDao = new TypeMaterialDAO();
		//IStoneUnit insertStone = new StoneCuttable(0, typeDao.getStoneTypeByID(1), "CZ", new Supplier(22), 20, 30, "Interesting Description", Date.valueOf("2022-05-02"), new Location(1,null,null, new City(5,null,null,null)), new Employee(12), StoneUnits);
		
		
		

		for(IStoneUnit stone : dao.getAllStoneUnits()) {
				System.out.println(stone);
		}
		
		System.out.println("----------------------------------------------------------------------");

		for(IStoneUnit stone : dao.getStoneProductsByOrderID(1)) {
			if(stone!= null)
				System.out.println(stone.toString());
		}
		
		System.out.println("----------------------------------------------------------------------");
		
		for(IStoneUnit stone : dao.getStoneUnitsByStoneMaterial(new StoneMaterial(1, null, null))) {
			if(stone!= null)
				System.out.println(stone.toString());
		}
		
		System.out.println("----------------------------------------------------------------------");
		
		
	}

}
