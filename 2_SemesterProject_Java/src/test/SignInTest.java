package test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.LoginController;
import model.Login;

class SignInTest {
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() throws SQLException {
		LoginController loginC = new LoginController();
		int employeeID=12;
		String inputedUsername = "admin";
		String inputedPassword = "admin";
		Login login = new Login(inputedUsername,inputedPassword,employeeID);
		
			loginC.createLogin(login);
			
	}
}
