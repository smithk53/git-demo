package com.revature.Project0;
import java.util.*;
public class Banker{
	static Customer l2 = new Customer();
	static AccountSummary asum = new AccountSummary();
	private String BankerID;
	private String BankerPassword;
	static Random rand = new Random();
	static Scanner scan = new Scanner(System.in);
	static String response = "";
	/*Method to assign an admin from a string array of admins*/
	public void setBankerID() {
		while(true) {
			System.out.print("Welcome new associate! Please enter your name: ");
			BankerID = scan.nextLine();
			if(BankerID.length() >= 5) {
				break;
			}
			else {
				System.out.println("The id must be at least 5 characters in length!");
			}
		}
	}
	//Method to prompt the banker for a password and return it
	public void setBankerPass() {
		while(true) {
			System.out.print("Thanks. Now, enter your password: ");
			BankerPassword = scan.nextLine();
			if(BankerPassword.length() >= 7 ) {
				break;
			}
			else {
				System.out.println("The password must be at least 7 characters in length!");
			}
		}
	}
	public void login() {
		setBankerID();
		setBankerPass();
		System.out.print("What would you like to do? ('check' latest user, 'view' their transaction, or 'getid' their ID)");
		response = scan.nextLine();
		if(response.equals("check")) {
			//add current users to database, then display the users through a sql query
		}
		else if(response.equals("view")) {
			/*add the transaction# and type to the database, 
			 * then display that stuff along with the details of the transaction through a query
			*/
		}
		else if(response.equals("getid"))
		{
			//add the 
		}
		else {
			System.err.println("That response was invalid.");
		}
	}
}
