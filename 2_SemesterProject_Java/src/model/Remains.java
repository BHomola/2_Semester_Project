package model;

import java.util.Date;

public class Remains extends StoneUnit {
	private int pieces;

	public Remains(int id, StoneType stoneType, String origin, Supplier supplier, double width, double weight,
			String description,Date createdDate, Location location, Employee employee, StoneUnitStatuses status, int pieces) {
		super(id, stoneType, origin, supplier, width, weight, description, createdDate, location, employee, status);
		this.pieces = pieces;
	}

	@Override
	public int getPieces() {
		return pieces;
	}

	public void setPieces(int ammount) {
		this.pieces = ammount;
	}

	@Override
	public String toString() {
		return "Remains [pieces=" + pieces + "] " + super.toString();
	}

	@Override
	public String getStoneKind() {
		return "Remains";
	}
	
	
	
	
}