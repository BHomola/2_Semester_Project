package dataaccess;

import java.util.Collection;

import model.IStoneUnit;
import model.Material;
import model.StoneType;

public interface IStoneDAO {
	public abstract Collection<IStoneUnit> getAll() throws Exception;
	public abstract IStoneUnit getByID(int id) throws Exception;
	public abstract Collection<IStoneUnit> getByMaterial(Material material) throws Exception;
	public abstract Collection<IStoneUnit> getByType(StoneType type) throws Exception;
	public abstract int createStone(IStoneUnit stone) throws Exception;
	public abstract boolean updateStoneUnit(IStoneUnit stone) throws Exception;
	public abstract boolean deleteStone(IStoneUnit stone) throws Exception;
}
