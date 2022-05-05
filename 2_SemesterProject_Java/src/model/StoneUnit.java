package model;

/*This is just an example i coded 
It is not mandatory to be like this
You can edit it if it is not correct 
Or just add the fields that are missing */

public abstract class StoneUnit implements IStoneUnit {
	private Material material;
	private String origin;
	private String supllier;
	private double width;
	private double weight;
	private String description;
	private Location location;
	private Status status;

	public StoneUnit(Material material, String origin, String supllier, double width, double weight, String description,
			Location location, Status status) {
		super();
		this.material = material;
		this.origin = origin;
		this.supllier = supllier;
		this.width = width;
		this.weight = weight;
		this.description = description;
		this.location = location;
		this.status = status;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getSupllier() {
		return supllier;
	}

	public void setSupllier(String supllier) {
		this.supllier = supllier;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}
