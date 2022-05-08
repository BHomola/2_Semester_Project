package dataaccess;

import java.util.ArrayList;

import model.IStoneUnit;
import model.Material;
import model.Type;

public class StoneDAO implements IStoneDAO{

	
	//TODO: Finish when the database is implemented.
	@Override
	public ArrayList<IStoneUnit> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IStoneUnit getByID() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<IStoneUnit> getByMaterial(Material material) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<IStoneUnit> getByType(Type type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int createStone(IStoneUnit stone) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean updateStoneUnit(IStoneUnit stone) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteStone(IStoneUnit stone) {
		// TODO Auto-generated method stub
		return false;
	}

}
