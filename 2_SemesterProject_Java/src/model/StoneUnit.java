package model;

import java.time.LocalDate;

public abstract class StoneUnit implements IStoneUnit {
	private int id;
	private StoneType stoneType;
	private String origin;
	private Supplier supplier;
	private double width;
	private double weight;
	private String description;
	private Location location;
	private Employee employee;
	private StoneUnitStatuses status;
	private String updates;

	public StoneUnit(int id, StoneType stoneType, String origin, Supplier supplier, double width, double weight,
			String description, Location location, Employee employee, StoneUnitStatuses status) {
		super();
		this.id = id;
		this.stoneType = stoneType;
		this.origin = origin;
		this.supplier = supplier;
		this.width = width;
		this.weight = weight;
		this.description = description;
		this.location = location;
		this.employee = employee;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public StoneType getStoneType() {
		return stoneType;
	}

	public void setMaterial(StoneType stoneType) {
		this.stoneType = stoneType;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
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

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public StoneUnitStatuses getStatus() {
		return status;
	}

	public void setStatus(StoneUnitStatuses status) {
		this.status = status;
	}

	public String getUpdates() {
		return updates;
	}

	public void setUpdates(String updates) {
		this.updates = updates;
	}

	public void addUpdate(String note) {
		this.updates += LocalDate.now() + ": " + note + "\n";
	}

	@Override
	public String toString() {
		return "StoneUnit [id=" + id + ", material=" + stoneType + ", origin=" + origin + ", supplier=" + supplier
				+ ", width=" + width + ", weight=" + weight + ", description=" + description + ", location=" + location
				+ ", employee=" + employee + ", status=" + status + ", updates=" + updates + "]";
	}

}
