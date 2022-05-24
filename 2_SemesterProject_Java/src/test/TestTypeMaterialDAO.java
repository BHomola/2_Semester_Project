package test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dataaccess.TypeMaterialDAO;
import model.City;
import model.StoneMaterial;
import model.StoneType;
import model.Supplier;

class TestTypeMaterialDAO {
	StoneMaterial sMaterial, smUpdated;
	StoneType sType, stUpdated;
	List<StoneType> lType = new ArrayList<StoneType>();
	Supplier supplier;
	City city;
	Date date;
	SimpleDateFormat formatter;
	TypeMaterialDAO tmDAO;
	int m=2, t=1;
	
	@BeforeEach
	void setUp() throws Exception {
		try {
			tmDAO = new TypeMaterialDAO();
			formatter = new SimpleDateFormat("dd-MM-yyyy");
			date = formatter.parse("10-05-2022");
			city = new City(111,"Chisinau", "MD-2000", "Moldova");
			supplier = new Supplier(4, "Grafit", "Vesterbro 49", city, "9843782", "test@test.com", date, 0, "None", "No note");
			sMaterial = new StoneMaterial( "Granite", "Hard Rock", lType);
			sType = new StoneType("Magma", "Orange-black", "Images\\Granite\\Magma.jpg", supplier, sMaterial);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@AfterEach
	void tearDown() throws Exception {
		//m++;
		t++;
	}
/*
	@Test
	void testCreateStoneMaterial() {
		try {
			assertEquals(m, tmDAO.createStoneMaterial(sMaterial));
		} catch (Exception e) {
			e.printStackTrace();
		}
	} */
	
	@Test
	void testCreateStoneType() {
		try {
			assertEquals(t, tmDAO.createStoneType(sType));
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
		
	}
	
	@Test
	void testDeleteStoneMaterial() {
		
	}
	
	@Test
	void testDeleteStoneType() {
		
	}
	
	@Test
	void testGetStoneMaterialByID() {
		
	}
	
	@Test
	void testGetStoneTypeByID() {
		
	}
	
	@Test 
	void testGetAllStoneMaterials() {
		
	}
	
	@Test
	void testGetAllStoneTypes() {
		
	}
	
	@Test
	void testGetTypeListOfSameMaterial() {
		
	}
	
	@Test
	void testBuildType() {
		
	}
	
	@Test
	void testBuildTypes() {
		
	}
	
	@Test
	void testBuildMaterial() {
		
	}
	
	@Test
	void testBuildMaterials() {
		
	}

}
