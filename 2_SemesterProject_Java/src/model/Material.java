package model;

import java.util.List;

public class Material {

	private int id;
	private String name;
	private String description;
	private List<Type> typeL;

	public Material(int id, String name, String description, List<Type> typeL) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.typeL = typeL;
	}

	public boolean addType(Type type) {
		return typeL.add(type);
	}

	public List<Type> getAllTypes() {
		 for  (int i = 0; i < typeL.size(); i++) {
			typeL.get(i);
		}
		 return typeL;
	}
	
	public int getNoAllTypes() {
		int nr=0; 
		for  (int i = 0; i < typeL.size(); i++) {
		 nr++;
		 }
		 return nr;
	}
	
	public Type getTypeByID(int i) {
		for (Type type : typeL) {
			if(type.getId()==i) {
				return type;
			}
		}
		return null;
	}
	
	public boolean updateType(Type type) {
		Type temp = getTypeByID(((Type) type).getId());
		if (temp == null)
			return false;
		temp = type;
		return true;
	}
	
	public void deleteType(Type type) {
		this.typeL.remove(type);
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
