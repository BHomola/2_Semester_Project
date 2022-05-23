package model;

public class Remains extends StoneUnit {
	private int pieces;

	public Remains(int id, StoneType stoneType, String origin, Supplier supplier, double width, double weight,
			String description, Location location, Employee employee, StoneUnitStatuses status, int pieces) {
		super(id, stoneType, origin, supplier, width, weight, description, location, employee, status);
		this.pieces = pieces;
	}

	@Override
	public int getPieces() {
		return pieces;
	}

	public void setPieces(int ammount) {
		this.pieces = ammount;
	}
}