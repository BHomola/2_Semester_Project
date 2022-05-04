package model;

public class Material {

	private int id;
	private String name;
	private String description;
	private Type type;

	public Material(int id, String name, String description, Type type) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.type = type;
	}

	public Material(int id, String name, Type type) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
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

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

}
