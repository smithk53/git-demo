/*
 * @author Kyle Smith
 * Date: 6/4/19
 * Last Modified: 4:08pm
 * Paper Rock Scissors Game*/
import java.util.*;
public class Player {
	static String name;
	static Scanner scan = new Scanner(System.in);
	static Choice c1 = new Choice();
	static Scissors s1 = new Scissors();
	static Rock r1 = new Rock();
	static Paper p1 = new Paper();
	static int roundcount = 0;
	static Random rand = new Random();
	static int ainamepick;
	static String[] ais;
	public static void main(String[] args) {
		ais = new String[]{"Ghaliath","Jacklyn","Hope","Jace"};
		//ai name will be selected at random from the 'ais' array later
		ainamepick = rand.nextInt(3)+0;
		//prompt user for name
		System.out.print("What's your name: ");
		//store the user's indicated name in a string object
		name = scan.nextLine();
		//display intro message for game
		System.out.println("Welcome " +name+" You have entered the 'Paper, Rock, Scissors': "
				+ "game and will be playing against "+ais[ainamepick]+".\nPlease select one of the following options:");
		c1.getChoice();
		//if the user selects 'scissors', trigger the scissors comparison tactics
		if(c1.userchoice.equals("scissors")) {
			s1.scissorscompare();
		}
		//if the user selects 'rock', trigger the rock comparison tactics
		else if(c1.userchoice.equals("rock")) {
			r1.rockcompare();
		}
		//if the user selects 'paper', trigger the paper comparison tactics
		else if(c1.userchoice.equals("paper")) {
			p1.papercompare();
		}
		else {
			System.err.println("Invalid entry. ");
		}
	}
}
