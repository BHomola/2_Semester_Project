package test;

import model.Stone;
import model.Type;
import model.CircleShape;
import model.City;
import model.Employee;
import model.IStoneUnit;
import model.Location;
import model.Material;
import model.Remains;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.StoneUnitStatuses;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestStone {
	Stone stone;
	Remains remains;
	Material material;
	Type type;
	City city;
	CircleShape cshape;
	Location location;
	Date date;
	Employee employee;
	SimpleDateFormat formatter;
	List<Type> list = new ArrayList<Type>();
	ArrayList<IStoneUnit> subUnits = new ArrayList<>() ;
	StoneUnitStatuses status;

	@BeforeEach
	void setUp() {
		try {
			
			stone = null;
			material = null;
			type = null;
			city = null;
			location = null;
			cshape = null;
			status = null;
			formatter = null;
			date=null;
			remains = null;
			material = new Material(1, "Granite", "Hard Rock", list);
			type = new Type(2, "Jaguar", "Orange-black");
			city = new City(111,"Chisinau", "MD-2000", "Moldova");
			location = new Location(222, "Deposit Fauresti", "Str. Nu stiu care", city);
			cshape = new CircleShape (15, "Cerc", 12);
			status = StoneUnitStatuses.AVAILABLE;
			formatter = new SimpleDateFormat("dd-MM-yyyy");
			date = formatter.parse("10-05-2022");
			employee = null;
			stone = new Stone(1,material,"Italy", "CMD", 1.5, 50.0, 
			"No description", location, employee, status, cshape, 250.0, 
			date, 0);
			remains = new Remains(3, material, "Italy", "CMD", 1.0, 40.0, "Left over", location, employee, status, 2);
			subUnits.add(remains);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	void testAddStoneUnit() {
		assertTrue(stone.addStoneUnit(stone));
	}

	@Test
	void testGetAllStoneUnits() {
		//Arrange
		int nr=0;
		stone.addStoneUnit(stone);
		//Act
		for(int i=0; i<stone.getAllStoneUnits().size(); i++) {
			nr++;
		}
		//Assert
		assertEquals(1, nr );
	}

	@Test
	void testGetStoneUnitByID() {
		stone.addStoneUnit(stone);
		assertEquals(50.0, stone.getStoneUnitByID(1).getWeight());
	}

	@Test
	void testUpdateStoneUnit() {
		//Act
		stone.addStoneUnit(stone);
		//Assert
		assertTrue(stone.updateStoneUnit(stone));
	}

	@Test
	void testDeleteStoneUnit() {
		//Act
		stone.addStoneUnit(stone);
		//Assert
		assertTrue(stone.deleteStoneUnit(stone));
	}

	@Test
	void testSetSubUnits() {
		//Act
		stone.setSubUnits(subUnits);
		//Assert
		assertEquals(1, stone.getSubUnits().size());
	}
	
	@Test
	void testGetSubUnits() {
		stone.setSubUnits(subUnits);
		assertEquals(1, stone.getSubUnits().size());
	}

	@Test
	void testGetPieces() {
		stone.addStoneUnit(remains);
		assertEquals(2, stone.getPieces());
	}
}
