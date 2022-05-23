package dataaccess;

import java.sql.SQLException;
import java.util.Collection;

import model.StoneMaterial;
import model.StoneType;

public interface ITypeMaterialDAO {
	public abstract Collection<StoneMaterial> getAllStoneMaterials() throws SQLException;
	public abstract Collection<StoneType> getAllStoneTypes() throws SQLException;
	public abstract StoneMaterial getStoneMaterialByID(int id) throws SQLException;
	public abstract StoneType getStoneTypeByID(int id) throws SQLException;
	public abstract int createStoneMaterial(StoneMaterial stoneMaterial) throws SQLException;
	public abstract int createStoneType(StoneType stoneType) throws SQLException;
	public abstract boolean updateStoneMaterial(StoneMaterial stoneMaterial) throws SQLException;
	public abstract boolean updateStoneType(StoneType stoneType) throws SQLException;
	public abstract boolean deleteStoneMaterial(StoneMaterial stoneMaterial) throws SQLException;
	public abstract boolean deleteStoneType(StoneType stoneType) throws SQLException;
}
