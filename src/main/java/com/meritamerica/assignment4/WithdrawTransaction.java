package com.meritamerica.assignment4;

import java.util.Date;

public class WithdrawTransaction extends Transaction {



	WithdrawTransaction(BankAccount targetAccount, double amount) {
		Transaction.targetAccount = targetAccount;
		Transaction.amount = amount;
		Transaction.date = new Date();
	}

	@Override
	public void process()
			throws NegativeAmountException, ExceedsAvailableBalanceException, ExceedsFraudSuspicionLimitException {
		

//		if(targetAccount instanceof CDAccount) {
//			System.out.println("\n\n\n OOOO! \n\n\n");
//			throw new ExceedsFraudSuspicionLimitException();
//		}
			
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
