package model;

public class StoneType {

	private int id;
	private String name;
	private String description;
	private String picturePath;
	private int sMaterialID, supplierID;


	
	public StoneType( String name, String description, String picturePath, int supplierID,
			int sMaterialID) {
		super();
		this.name = name;
		this.description = description;
		this.picturePath = picturePath;
		this.supplierID = supplierID;
		this.sMaterialID = sMaterialID;
	}

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

	public StoneType(int id, String name, String description, String picturePath, int supplierID, int sMaterialID) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.picturePath = picturePath;
		this.supplierID = supplierID;
		this.sMaterialID = sMaterialID;
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

	public int getsMaterialID() {
		return sMaterialID;
	}

	public void setsMaterialID(int sMaterialID) {
		this.sMaterialID = sMaterialID;
	}

	public int getSupplierID() {
		return supplierID;
	}

	public void setSupplierID(int supplierID) {
		this.supplierID = supplierID;
	}

	@Override
	public String toString() {
		return "StoneType [id=" + id + ", name=" + name + ", description=" + description + ", picturePath="
				+ picturePath + ", supplierID=" + supplierID + ", sMaterialID=" + sMaterialID + "]";
	}
	
	
}
