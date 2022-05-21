package model;

import java.util.ArrayList;
import java.util.Date;

public class Stone extends StoneUnit {
	private Shape shape;
	private double totalSize;
	private Date birthDate;
	private int orderID;
	private ArrayList<IStoneUnit> subUnits = new ArrayList<>();

	public Stone(int id, Material material, String origin, String supplier, double width, double weight,
			String description, Location location, Employee employee, StoneUnitStatuses status, Shape shape,
			double totalSize, Date birth, int orderID) {
		super(id, material, origin, supplier, width, weight, description, location, employee, status);
		this.shape = shape;
		this.totalSize = totalSize;
		this.birthDate = birth;
		this.setOrderID(orderID);
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

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birth) {
		this.birthDate = birth;
	}

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
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
		return "Stone [shape=" + shape + ", totalSize=" + totalSize + ", birthDate=" + birthDate + ", orderID="
				+ orderID + ", subUnits=" + subUnits + "]";
	}
	
	
}
