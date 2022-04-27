import model.Material;
import model.Stone;

public class main {

	public static void main(String[] args) {
		Stone stone = new Stone(Material.Quartz.Ceniza);
		System.out.println(stone.getStoneType());
	}

}
