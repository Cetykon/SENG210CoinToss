import java.util.*;

public class main {
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Random rand = new Random();
		Scanner myObj = new Scanner(System.in);
		
		System.out.println("Do you want to play the coin toss game? y / n ");
		String userWantsToPlay = myObj.nextLine();
				
		int numberOfHeads = 0;
		int numberOfTails = 0;
		
		
		while (userWantsToPlay.equals("y")){
			
			System.out.println("How many times you want to toss the coin? ");
			int timesToToss = Integer.parseInt(myObj.nextLine().toLowerCase());
			
			for (int i = 0; i < timesToToss; i++) {
				
				int randNumber = rand.nextInt(50);
				
				if ((randNumber % 2) == 0) {
					
					System.out.println("Heads");
					numberOfHeads++;
					
				}else {
					
					System.out.println("Tails");
					numberOfTails++;
				}
			}
			
			System.out.println("Do you want to play again? y / n ");
			userWantsToPlay = myObj.nextLine().toLowerCase();
			
		}
		
		System.out.println("Stats: \n Heads: " + numberOfHeads + "\n Tails: " + numberOfTails);

	}

}
