package model;

public class Location {
	private int id;
	private String locationName;
	private String address;
	private City city;

	public Location(int id, String locationName, String address, City city) {
		super();
		this.id = id;
		this.locationName = locationName;
		this.address = address;
		this.city = city;
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

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "Location [id=" + id + ", locationName=" + locationName + ", address=" + address + ", cityID=" + city + "]";
	}
	
	
}
