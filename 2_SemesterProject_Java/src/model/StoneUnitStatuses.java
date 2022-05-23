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
	public static StoneUnitStatuses GetStatusByID(int id) {
		switch(id) {
		case 0:
			return StoneUnitStatuses.WIP;
		case 1:
			return StoneUnitStatuses.AVAILABLE;
		case 2:
			return StoneUnitStatuses.UNAVAILABLE;
		case 3:
			return StoneUnitStatuses.OTHER;
		}
		return StoneUnitStatuses.OTHER;
	}

	public String toString() {
		return statusString;
	}
}