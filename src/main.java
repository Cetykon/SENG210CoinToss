import java.util.*;

public class main {
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Random rand = new Random();
		
		int randNumber = rand.nextInt(50);
		
		if ((randNumber % 2) == 0) {
			
			System.out.print("Heads");
			
		}else {
			
			System.out.print("Tails");
		}

	}

}
