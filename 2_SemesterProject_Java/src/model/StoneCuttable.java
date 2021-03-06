package model;

import java.util.ArrayList;
import java.sql.Date;

public class StoneCuttable extends Stone {
	private ArrayList<IStoneUnit> subUnits = new ArrayList<>();
	
	public StoneCuttable(int id, StoneType stoneType, String origin, Supplier supplier, double width, double weight,
			String description, Date createdDate, Location location, Employee employee, StoneUnitStatuses status, Shape shape,
			double totalSize, String updates) {
		super(id, stoneType, origin, supplier, width, weight, description, createdDate, location, employee, status, shape, totalSize, updates);
	}

	// setters - make another object and put it into list
	public boolean addStoneUnit(IStoneUnit unit) {
		return subUnits.add(unit);
	}
	public boolean addStoneUnits(ArrayList<IStoneUnit> units) {
		subUnits = units;
		return true;
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
		int sum = 1;
		for (IStoneUnit su: getAllStoneUnits())
			if(su instanceof Remains)
				sum += su.getPieces();
		return sum;
	}

	@Override
	public String toString() {
		return "Cuttable Stone ID:" + super.getId();
	}

	@Override
	public String getStoneKind() {
		return "StoneCuttable";
	}
	
	
	
}
