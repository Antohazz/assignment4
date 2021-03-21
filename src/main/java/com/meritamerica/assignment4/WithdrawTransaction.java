package com.meritamerica.assignment4;

public class WithdrawTransaction extends Transaction
{
	WithdrawTransaction(
			BankAccount targetAccount, double amount
	)
	{
		this.setTargetAccount( targetAccount );
		this.setAmount( amount );
	}

	@Override public void process() throws NegativeAmountException, ExceedsAvailableBalanceException, ExceedsFraudSuspicionLimitException
	{
		double amount = this.getAmount();

		BankAccount target = this.getTargetAccount();

		if( amount > 1000 )
			throw new ExceedsFraudSuspicionLimitException();

		if( amount < 0 )
			throw new NegativeAmountException();

		double balanceAfterWithdrawal = target.getBalance() - amount;
		if( balanceAfterWithdrawal < 0 )
			throw new ExceedsAvailableBalanceException();

		target.withdraw( amount );
	}
}
