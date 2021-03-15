package com.meritamerica.assignment4;

public class WithdrawTransaction extends Transaction
{
	WithdrawTransaction(
			BankAccount targetAccount, double amount
	) throws NegativeAmountException, ExceedsAvailableBalanceException, ExceedsFraudSuspicionLimitException
	{
		double amountAbsolute = Math.abs( amount );
		if( amountAbsolute > 1000 )
			throw new ExceedsFraudSuspicionLimitException();

		if( amount > 0 )
			throw new NegativeAmountException();

		double balanceAfterWithdrawal = targetAccount.getBalance() - amountAbsolute;
		if( balanceAfterWithdrawal < 0 )
			throw new ExceedsAvailableBalanceException();

		targetAccount.withdraw( amountAbsolute );
	}

	@Override public void process() throws NegativeAmountException, ExceedsAvailableBalanceException, ExceedsFraudSuspicionLimitException
	{
		// TODO Auto-generated method stub

	}

}
