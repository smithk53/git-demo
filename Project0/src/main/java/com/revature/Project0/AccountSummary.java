package com.revature.Project0;

public class AccountSummary {
	//user starts with $3000 in their account
	static int accountbal = 3000;
	private String accountstat;
	//Method to get the user's account status (active = true/inactive = false)
	public String getAccountStatus() {
		return accountstat;
	}
	public void setAccountStatus(String a) {
		accountstat = a;
	}
	public int getAccountBalance() {
		return accountbal;
	}
}
