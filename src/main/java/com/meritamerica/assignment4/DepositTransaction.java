package com.meritamerica.assignment4;

import java.util.Date;

public class DepositTransaction extends Transaction {
	private BankAccount targetAccount;
	private double amount;
	private Date date;

	DepositTransaction(BankAccount targetAccount, double amount) {
		this.targetAccount = targetAccount;
		this.amount = amount;
		this.date = new Date();
	}

	@Override
	public void process()
			throws NegativeAmountException, ExceedsAvailableBalanceException, ExceedsFraudSuspicionLimitException {
		if (amount > 1000) {
		throw new ExceedsFraudSuspicionLimitException();}
			if (amount > 0) {
			

				targetAccount.deposit(amount);


				
				
			} else 
				throw new NegativeAmountException();
			
		

	}//process()

}//class
