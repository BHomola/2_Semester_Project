package test;

import model.StoneCuttable;
import model.StoneType;
import model.CircleShape;
import model.City;
import model.Employee;
import model.IStoneUnit;
import model.Location;
import model.StoneMaterial;
import model.Remains;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.StoneUnitStatuses;
import model.Supplier;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestStone {
	StoneCuttable stone;
	Remains remains;
	StoneMaterial stoneMaterial;
	StoneType type;
	City city;
	CircleShape cshape;
	Location location;
	Date date;
	Employee employee;
	Supplier supplier;
	SimpleDateFormat formatter;
	List<StoneType> list = new ArrayList<StoneType>();
	ArrayList<IStoneUnit> subUnits = new ArrayList<>() ;
	StoneUnitStatuses status;

	@BeforeEach
	void setUp() {
		try {
			
			stone = null;
			stoneMaterial = null;
			type = null;
			city = null;
			location = null;
			cshape = null;
			status = null;
			formatter = null;
			date=null;
			remains = null;
			supplier = null;
			stoneMaterial = new StoneMaterial(1, "Granite", "Hard Rock");
			type = new StoneType(2, "Jaguar", "Orange-black");
			city = new City(111,"Chisinau", "MD-2000", "Moldova");
			location = new Location(222, "Deposit Fauresti", "Str. Nu stiu care", city);
			cshape = new CircleShape  ("Cerc", 1, 12.0);
			status = StoneUnitStatuses.AVAILABLE;
			formatter = new SimpleDateFormat("dd-MM-yyyy");
			date = formatter.parse("10-05-2022");
			supplier = new Supplier(13, "CMD", "Vesterbro 41", city, "9143283", "test@test.com", date, 30, "None", "Note");
			employee = null;
			stone = new StoneCuttable(1,type,"Italy", supplier, 1.5, 50.0, 
			"No description", date, location, employee, status, cshape, 250.0);
			remains = new Remains(3, type, "Italy", supplier, 40.0, 1.0, "Left over", date, location, employee, status, 2);
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
