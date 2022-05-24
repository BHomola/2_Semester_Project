package model;

import java.util.ArrayList;
import java.util.Date;

public class StoneCuttable extends Stone {
	private ArrayList<IStoneUnit> subUnits = new ArrayList<>();
	
	public StoneCuttable(int id, StoneType stoneType, String origin, Supplier supplier, double width, double weight,
			String description, Date createdDate, Location location, Employee employee, StoneUnitStatuses status, Shape shape,
			double totalSize) {
		super(id, stoneType, origin, supplier, width, weight, description, createdDate, location, employee, status, shape, totalSize);
	}

	// setters - make another object and put it into list
	public boolean addStoneUnit(IStoneUnit unit) {
		return subUnits.add(unit);
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<IStoneUnit> getAllStoneUnits() {
		return (ArrayList<IStoneUnit>) subUnits.clone();
	}

	public IStoneUnit getStoneUnitByID(int id) {
		for (IStoneUnit su : getAllStoneUnits())
			if (su instanceof Stone && ((Stone) su).getId() == id)
				return su;
		return null;
	}

	public boolean updateStoneUnit(IStoneUnit stoneUnit) {
		IStoneUnit temp = getStoneUnitByID(((StoneUnit) stoneUnit).getId());
		if (temp == null)
			return false;
		temp = stoneUnit;
		return true;
	}

	public boolean deleteStoneUnit(IStoneUnit stoneUnit) {
		return subUnits.remove(stoneUnit);
	}
	
	public ArrayList<IStoneUnit> getSubUnits() {
		return subUnits;
	}

	public void setSubUnits(ArrayList<IStoneUnit> subUnits) {
		this.subUnits = subUnits;
	}

	@Override
	public int getPieces() {
		int sum = 0;
		for (IStoneUnit su: getAllStoneUnits())
			if(su instanceof Remains)
				sum += su.getPieces();
		return sum;
	}

	@Override
	public String toString() {
		return "CuttableStone [subUnits=" + subUnits + "] " + super.toString();
	}

	@Override
	public String getStoneKind() {
		return "CuttableStone";
	}
	
	
	
}
