package com.revature.Project0;
import java.util.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Banker{
	static Customer c2 = new Customer();
	static AccountSummary asum = new AccountSummary();
	private static String BankerID = "";
	private static String BankerPassword = "";
	static Random rand = new Random();
	static Scanner scan = new Scanner(System.in);
	static String response = "";
	private static final Logger bankclasslog = LogManager.getLogger(Banker.class.getName());
	//Method to prompt banker for a username till they enter a valid one
	public void setBankerID() {
		while(true) {
			System.out.print("Welcome new associate! Please enter your name: ");
			BankerID = scan.nextLine();
			if(BankerID.length() >= 4 && BankerID.length() <= 7) {
				break;
			}
			else {
				System.out.println("The id must be between 4 and 7 characters in length.");
			}
		}
	}
	public static String getBankerID() {
		return BankerID;
	}
	public static String getBankerPass() {
		return BankerPassword;
	}
	//Method to prompt the banker for a password and return it
	public void setBankerPass() {
		while(true) {
			System.out.print("Thanks. Now, enter your password: ");
			BankerPassword = scan.nextLine();
			if(BankerPassword.length() >=4 && BankerPassword.length() <= 7 ) {
				break;
			}
			else {
				System.out.println("The password must be between 4 and 7 characters in length as well!");
			}
		}
	}
	public static void createBanker() throws SQLException{
		bankclasslog.info("Banker is being created.");
		String createbankerquery = "INSERT INTO BANKER"
							+ "\nVALUES('"+getBankerID()+"','"+getBankerPass()+"',"+c2.getTransactionNum()+",'"
							+c2.getTransactType()+"','"+c2.getCustomerLoginID()+"'"+")"; 
		
		c2.con = ConnectionUtil.getConnection();
		PreparedStatement stmt = c2.con.prepareStatement(createbankerquery);
		c2.rs = stmt.executeQuery();
		bankclasslog.info("Banker is being added to the database");
	}
	public static void viewBanker() throws SQLException{
		bankclasslog.info("Banker is being displayed");
		String vbquery = "SELECT BANKERID,PASSWORD FROM BANKER";
		PreparedStatement stmt2 = c2.con.prepareStatement(vbquery);
		c2.rs = stmt2.executeQuery();
		System.out.println("BANKERID\t"+"PASSWORD");
		while(c2.rs.next()) {
			String bankid = c2.rs.getString("BANKERID");
			String bankpass = c2.rs.getString("PASSWORD");
			System.out.println(bankid+"\t\t"+bankpass);
		}
	}
	public static void viewTransaction() throws SQLException{
		bankclasslog.info("preparing to display the last customer's transaction");
		String transquery = "SELECT TRANSACTIONNUM,TRANSACTIONTYPE,HOLDERID,Customer.DEPOSIT,Customer.WITHDRAW"
							+"\nFROM BANKER"
							+"\nINNER JOIN CUSTOMER"
							+"\nON BANKER.HOLDERID = CUSTOMER.CUSTOMERID";
		PreparedStatement stmt3 = c2.con.prepareStatement(transquery);
		c2.rs = stmt3.executeQuery();
		System.out.println("TRANSACTIONNUM\t"+"TRANSACTIONTYPE\t"+"HOLDERID\t"+"DEPOSIT\t"+"WITHDRAW");
		while(c2.rs.next()) {
			String transnum = c2.rs.getString("TRANSACTIONNUM");
			String transtype = c2.rs.getString("TRANSACTIONTYPE");
			String accholder = c2.rs.getString("HOLDERID");
			int depomade = c2.rs.getInt("DEPOSIT");
			int withdmade = c2.rs.getInt("WITHDRAW");
			System.out.println(transnum+"\t"+transtype+"\t"+accholder+"\t"+depomade+"\t"+withdmade);
		}
	}
	public static void getCustomerInfo() throws SQLException{
		bankclasslog.info("Getting the customer's info..");
		String cusinfoquery = "SELECT HOLDERID,CUSTOMER.PASSWORD,CUSTOMER.DEPOSIT,Customer.WITHDRAW,CUSTOMER.ACCOUNTSTATUS,CUSTOMER.ACCBALANCE"
				+"\nFROM BANKER"
				+ "\nINNER JOIN CUSTOMER" + 
				"\nON BANKER.HOLDERID = CUSTOMER.CUSTOMERID";
		PreparedStatement stmt4 = c2.con.prepareStatement(cusinfoquery);
		c2.rs = stmt4.executeQuery();
		System.out.println("HOLDERID\t"+"PASSWORD\t"+"DEPOSIT\t"+"WITHDRAW\t"+"ACCOUNTSTATUS\t"+"ACCBALANCE");
		while(c2.rs.next()) {
			String holderid = c2.rs.getString("HOLDERID");
			String cuspass = c2.rs.getString("PASSWORD");
			int cusdepo = c2.rs.getInt("DEPOSIT");
			int cuswithd = c2.rs.getInt("WITHDRAW");
			String accstat = c2.rs.getString("ACCOUNTSTATUS");
			int accbal = c2.rs.getInt("ACCBALANCE");
			System.out.println(holderid+"\t"+cuspass+"\t"+cusdepo+"\t"+cuswithd+"\t"+accstat+"\t"+accbal);
		}
	}
	public void login() 
	{
	  if(c2.customercreated = true) 
	  {
		  setBankerID();
		  setBankerPass();
		  try 
		  {
			createBanker();
			while(true) {
				System.out.print("What would you like to do? ('see' your credentials, view customers' 'transactions', get their account 'info')");
				response = scan.nextLine();
				if(response.equals("see")) {
					//bankerid,bankerpass
					viewBanker();
				}
				else if(response.equals("transactions")) {
					/*add the transaction# and type to the database, 
				 	* then display that stuff along with the details of the transaction through a query
				 	*/
					//transactionnum,transactiontype,holderid,deposit,withdraw
					viewTransaction();
				}
				else if(response.equals("info"))
				{
					//customerid,password,deposit,accountstatus,accountbalance
					getCustomerInfo();
				}
				else {
					System.err.println("That response was invalid.");
				}
				System.out.print("Would you like to logout('y' or 'n'? ");
				response = scan.nextLine();
				if(response.equals("y")) {
					break;
				}
				else if(response.equals("n")) {
					continue;
				}
			}
		  }
		  catch(SQLException sqe) {
			  sqe.printStackTrace();
		  }
		}
	  	else {
	  		System.out.println("Customer has not been created yet! Taking you back to the login screen..");
	  	}
	}
}
