/* Scissors.java */
public class Scissors {
	Choice c3 = new Choice();
	Player p = new Player();
	//method to compare the user's scissors to the ai's object in game
	public void scissorscompare(){
		if(c3.aichoice.equals("scissors")){
			System.out.println("It's a tie! ");
		}
		else if(c3.aichoice.equals("paper")) {
			System.out.println("You win! ");
		}
		else if(c3.aichoice.equals("rock")) {
			System.out.println("You Lose! ");
		}
		System.out.println(p.ais[p.ainamepick]+" selected "+c3.aichoice);
	}
}
