package dataaccess;

import java.util.Collection;

import model.IStoneUnit;
import model.Material;
import model.StoneType;

public interface IStoneDAO {
	public abstract Collection<IStoneUnit> getAll();
	public abstract IStoneUnit getByID(int id);
	public abstract Collection<IStoneUnit> getByMaterial(Material material);
	public abstract Collection<IStoneUnit> getByType(StoneType type);
	public abstract int createStone(IStoneUnit stone);
	public abstract boolean updateStoneUnit(IStoneUnit stone);
	public abstract boolean deleteStone(IStoneUnit stone);
}
