package model;

public class City {
	private int id;
	private String cityName;
	private int zipCode;
	private String country;

	public City(int id, String cityName, int zipCode, String country) {
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

	public int getZipCode() {
		return zipCode;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

}
