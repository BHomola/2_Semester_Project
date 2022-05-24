package Runner;

import java.sql.SQLException;

import dataaccess.StoneDAO;
import model.StoneCuttable;
import model.IStoneUnit;
import model.StoneMaterial;
import model.StoneUnit;

public class StoneRunner {

	public static void main(String[] args) throws SQLException {
		StoneDAO dao = new StoneDAO();
		
		
		for(IStoneUnit stone : dao.getStoneChildren((StoneCuttable) dao.getStoneUnitByID(1))) {
			if(stone!= null)
				System.out.println(stone.toString());
		}
		
		System.out.println("----------------------------------------------------------------------");
		
		for(IStoneUnit stone : dao.getStoneUnitsByStoneMaterial(new StoneMaterial(1, null, null))) {
			if(stone!= null)
				System.out.println(stone.toString());
		}
	}

}
