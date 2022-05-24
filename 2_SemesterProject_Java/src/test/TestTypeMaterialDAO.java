package test;

import static org.junit.jupiter.api.Assertions.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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
			clDAO = new CityLocationDAO();
			tmDAO = new TypeMaterialDAO();
			String sdate = "2022-05-10";
			date = Date.valueOf(sdate);
			supplier = new Supplier("Grafit", "Vesterbro 49", clDAO.getCityByID(3), "9843782", "test@test.com", date, 0, "Supplier", "No note");
			pDAO.createPerson(supplier);
			sMaterial = new StoneMaterial( "Granite", "Hard Rock", lType);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
		@Test
	void testCreateStoneMaterial() {
		try {
			assertEquals(1, tmDAO.createStoneMaterial(sMaterial));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}   
	
	//Need fix
	@Test
	void testCreateStoneType() {
		try {
			sType = new StoneType("Magma", "Orange-black", "Images\\Granite\\Magma.jpg", 2, 1);
			assertEquals(1, tmDAO.createStoneType(sType));
		} catch (Exception e) {
			e.printStackTrace();
		}
	} 
	
 	@Test
	void testGetStoneMaterialByID() {
		try {
			assertEquals("Granite", tmDAO.getStoneMaterialByID(1).getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
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
	
	@Test
	void testGetStoneTypeByID() {
		try {
			assertEquals("Magmma", tmDAO.getStoneTypeByID(1).getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	} 
	
	@Test 
	void testGetAllStoneMaterials() throws SQLException {
		assertEquals(1, tmDAO.getAllStoneMaterials().size());
	} 
	
	@Test
	void testGetAllStoneTypes() throws SQLException {
		assertEquals(1, tmDAO.getAllStoneTypes().size());
	}
	
	@Test
	void testGetTypeListOfSameMaterial() throws SQLException {
		smUpdated = new StoneMaterial(1, "Granite", "Hard hard rock");
		assertEquals(1, tmDAO.getTypeListOfSameMaterial(smUpdated).size());
	} 
	
	@Test
	void testDeleteStoneMaterial() throws SQLException {
		smUpdated = new StoneMaterial(1, "Granite", "Hard hard rock");
		assertTrue(tmDAO.deleteStoneMaterial(smUpdated));
	} 
	
	@Test
	void testDeleteStoneType() throws SQLException {
		stUpdated = new StoneType(1, "Magmma", "Orange-black", "Images\\Granite\\Magma.jpg", 11, 2);
		assertTrue(tmDAO.deleteStoneType(stUpdated));
	} 

}
