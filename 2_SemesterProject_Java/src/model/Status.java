package model;

public enum Status {
	WIP("Work in progress"),
	AVAILABLE("Available"),
	UNAVAILABLE("Unavailable");
	
	private String statusString;
	
	Status(String statusString)
	{
		this.statusString = statusString;
	}
	
	public String toString() {
		return statusString;
	}
}