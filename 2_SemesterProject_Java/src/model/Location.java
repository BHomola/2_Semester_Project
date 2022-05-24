package model;

public class Location {
	private int id;
	private String locationName;
	private String address;
	private int cityID;

	public Location(int id, String locationName, String address, int cityID) {
		super();
		this.id = id;
		this.locationName = locationName;
		this.address = address;
		this.cityID = cityID;
	}
	
	public Location( String locationName, String address, int cityID) {
		super();
		this.locationName = locationName;
		this.address = address;
		this.cityID = cityID;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getCity() {
		return cityID;
	}

	public void setCity(int cityID) {
		this.cityID = cityID;
	}

	@Override
	public String toString() {
		return "Location [id=" + id + ", locationName=" + locationName + ", address=" + address + ", cityID=" + cityID + "]";
	}
	
	
}
