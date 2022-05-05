package model;

import java.util.ArrayList;
import java.util.Date;

public class Stone extends StoneUnit {
	private Shape shape;
	private double totalSize;
	private Date birth;
	private ArrayList<IStoneUnit> subUnits = new ArrayList<>();

	public Stone(int id, Material material, String origin, String supllier, double width, double weight,
			String description, Location location, Status status, Shape shape, double totalSize, Date birth) {
		super(id, material, origin, supllier, width, weight, description, location, status);
		this.shape = shape;
		this.totalSize = totalSize;
		this.birth = birth;
	}

	public boolean addStoneUnit(IStoneUnit unit) {
		return subUnits.add(unit);
	}

	/// ?
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

	// wont be that easy probably
	public boolean deleteStoneUnit(IStoneUnit stoneUnit) {
		return subUnits.remove(stoneUnit);
	}

	public Shape getShape() {
		return shape;
	}

	public void setShape(Shape shape) {
		this.shape = shape;
	}

	public double getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(double totalSize) {
		this.totalSize = totalSize;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
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

}
