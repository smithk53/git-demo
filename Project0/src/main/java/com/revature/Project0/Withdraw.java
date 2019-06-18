package com.revature.Project0;

public class Withdraw {
	static boolean withdrmade;
	static AccountSummary acsum = new AccountSummary();
	//method to make bank withdrawals possible for the user
	public void subtractfrombal(int a) {
		withdrmade = true;
		acsum.accountbal = acsum.accountbal - a;
	}
}
