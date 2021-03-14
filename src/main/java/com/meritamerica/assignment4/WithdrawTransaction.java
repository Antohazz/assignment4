package com.meritamerica.assignment4;

import java.util.Date;

public class WithdrawTransaction extends Transaction {
	private BankAccount targetAccount;
	private double amount;
	private Date date;


	WithdrawTransaction(BankAccount targetAccount, double amount) {
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

			if (amount <= targetAccount.getBalance()) {

				
					targetAccount.withdraw(amount);
					
					

			} else
				throw new ExceedsAvailableBalanceException();

		} else
			throw new NegativeAmountException();

	}

}
