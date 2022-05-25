package test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Date;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dataaccess.CityLocationDAO;
import dataaccess.PersonDAO;
import dataaccess.ShapeDAO;
import dataaccess.StoneDAO;
import dataaccess.TypeMaterialDAO;
import model.Employee;
import model.StoneCuttable;
import model.StoneProduct;
import model.StoneUnit;
import model.StoneUnitStatuses;
import model.Supplier;

public class TestStoneDAO {
	StoneDAO sDAO;
	StoneProduct stoneP;
	TypeMaterialDAO tmDAO;
	CityLocationDAO clDAO;
	PersonDAO pDAO;
	ShapeDAO shDAO;
	Date date;
	StoneUnitStatuses status;

	@BeforeEach
	void setUp() throws SQLException {
		sDAO = new StoneDAO();
		tmDAO = new TypeMaterialDAO();
		clDAO = new CityLocationDAO();
		pDAO = new PersonDAO();
		shDAO = new ShapeDAO();
		String sDate = "2022-05-23";
		date = Date.valueOf(sDate);
		status = StoneUnitStatuses.AVAILABLE;
		stoneP = new StoneProduct(tmDAO.getStoneTypeByID(6), "Spain", (Supplier) pDAO.getByID(23), 40.00, 90.00,
				"Product", date, clDAO.getLocationByID(3), (Employee) pDAO.getByID(19), status, shDAO.getById(1),
				150.00, 340, 1);
	}

	@Test
	void testGetAllStoneUnits() throws SQLException {
		assertEquals(10, sDAO.getAllStoneUnits().size());
	}

	@Test
	void testGetStoneChildren() throws SQLException {
		assertEquals(3, sDAO.getStoneChildren((StoneCuttable) sDAO.getStoneUnitByID(1)).size());
	}

	@Test
	void testCreateStone() throws SQLException {
		assertTrue(sDAO.createStone(stoneP, stoneP));
	}

	@Test
	void testUpdateStone() throws SQLException {
		assertTrue(sDAO.updateStone(stoneP));
	}

	@Test
	void testDeleteStone() throws SQLException {
		assertTrue(sDAO.deleteStone(stoneP));
	}
}
