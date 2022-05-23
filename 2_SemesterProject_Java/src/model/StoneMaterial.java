package model;

import java.util.List;

public class StoneMaterial {

	private int id;
	private String name;
	private String description;
	private List<StoneType> stoneType;
	
	public StoneMaterial(int id, String name, String description, List<StoneType> stoneType) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.stoneType = stoneType;
	}

	public StoneMaterial(int id, String name, String description ) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
	}

	public boolean addType(StoneType type) {
		return stoneType.add(type);
	}

	public void setStoneType(List<StoneType> stoneType) {
		this.stoneType = stoneType;
	}

	public List<StoneType> getAllTypes() {
		 for  (int i = 0; i < stoneType.size(); i++) {
			stoneType.get(i);
		}
		 return stoneType;
	}
	
	public int getNoAllTypes() {
		int nr=0; 
		for  (int i = 0; i < stoneType.size(); i++) {
		 nr++;
		 }
		 return nr;
	}
	
	public StoneType getStoneTypeByID(int i) {
		for (StoneType stoneType : stoneType) {
			if(stoneType.getId()==i) {
				return stoneType;
			}
		}
		return null;
	}
	
	public boolean updateType(StoneType type) {
		StoneType temp = getStoneTypeByID(((StoneType) type).getId());
		if (temp == null)
			return false;
		temp = type;
		return true;
	}
	
	public void deleteType(StoneType type) {
		this.stoneType.remove(type);
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

}
