package dataaccess;

import java.sql.SQLException;
import java.util.Collection;

import model.IStoneUnit;
import model.StoneMaterial;
import model.StoneType;

public interface IStoneDAO {
	public abstract Collection<IStoneUnit> getAll() throws SQLException;
	public abstract IStoneUnit getStoneUnitByID(int id) throws SQLException;
	public abstract Collection<IStoneUnit> getStoneUnitsByStoneMaterial(StoneMaterial stoneMaterial) throws SQLException;
	public abstract Collection<IStoneUnit> getStoneUnitsByStoneType(StoneType type) throws SQLException;
	public abstract boolean createStone(IStoneUnit stone) throws SQLException;
	public abstract boolean updateStone(IStoneUnit stone) throws SQLException;
	public abstract boolean deleteStone(IStoneUnit stone) throws SQLException;
}
