package dataaccess;

import java.sql.SQLException;
import java.util.Collection;

import model.IStoneUnit;
import model.StoneMaterial;
import model.StoneProduct;
import model.StoneType;

public interface IStoneDAO {
	public abstract Collection<IStoneUnit> getAllStoneUnits() throws SQLException;
	public abstract IStoneUnit getStoneUnitByID(int id) throws SQLException;
	public abstract Collection<IStoneUnit> getStoneUnitsByStoneMaterial(StoneMaterial stoneMaterial) throws SQLException;
	public abstract Collection<IStoneUnit> getStoneUnitsByStoneType(StoneType type) throws SQLException;
	public abstract Collection<IStoneUnit> getStoneProductsByOrderID(int orderID) throws SQLException;
	public abstract boolean createStone(IStoneUnit stone, IStoneUnit parentStone) throws SQLException;
	public abstract boolean updateStone(IStoneUnit stone) throws SQLException;
	public abstract boolean deleteStone(IStoneUnit stone) throws SQLException;
	public abstract boolean deleteStone(int stoneID) throws SQLException;
}
