package model;

import java.util.ArrayList;
import java.util.Date;

public class Supplier extends ImportExportPerson{
	
	private ArrayList<Type> selectionOfProducts;
	
	public Supplier(int id, String name, String address, City city, String phoneNumber, String email, Date dateOfBirth,
			int age, String description) {
		super(id, name, address, city, phoneNumber, email, dateOfBirth, age, description);
		setSelectionOfProducts(new ArrayList<Type>());
	}

	public ArrayList<Type> getSelectionOfProducts() {
		return selectionOfProducts;
	}

	public void setSelectionOfProducts(ArrayList<Type> selectionOfProducts) {
		this.selectionOfProducts = selectionOfProducts;
	}
}
