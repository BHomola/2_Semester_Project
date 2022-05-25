package test;

import static org.junit.jupiter.api.Assertions.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import dataaccess.CityLocationDAO;
import dataaccess.PersonDAO;
import dataaccess.TypeMaterialDAO;
import model.StoneMaterial;
import model.StoneType;
import model.Supplier;

class TestTypeMaterialDAO {
	StoneMaterial sMaterial, smUpdated;
	StoneType sType, stUpdated;
	List<StoneType> lType = new ArrayList<StoneType>();
	Supplier supplier;
	Date date;
	TypeMaterialDAO tmDAO;
	CityLocationDAO clDAO;
	PersonDAO pDAO;
	
	@BeforeEach
	void setUp() throws Exception {
		try {
			pDAO = new PersonDAO();
			lType = null;
			clDAO = new CityLocationDAO();
			tmDAO = new TypeMaterialDAO();
			String sdate = "2022-05-10";
			date = Date.valueOf(sdate);
			supplier = new Supplier("Grafit", "Vesterbro 49", clDAO.getCityByID(1), "9843782", "test@test.com", date, 0, "Supplier", "No note");
			pDAO.createPerson(supplier);
			sMaterial = new StoneMaterial( "Granite", "Hard Rock", lType);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	@Order(1)
		@Test
	void testCreateStoneMaterial() {
		try {
			assertEquals(1, tmDAO.createStoneMaterial(sMaterial));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}   
	
	@Order(2)
	@Test
	void testCreateStoneType() {
		try {
			sType = new StoneType("Magma", "Orange-black", "Images\\Granite\\Magma.jpg", 1, 1);
			assertEquals(1, tmDAO.createStoneType(sType));
		} catch (Exception e) {
			e.printStackTrace();
		}
	} 
	
	@Order(3)
 	@Test
	void testGetStoneMaterialByID() {
		try {
			assertEquals("Granite", tmDAO.getStoneMaterialByID(1).getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Order(4)
 		@Test
	void testUpdateStoneMaterial() {
		//Arange
		smUpdated = new StoneMaterial(1, "Granite", "Hard hard rock");
		//Assert
		try {
			assertTrue(tmDAO.updateStoneMaterial(smUpdated));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 
	
	@Order(5)
	@Test 
	void testUpdateStoneType(){
		//Arange
				stUpdated = new StoneType(1, "Magmma", "Orange-black", "Images\\Granite\\Magma.jpg", 2, 1);
				//Assert
				try {
					assertTrue(tmDAO.updateStoneType(stUpdated));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	} 
	
	@Order(6)
	@Test
	void testGetStoneTypeByID() {
		try {
			assertEquals("Magmma", tmDAO.getStoneTypeByID(1).getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	} 
	
	@Order(7)
	@Test 
	void testGetAllStoneMaterials() throws SQLException {
		assertEquals(1, tmDAO.getAllStoneMaterials().size());
	} 
	
	@Order(8)
	@Test
	void testGetAllStoneTypes() throws SQLException {
		assertEquals(1, tmDAO.getAllStoneTypes().size());
	}
	
	@Order(9)
	@Test
	void testGetTypeListOfSameMaterial() throws SQLException {
		smUpdated = new StoneMaterial(1, "Granite", "Hard hard rock");
		assertEquals(1, tmDAO.getTypeListOfSameMaterial(smUpdated).size());
	} 
	
	@Order(11)
	@Test
	void testDeleteStoneMaterial() throws SQLException {
		smUpdated = new StoneMaterial(1, "Granite", "Hard hard rock");
		assertTrue(tmDAO.deleteStoneMaterial(smUpdated));
	} 
	
	@Order(10)
	@Test
	void testDeleteStoneType() throws SQLException {
		stUpdated = new StoneType(1, "Magmma", "Orange-black", "Images\\Granite\\Magma.jpg", 11, 2);
		assertTrue(tmDAO.deleteStoneType(stUpdated));
	} 

}
