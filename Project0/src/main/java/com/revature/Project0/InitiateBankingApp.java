package com.revature.Project0;
/*
 *@author Kyle Smith
 *Date: 6/15/19
 *Last Modified: 9:20am
 *Assignment: Project0
 */
import java.util.*;
public class InitiateBankingApp 
{
	//create new instances of objects
	static Banker b1 = new Banker();
	static Customer cust = new Customer();
	static Scanner scan = new Scanner(System.in);
	static String input = "";
	//start here 
    public static void main(String[] args )
    {
    	//prompt the user for identification
		System.out.print("Welcome to 'Smith's Bank'. Are you a 'customer', or a 'banker'? ");
    	while(true) {
			input = scan.nextLine();
			//if the user entered either of the two supported options, they can get into the system
			if(input.equals("customer")) {
				cust.login();
				break;
			}
			else if(input.equals("banker")) {
				b1.login();
				 break;
			}
			//otherwise, print an error message
			else {
				System.err.println("That answer is invalid. Do try again..");
			}
		}
    }
}
