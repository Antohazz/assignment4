package com.meritamerica.assignment4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.meritamerica.assignment4.AccountHolder;
import com.meritamerica.assignment4.CDAccount;
import com.meritamerica.assignment4.CDOffering;
import com.meritamerica.assignment4.CheckingAccount;
import com.meritamerica.assignment4.MeritBank;
import com.meritamerica.assignment4.SavingsAccount;

import static org.junit.Assert.*;

public class AntonAndAshwinsTests
{
	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();

	@Test public void testFraudReviewed() throws ExceedsCombinedBalanceLimitException
	{
		Transaction t;

		byte b = 0;

		do
		{
			t = MeritBank.getFraudQueue().getTransaction();
			b++ ;
		}
		while( t != null );

		AccountHolder accountHolder = new AccountHolder(
				"Sadiq", "", "Manji", "123456789" );

		CheckingAccount ca1, ca2;
		ca1 = accountHolder.addCheckingAccount( 1000 );
		ca2 = accountHolder.addCheckingAccount( 1001 );

		byte c = 0;
		do
		{
			t = MeritBank.getFraudQueue().getTransaction();
			c++ ;
		}
		while( t != null );

		assertEquals( c, b );
	}
}
