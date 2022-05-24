package model;

import java.util.ArrayList;
import java.util.Date;

public class Supplier extends Person {

	private ArrayList<StoneType> selectionOfProducts;

	public Supplier(int id, String name, String address, City city, String phoneNumber, String email, Date dateOfBirth,
			int age, String description, String note) {
		super(id, name, address, city, phoneNumber, email, dateOfBirth, age, description, note);
		setSelectionOfProducts(new ArrayList<StoneType>());
	}
	
	public Supplier( String name, String address, City city, String phoneNumber, String email, Date dateOfBirth,
			int age, String description, String note) {
		super( name, address, city, phoneNumber, email, dateOfBirth, age, description, note);
		setSelectionOfProducts(new ArrayList<StoneType>());
	}


	public Supplier(int id) {
		super(id);
	}

	public ArrayList<StoneType> getSelectionOfProducts() {
		return selectionOfProducts;
	}

	public void setSelectionOfProducts(ArrayList<StoneType> selectionOfProducts) {
		this.selectionOfProducts = selectionOfProducts;
	}

	@Override
	public String toString() {
		return "Supplier [selectionOfProducts=" + selectionOfProducts + "]"  + super.toString();
	}

}
