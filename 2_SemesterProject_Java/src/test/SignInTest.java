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
	void test() {
		LoginController loginC = new LoginController();
		int employeeID=11;
		String inputedUsername = "admin";
		String inputedPassword = "admin";
		try {
			for(Login l : loginC.getAllLogins()) {
				if(inputedUsername.equals(l.getUsername()))
					employeeID = l.getEmployeeID();
			}
			 if(inputedPassword.equals((String)loginC.getLoginByID(employeeID).getPassword())) {
				 System.out.println("Success");
			 }
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
