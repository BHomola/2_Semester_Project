package model;

public enum StoneUnitStatuses {
	WIP("Work in progress",0), AVAILABLE("Available",1), UNAVAILABLE("Unavailable",2), OTHER("Other",3);

	private String statusString;
	private int id;

	StoneUnitStatuses(String statusString, int id) {
		this.statusString = statusString;
		this.id = id;
	}
	
	public int getID() {
		return id;
	}

	public String toString() {
		return statusString;
	}
}