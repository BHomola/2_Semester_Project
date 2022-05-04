package model;

import java.awt.Image;

public class Type {

	private int id;
	private String name;
	private String description;
	private Image picture;

	public Type(int id, String name, String description, Image picture) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.picture = picture;
	}
	

	public Type(int id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
	}


	public Type(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}


	public Type(int id, String name, Image picture) {
		super();
		this.id = id;
		this.name = name;
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

	public Image getPicture() {
		return picture;
	}

	public void setPicture(Image picture) {
		this.picture = picture;
	}

}
