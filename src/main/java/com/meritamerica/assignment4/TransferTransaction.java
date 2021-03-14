package com.meritamerica.assignment4;

import java.util.Date;

public class TransferTransaction extends Transaction {


	TransferTransaction(BankAccount sourceAccount, BankAccount targetAccount, double amount) {
		Transaction.sourceAccount = sourceAccount;
		Transaction.targetAccount = targetAccount;
		Transaction.amount = amount;
		Transaction.date = new Date();
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
