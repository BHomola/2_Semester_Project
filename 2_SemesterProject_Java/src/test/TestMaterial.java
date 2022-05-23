package test;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.Material;
import model.StoneType;


class TestMaterial {
	Material material;
	StoneType type;
	List<StoneType> list = new ArrayList<StoneType>();
	@BeforeEach

	void setUp() {
		try {
			material = null;
			type = null;
			material = new Material(1, "Granite", "Hard Rock", list);
			type = new StoneType(2, "Jaguar", "Orange-black");
 		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testAddType() {
		try {
			assertTrue(material.addType(type));
		} catch(Exception e) {
			e.printStackTrace();
		}
		assertEquals(1, material.getNoAllTypes());
	}
	
	@Test
	void testGetAllTypes() {
		//Arrange
		StoneType type2 = new StoneType(3, "Space", "Dark blue");
		//Act
		material.addType(type2);
		//Assert
		assertEquals(1, material.getNoAllTypes());
		}
	
	@Test
	void testGetTypeByID() {
		//Arrange
		material.addType(type);
		String expectedName="Jaguar";
		//Act
		material.getStoneTypeByID(0);
		//Assert
		assertEquals(expectedName, type.getName());
	}
	
	@Test
	void testUpdateType() {
		material.addType(type);
		assertTrue(material.updateType(type));
	}
	
	@Test
	void testDeleteType() {
		//Arrange
		material.addType(type);
		//Act
		try {
			material.deleteType(type);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//Assert
		assertEquals(0, material.getNoAllTypes());
	}
}
