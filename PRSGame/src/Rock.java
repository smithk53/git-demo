/* Rock.java */
public class Rock {
	Player p3 = new Player();
	Choice c3 = new Choice();
	//method to compare the user's rock to the ai's object in game
	public void rockcompare() {
		//if the user and the ai selected the same object, it's a tie regardless
		if(c3.aichoice.equals("rock")) {
			System.out.println("It's a tie!");
		}
		else if(c3.aichoice.equals("paper")){
			System.out.println("You Lose!");
		}
		else if(c3.aichoice.equals("scissors")) {
			System.out.println("You Win!");
		}
		System.out.println(p3.ais[p3.ainamepick]+" selected "+c3.aichoice);
	}
}
