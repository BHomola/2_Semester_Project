package model;

public enum StoneUnitStatuses {
	WIP("Work in progress"), AVAILABLE("Available"), UNAVAILABLE("Unavailable"), OTHER("Other");

	private String statusString;

	StoneUnitStatuses(String statusString) {
		this.statusString = statusString;
	}

	public String toString() {
		return statusString;
	}
}