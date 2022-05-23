package dataaccess;

import java.util.Collection;

import model.IStoneUnit;
import model.Material;
import model.Type;

public interface IStoneDAO {
	public abstract Collection<IStoneUnit> getAll();
	public abstract IStoneUnit getByID(int id);
	public abstract Collection<IStoneUnit> getByMaterial(Material material);
	public abstract Collection<IStoneUnit> getByType(Type type);
	public abstract int createStone(IStoneUnit stone);
	public abstract boolean updateStoneUnit(IStoneUnit stone);
	public abstract boolean deleteStone(IStoneUnit stone);
	IStoneUnit getByID();
}
