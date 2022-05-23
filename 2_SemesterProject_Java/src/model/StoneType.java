package model;

public class StoneType {

	private int id;
	private String name;
	private String description;
	private String picturePath;

	
	public StoneType(int id, String name, String description, String picturePath) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.picturePath = picturePath;
	}

	public StoneType(int id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
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

	public String getpicturePath() {
		return picturePath;
	}

	public void setpicturePath(String picturePath) {
		this.picturePath = picturePath;
	}

}
