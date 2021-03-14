package com.meritamerica.assignment4;

import java.util.Date;

public class DepositTransaction extends Transaction {


	DepositTransaction(BankAccount targetAccount, double amount) {

		Transaction.targetAccount = targetAccount;
		Transaction.amount = amount;
		Transaction.date = new Date();
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
