package com.meritamerica.assignment4;

import java.util.Date;

public class TransferTransaction extends Transaction {
	private BankAccount sourceAccount;
	private BankAccount targetAccount;
	private double amount;
	private Date date;


	TransferTransaction(BankAccount sourceAccount, BankAccount targetAccount, double amount) {
		this.sourceAccount = sourceAccount;
		this.targetAccount = targetAccount;
		this.amount = amount;
		this.date = new Date();
	}

	@Override
	public void process()
			throws NegativeAmountException, ExceedsAvailableBalanceException, ExceedsFraudSuspicionLimitException {

		if (amount > 0) {

			if (amount <= sourceAccount.getBalance()) {


					sourceAccount.withdraw(amount);
					targetAccount.deposit(amount);

					if (amount > 1000) {
					throw new ExceedsFraudSuspicionLimitException();}

			} else
				throw new ExceedsAvailableBalanceException();

		} else
			throw new NegativeAmountException();

	}

}
