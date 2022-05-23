package dataaccess;

import java.sql.SQLException;
import java.util.Collection;

import model.IStoneUnit;
import model.StoneMaterial;
import model.StoneType;

public interface IStoneDAO {
	public abstract Collection<IStoneUnit> getAll() throws SQLException;
	public abstract IStoneUnit getByID(int id) throws SQLException;
	public abstract Collection<IStoneUnit> getByMaterial(StoneMaterial stoneMaterial) throws SQLException;
	public abstract Collection<IStoneUnit> getByType(StoneType type) throws SQLException;
	public abstract int createStone(IStoneUnit stone) throws SQLException;
	public abstract boolean updateStoneUnit(IStoneUnit stone) throws SQLException;
	public abstract boolean deleteStone(IStoneUnit stone) throws SQLException;
}
