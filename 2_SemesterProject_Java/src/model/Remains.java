package model;

import java.sql.Date;

public class Remains extends StoneUnit {
	private int pieces;

	public Remains(int id, StoneType stoneType, String origin, Supplier supplier, double width, double weight,
			String description,Date createdDate, Location location, Employee employee, StoneUnitStatuses status, int pieces, String updates) {
		super(id, stoneType, origin, supplier, width, weight, description, createdDate, location, employee, status, updates);
		this.pieces = pieces;
	}

	@Override
	public int getPieces() {
		return 1;
	}
	
	public int getPiecesCount() {
		return pieces;
	}

	public void setPieces(int ammount) {
		this.pieces = ammount;
	}

	@Override
	public String toString() {
		return "Remains ID:" + super.getId();
	}

	@Override
	public String getStoneKind() {
		return "Remains";
	}
	
	
	
	
}