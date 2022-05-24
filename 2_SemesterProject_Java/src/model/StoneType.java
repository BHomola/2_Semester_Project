package model;

public class StoneType {

	private int id;
	private String name;
	private String description;
	private String picturePath;
	private Supplier supplier;
	private StoneMaterial sMaterial;

	
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

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public StoneMaterial getsMaterial() {
		return sMaterial;
	}

	public void setsMaterial(StoneMaterial sMaterial) {
		this.sMaterial = sMaterial;
	}

	@Override
	public String toString() {
		return "StoneType [id=" + id + ", name=" + name + ", description=" + description + ", picturePath="
				+ picturePath + ", supplier=" + supplier + ", sMaterial=" + sMaterial + "]";
	}
	
	
}
