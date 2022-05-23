package model;

public class City {
	private int id;
	private String cityName;
	private String zipCode;
	private String country;

	public City(int id, String cityName, String zipCode, String country) {
		super();
		this.id = id;
		this.cityName = cityName;
		this.zipCode = zipCode;
		this.country = country;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "City [id=" + id + ", cityName=" + cityName + ", zipCode=" + zipCode + ", country=" + country + "]";
	}
	
	

}
