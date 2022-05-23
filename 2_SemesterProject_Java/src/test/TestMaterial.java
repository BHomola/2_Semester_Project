package test;

import static org.junit.Assert.assertEquals;


import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.StoneMaterial;
import model.StoneType;


class TestMaterial {
	StoneMaterial stoneMaterial;
	StoneType type;
	List<StoneType> list = new ArrayList<StoneType>();
	@BeforeEach

	void setUp() {
		try {
			stoneMaterial = null;
			type = null;
			stoneMaterial = new StoneMaterial(1, "Granite", "Hard Rock", list);
			type = new StoneType(2, "Jaguar", "Orange-black");
 		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testAddType() {
		try {
			assertTrue(stoneMaterial.addType(type));
		} catch(Exception e) {
			e.printStackTrace();
		}
		assertEquals(1, stoneMaterial.getNoAllTypes());
	}
	
	@Test
	void testGetAllTypes() {
		//Arrange
		StoneType type2 = new StoneType(3, "Space", "Dark blue");
		//Act
		stoneMaterial.addType(type2);
		//Assert
		assertEquals(1, stoneMaterial.getNoAllTypes());
		}
	
	@Test
	void testGetTypeByID() {
		//Arrange
		stoneMaterial.addType(type);
		String expectedName="Jaguar";
		//Act
		stoneMaterial.getStoneTypeByID(0);
		//Assert
		assertEquals(expectedName, type.getName());
	}
	
	@Test
	void testUpdateType() {
		stoneMaterial.addType(type);
		assertTrue(stoneMaterial.updateType(type));
	}
	
	@Test
	void testDeleteType() {
		//Arrange
		stoneMaterial.addType(type);
		//Act
		try {
			stoneMaterial.deleteType(type);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//Assert
		assertEquals(0, stoneMaterial.getNoAllTypes());
	}
}
