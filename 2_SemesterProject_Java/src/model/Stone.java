package model;

public class Stone {
	private StoneType stoneType;

	public Stone(StoneType stoneType) {
		super();
		this.setStoneType(stoneType);
	}

	public StoneType getStoneType() {
		return stoneType;
	}

	public void setStoneType(StoneType stoneType) {
		this.stoneType = stoneType;
	}
}
