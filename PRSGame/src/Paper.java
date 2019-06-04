/*Paper.java*/
public class Paper {
	Player p2 = new Player();
	Choice c2 = new Choice();
	//method to compare the user's paper to the ai's object in game
	public void papercompare(){
		if(c2.aichoice.equals("paper")) {
			System.out.println("It's a tie! ");
		}
		else if(c2.aichoice.equals("rock")) {
			System.out.println("You Win! ");
		}
		else if(c2.aichoice.equals("scissors")) {
			System.out.println("You Lose! ");
		}
		System.out.println(p2.ais[p2.ainamepick]+" selected "+c2.aichoice);
	}
}
