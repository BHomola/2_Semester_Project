package test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dataaccess.JSONShapeDAO;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.SQLException;

import model.CircleShape;

class TestJSONShapeDAO {
	CircleShape cShape;
	JSONShapeDAO jsonDAO;

	@BeforeEach
	void setUp() throws Exception {
		jsonDAO = new JSONShapeDAO();
		cShape = new CircleShape("2Circle", 4, 5.00);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testCreateShape() throws SQLException {
		equals(jsonDAO.createShape(cShape, 1));
	} 
	
	@Test
	void testUpdateShape() throws SQLException {
		assertTrue(jsonDAO.updateShape(cShape));
	}
	
	@Test
	void testDeleteShape() throws SQLException {
		assertTrue(jsonDAO.deleteShape(cShape));
	}

}
