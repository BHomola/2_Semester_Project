package dataaccess;

import java.util.Collection;

import model.StoneMaterial;
import model.StoneType;

public interface ITypeMaterialDAO {
	public abstract Collection<StoneMaterial> getAllStoneMaterials();
	public abstract Collection<StoneType> getAllStoneTypes();
	public abstract StoneMaterial getStoneMaterialByID(int id);
	public abstract StoneType getStoneTypeByID(int id);
	public abstract int createStoneMaterial(StoneMaterial stoneMaterial);
	public abstract int createStoneType(StoneType stoneType);
	public abstract boolean updateStoneMaterial(StoneMaterial stoneMaterial);
	public abstract boolean updateStoneType(StoneType stoneType);
	public abstract boolean deleteStoneMaterial(StoneMaterial stoneMaterial);
	public abstract boolean deleteStoneType(StoneType stoneType);
}
