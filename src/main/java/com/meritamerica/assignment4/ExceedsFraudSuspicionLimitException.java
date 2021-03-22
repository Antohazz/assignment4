package com.meritamerica.assignment4;

public class ExceedsFraudSuspicionLimitException extends Exception
{
	ExceedsFraudSuspicionLimitException(
			Transaction t
	)
	{
		MeritBank.getFraudQueue().addTransaction( t );
	}

	private static final long serialVersionUID = 1L;
}
