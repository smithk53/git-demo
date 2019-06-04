/*Choice.java*/
import java.util.*;
public class Choice {
	static String userchoice = "";
	Player p1 = new Player();
	private String[] aichoicesavailable = {};
	static String aichoice = "";
	//static Random rand2 = new Random();
	//method to prompt the user for a selection, then return the user's choice
	public String getChoice() {
		System.out.print("What do you select('paper','rock', or 'scissors')?");
		userchoice = p1.scan.nextLine();
		aichoicesavailable = new String[]{"paper","rock","scissors"};
		int posofaichoice = p1.rand.nextInt(2)+0;
		aichoice = aichoicesavailable[posofaichoice];
		return userchoice;
	}
	public void setChoice(String a) {
		userchoice = a;
	}
}
