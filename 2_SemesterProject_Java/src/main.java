import model.Quartz;
import model.Stone;

public class main {

	public static void main(String[] args) {
		Stone stone = new Stone(Quartz.Arena);
		System.out.println(stone.getStoneType());
		System.out.println(stone.getClass()); 
	}

}
