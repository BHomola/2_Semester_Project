package model;

public class Login {
	private String username;
	private String password;
	private int employeeID;
	
	public Login(String username, String password, int employeeID) {
		super();
		this.username = username;
		this.password = password;
		this.employeeID = employeeID;
	}

	public Login(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public int getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Login [employeeID=" + employeeID + ", username=" + username + ", password=" + password + "]";
	}
	
	
}
