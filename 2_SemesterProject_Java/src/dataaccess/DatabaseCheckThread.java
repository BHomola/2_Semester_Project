package dataaccess;

public class DatabaseCheckThread  extends Thread{
	
	
	public void run() {
		while(true) {
			
			try {
				
				if(DBConnection.getConnection() == null) {
					System.out.println("Connection closed");
				}
				else {
					System.out.println("Connection established");
				}
				
				sleep(5000);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
