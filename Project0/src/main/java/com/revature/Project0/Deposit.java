package com.revature.Project0;
public class Deposit {
	static boolean depomade;
	static AccountSummary acsum = new AccountSummary();
	//method to make bank deposits possible for the user
	public void addtobal(int a) {
		depomade = true;
		acsum.accountbal= acsum.accountbal + a;
	}
}
