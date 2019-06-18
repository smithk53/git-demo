package com.revature.Project0;
import java.util.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
public class Customer extends Banker{
	private static final Logger classlog = LogManager.getLogger(Customer.class.getName());
	private static String loginid = "";
	private static String password = "";
	static boolean yesorno;
	//will store the amount of money the user wishes to withdraw from their account later on
	private static int withdrawamount = 0;
	private static int depoamount = 0;
	private static String transType;
	//will be used to keep track of different transactions
	private static int transactionnum;
	private String response1; //will later store the user's response to question #1
	private String response2; //will later store the user's response to question #2
	//create new instances of objects
	static Deposit depo = new Deposit();
	static Withdraw withdrw = new Withdraw();
	static AccountSummary accsum = new AccountSummary();
	static Random rand = new Random();
	static Scanner scan = new Scanner(System.in);
	static String[] customers = new String[10];
	//will be used later to help in the randomization of the transaction# and adding customers to the above array
	static int i = 0;
	public int getTransactionNum() {
		return transactionnum;
	}
	public static String getCustomerLoginID() {
		customers[i] = loginid;
		return customers[i];
	}
	public static String getCustomerPassword() {
		return password;
	}
	public static String getTransactType() {
		return transType;
	}
	public static int getWithdrawAmount() {
		return withdrawamount;
	}
	public static int getDepoAmount() {
		return depoamount;
	}
	public static void createCustomer() throws SQLException{
		classlog.info("Method 'createCustomer' has been activated");
		/*String sqlquery = "INSERT INTO CUSTOMER"
							+ "VALUES('"+getCustomerLoginID()+"','"+getCustomerPassword()+"','"+getDepoAmount()+"','"
							+getWithdrawAmount()+"','"+accsum.getAccountStatus()+"','"+accsum.getAccountBalance()+"'"; 
		*/
		String sqlquery = "SELECT * FROM ARTIST";
		//Connection connect = ConnectToDatabase.acquireConnection();
		//Connection con = ConnectionUtil.getConnection();
		Connection con = ConnectionUtil.getConnection();
		System.out.println("Connection: "+con);
		PreparedStatement stmt = con.prepareStatement(sqlquery);
		ResultSet rs = stmt.executeQuery();
		classlog.info("Artists' info is being collected from the database and displayed");
		System.out.println("ARTISTID\t"+"NAME");
		while(rs.next()) {
			int artistid = rs.getInt("ARTISTID");
			String artistname = rs.getString("NAME");
			System.out.println(artistid +"\t\t"+artistname);
		}
		//PreparedStatement pstmt1 = con.prepareStatement(sqlquery);
		/*pstmt1.setString(1,getCustomerLoginID());
		pstmt1.setString(2,getCustomerPassword());
		pstmt1.setInt(3,getDepoAmount());
		pstmt1.setInt(4,getWithdrawAmount());
		pstmt1.setString(5,accsum.getAccountStatus());
		pstmt1.setInt(6,accsum.getAccountBalance());
		pstmt1.executeUpdate();*/
	}
	//method to prompt the user for login credentials and process them locally
	public void login() 
	{
		//loop till the customer enters a valid username
		while(true) {
			classlog.info("User has started the log in process");
			//prompt customer for a id
			//user's id must be 5 characters. If it isn't, prompt the customer for an id again 
			System.out.print("Please enter a username: ");
			//store the user's input in a string object
			loginid = scan.nextLine();
			if(loginid.length() >= 5) {
				classlog.info("Customer "+ loginid+" has logged in!");
				break;
			}
			System.out.println("Sorry, you must enter at least 5 characters");
		}
		while(true) {
			//if the user's indicated password is not at least 7 characters long, prompt the user again
			System.out.print("Now enter a password: ");
			//pick up user's input from standard input stream and store it in a string object
			password = scan.nextLine();
			if(password.length() >= 7) {
				classlog.info("password entered: "+ password);
				break;
			}
			System.out.println("Passwords must be at least 7 characters long");	
		}
		
		//--Main fragment that has the core banking functionality--
		while(true) {
			//user has made it into the banking system if they have made it this far
			System.out.print("Would you like to logout('y' or 'n')? ");
			response1 = scan.nextLine();
			accsum.setAccountStatus("active");
			//user has indicated that they want to logout
			if(response1.equals("y")) {
				System.out.println("Ok. Say less..");
				accsum.setAccountStatus("inactive");
				//once you create it, call the function that displays the new account status here
				System.exit(0);
				//change the status of the account in the database -here-
			}
			//user has indicated that they want to continue operating on their bank account
			else if(response1.equals("n")) 
			{
				System.out.print("What would you like to do next ('withdraw', 'viewbal', 'deposit')? ");
				response2 = scan.nextLine();
				//user has indicated that they want to see their account balance, so show them their balance
				if(response2.equals("viewbal")) 
				{
					System.out.println("Your balance is: "+"$"+accsum.getAccountBalance());
					transType = "viewbal";
				}//end if "viewbal" call
				//user wants to make a withdrawal, so prompt them for an amount if their account is not on E
				else if(response2.equals("withdraw")) 
				{
					//if the user doesn't have a zero balance, carry out the logic below
					if(accsum.getAccountBalance() != 0) {
						System.out.print("How much would you like to withdraw? ");
						//anything other than an integer will throw an error
						withdrawamount = scan.nextInt();
						//check if the user's account balance is lower or higher than their requested withdrawal
						if(withdrawamount > accsum.getAccountBalance()) {
							System.out.println("Your account balance is "+"$"+accsum.getAccountBalance()+"\nwhich is lower than"
														+ " your specified withdrawal amount!");
						}
						//if the requested withdrawal amount is lower than the account balance, grant it
						else {
							//each transaction number should be different
							transactionnum = rand.nextInt(5200);
							transactionnum = transactionnum+rand.nextInt(100);
							System.out.println("'transactionnum' is: "+transactionnum);
							withdrw.subtractfrombal(withdrawamount);
							System.out.println("Your new balance is: "+"$"+accsum.getAccountBalance());
						}
					}
					//user's account is on E. Print an informative message
					else {
						System.out.println("Insufficient funds..");
						System.out.println("Your current balance is: "+"$"+accsum.getAccountBalance());
					}
					transType = "withdraw";
				}//end of "withdraw" call
				//user wants to make a deposit, so process the amount
				else if(response2.equals("deposit")){
					//each transaction number should be different
					transactionnum = rand.nextInt(5200);
					transactionnum = transactionnum+rand.nextInt(200); 
					System.out.println("'transactionnum' is: "+transactionnum);
					System.out.print("How much would you like to deposit? $");
					depoamount = scan.nextInt();
					depo.addtobal(depoamount);
					transType = "deposit";
					System.out.println("Your new balance is: "+"$"+accsum.getAccountBalance());
				}//end of "deposit" call
				else {
					System.err.println("That is not an integrated function. Try again.");
				}
				try {
					createCustomer();
				}
				catch(SQLException sqe) {
					sqe.printStackTrace();
				}
				i++;
			}
		}
	}
}
