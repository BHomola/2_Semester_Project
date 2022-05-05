package model;

import java.util.ArrayList;
import java.util.Date;

public class Stone extends StoneUnit {
	private int id;
	private Shape shape;
	private double totalSize;
	private Date birth;
	private ArrayList<IStoneUnit> subUnits = new ArrayList<>();

	public Stone(Material material, String origin, String supllier, double width, double weight, String description,
			Location location, Status status, int id, Shape shape, double totalSize, Date birth) {
		super(material, origin, supllier, width, weight, description, location, status);
		this.id = id;
		this.shape = shape;
		this.totalSize = totalSize;
		this.birth = birth;
	}

	public boolean addUnit(IStoneUnit unit) {
		return subUnits.add(unit);
	}
	
	///?
	@SuppressWarnings("unchecked")
	public ArrayList<IStoneUnit> getAllUnits() {
		return (ArrayList<IStoneUnit>) subUnits.clone();
	}
	
	public IStoneUnit getStoneByID(int id) {
		for(IStoneUnit su:getAllUnits()) 
			if(su instanceof Stone && ((Stone) su).getId() == id)
				return su;
		return null;
	}
	
	public boolean updateStone (Type type) {
		return getStoneByID(ty)
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Shape getShape() {
		return shape;
	}

	public void setShape(Shape shape) {
		this.shape = shape;
	}

	public double getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(double totalSize) {
		this.totalSize = totalSize;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public ArrayList<IStoneUnit> getSubUnits() {
		return subUnits;
	}

	public void setSubUnits(ArrayList<IStoneUnit> subUnits) {
		this.subUnits = subUnits;
	}

	@Override
	public int getPieces() {
		return 1;
	}

}
