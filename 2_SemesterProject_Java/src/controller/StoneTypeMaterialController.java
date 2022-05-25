package controller;

import java.sql.SQLException;
import java.util.Collection;

import dataaccess.ITypeMaterialDAO;
import dataaccess.TypeMaterialDAO;
import model.StoneMaterial;
import model.StoneType;

public class StoneTypeMaterialController  {

	ITypeMaterialDAO dao;
	public StoneTypeMaterialController() {
		dao = new TypeMaterialDAO();
	}
	
	public Collection<StoneMaterial> getAllStoneMaterials() throws SQLException {
		return dao.getAllStoneMaterials();
	}


	public Collection<StoneType> getAllStoneTypes() throws SQLException {
		return dao.getAllStoneTypes();
	}


	public StoneMaterial getStoneMaterialByID(int id) throws SQLException {
		return dao.getStoneMaterialByID(id);

	}


	public StoneType getStoneTypeByID(int id) throws SQLException {
		return dao.getStoneTypeByID(id);
	}


	public int createStoneMaterial(StoneMaterial stoneMaterial) throws SQLException {
		return dao.createStoneMaterial(stoneMaterial);
	}


	public int createStoneType(StoneType stoneType) throws SQLException {
		return dao.createStoneType(stoneType);
	}


	public boolean updateStoneMaterial(StoneMaterial stoneMaterial) throws SQLException {
		return dao.updateStoneMaterial(stoneMaterial);
	}


	public boolean updateStoneType(StoneType stoneType) throws SQLException {
		return dao.updateStoneType(stoneType);
	}


	public boolean deleteStoneMaterial(StoneMaterial stoneMaterial) throws SQLException {
		return dao.deleteStoneMaterial(stoneMaterial);
	}


	public boolean deleteStoneType(StoneType stoneType) throws SQLException {
		return dao.deleteStoneType(stoneType);
	}

}
