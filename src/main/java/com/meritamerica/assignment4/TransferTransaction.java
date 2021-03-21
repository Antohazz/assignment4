package com.meritamerica.assignment4;

public class TransferTransaction extends Transaction
{
	TransferTransaction(
			BankAccount sourceAccount, BankAccount targetAccount, double amount
	)
	{
		this.setSourceAccount( sourceAccount );
		this.setTargetAccount( targetAccount );
		this.setAmount( amount );
	}

	@Override public void process() throws NegativeAmountException, ExceedsAvailableBalanceException, ExceedsFraudSuspicionLimitException
	{
		double amount = this.getAmount();

		this.getSourceAccount().withdraw( amount );
		this.getTargetAccount().deposit( amount );
	}
}