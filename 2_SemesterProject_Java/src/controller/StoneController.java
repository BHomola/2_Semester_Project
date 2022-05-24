package controller;

import java.sql.SQLException;
import java.util.Collection;

import dataaccess.IStoneDAO;
import dataaccess.StoneDAO;
import model.*;

public class StoneController{
	
	IStoneDAO stoneDAO;
	
	public StoneController() {
		stoneDAO = new StoneDAO();
	}

	public Collection<IStoneUnit> getAllStoneUnits() throws SQLException {
		return stoneDAO.getAllStoneUnits();
	}

	public IStoneUnit getStoneUnitByID(int id) throws SQLException {
		if(id > 0)
			return stoneDAO.getStoneUnitByID(id);
		return null;
	}

	public Collection<IStoneUnit> getStoneUnitsByStoneMaterial(StoneMaterial stoneMaterial) throws SQLException {
		if(stoneMaterial != null)
			return stoneDAO.getStoneUnitsByStoneMaterial(stoneMaterial);
		return null;
	}

	public Collection<IStoneUnit> getStoneUnitsByStoneType(StoneType type) throws SQLException {
		if(type != null)
			return stoneDAO.getStoneUnitsByStoneType(type);
		return null;
	}

	public Collection<IStoneUnit> getStoneProductsByOrderID(int orderID) throws SQLException {
		if(orderID > 0)
			return stoneDAO.getStoneProductsByOrderID(orderID);
		return null;
	}

	public boolean createStone(IStoneUnit stone, IStoneUnit parentStone) throws SQLException {
		if(stone != null)
			return stoneDAO.createStone(stone, parentStone);
		return false;
	}

	public boolean updateStone(IStoneUnit stone) throws SQLException {
		if(stone != null && ((StoneUnit)stone).getId() > 0)
			return stoneDAO.updateStone(stone);
		return false;
	}

	public boolean deleteStone(IStoneUnit stone) throws SQLException {
		if(stone != null && ((StoneUnit)stone).getId() > 0)
			return stoneDAO.deleteStone(stone);
		return false;
	}

	public boolean deleteStone(int stoneID) throws SQLException {
		if(stoneID>0)
			return stoneDAO.deleteStone(stoneID);
		return false;
	}
	
}
