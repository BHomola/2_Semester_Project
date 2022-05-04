package model;

public class Stone {
	private Type Type;

	public Stone(Type Type) {
		super();
		this.setStoneType(Type);
	}

	public Type getStoneType() {
		return Type;
	}

	public void setStoneType(Type Type) {
		this.Type = Type;
	}
}
