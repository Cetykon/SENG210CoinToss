import java.util.*;

public class main {
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Random rand = new Random();
		Scanner myObj = new Scanner(System.in);
		
		System.out.println("Do you want to play the coin toss game? y / n ");

		
		String userWantsToPlay = myObj.nextLine().toLowerCase();
		
		
		while (true){
			
			System.out.println("How many times you want to toss the coin? ");
			int timesToToss = Integer.parseInt(myObj.nextLine().toLowerCase());
			
			for (int i = 0; i < timesToToss; i++) {
				
				int randNumber = rand.nextInt(50);
				
				if ((randNumber % 2) == 0) {
					
					System.out.println("Heads");
					
				}else {
					
					System.out.println("Tails");
				}
			}
			
		}

	}

}
