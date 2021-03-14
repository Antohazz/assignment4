package com.meritamerica.assignment4;

public class DepositTransaction extends Transaction
{
	DepositTransaction(
			BankAccount targetAccount, double amount
	)
	{
	}

	
	@Override public void process() throws NegativeAmountException, ExceedsAvailableBalanceException, ExceedsFraudSuspicionLimitException
	{


		{
			if( amount > 0 )
			{
				balance += amount;
				return true;
			}
			else
			{
				System.out.println( "Can't be zero or negative." );
				return false;
			}
		}

	}

}
