package model;

public class Type {

	private int id;
	private String name;
	private String description;
	private String picture;

	public Type(int id, String name, String description, String picture) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.picture = picture;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

}
