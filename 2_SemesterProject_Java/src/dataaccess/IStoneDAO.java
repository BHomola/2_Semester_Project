package dataaccess;

import java.util.ArrayList;

import model.IStoneUnit;
import model.Material;
import model.Type;

public interface IStoneDAO {
	public abstract ArrayList<IStoneUnit> getAll();
	public abstract IStoneUnit getByID();
	public abstract ArrayList<IStoneUnit> getByMaterial(Material material);
	public abstract ArrayList<IStoneUnit> getByType(Type type);
	public abstract int createStone(IStoneUnit stone);
	public abstract boolean updateStoneUnit(IStoneUnit stone);
	public abstract boolean deleteStone(IStoneUnit stone);
}
