package model;

public class StoneType {

	private int id;
	private String name;
	private String description;
	private String picturePath;
	private StoneMaterial material;
	private int supplierID;


	
	public StoneType( String name, String description, String picturePath, int supplierID,
			StoneMaterial material) {
		super();
		this.name = name;
		this.description = description;
		this.picturePath = picturePath;
		this.supplierID = supplierID;
		this.material = material;
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

	public StoneType(int id, String name, String description, String picturePath, int supplierID, StoneMaterial material ) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.picturePath = picturePath;
		this.supplierID = supplierID;
		this.material = material;
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

	public StoneMaterial getMaterial() {
		return material;
	}

	public void setsMaterial(StoneMaterial material) {
		this.material = material;
	}

	public int getSupplierID() {
		return supplierID;
	}

	public void setSupplierID(int supplierID) {
		this.supplierID = supplierID;
	}

	@Override
	public String toString() {
		return name;
	}
	
	
}
