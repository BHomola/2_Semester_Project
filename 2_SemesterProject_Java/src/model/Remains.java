package model;

public class Remains extends StoneUnit {
	private int pieces;

	public Remains(int id, Material material, String origin, String supplier, double width, double weight, String description,
			Location location, Status status, int pieces) {
		super(id, material, origin, supplier, width, weight, description, location, status);
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