package com.meritamerica.assignment4;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//ASSIGNMENT 3
//static BankAccount readFromString(String accountData) throws ParseException
//Should throw a java.lang.NumberFormatException if String cannot be correctly parsed
//String writeToString()
//

//ASSIGNMENT 4
//abstract BankAccount
//a. public void addTransaction(Transaction transaction)
//b. public List<Transaction> getTransactions()

public abstract class BankAccount
{
	private double interestRate;

	private long accountNumber;

	private double balance;

	private CDOffering offering;

	private Date openingDate;

	private ArrayList< String > transactionStrings = new ArrayList<>();

	public void setInterestRate(
			double interestRate
	)
	{
		this.interestRate = interestRate;
	}

	public Date getOpeningDate()
	{
		return openingDate;
	}

	public void setOpeningDate(
			Date openDate
	)
	{
		this.openingDate = openDate;
	}

	public void setAccountNumber(
			long accountNumber
	)
	{
		this.accountNumber = accountNumber;
	}

	public void setBalance(
			double balance
	)
	{
		this.balance = balance;
	}

	public CDOffering getOffering()
	{
		return offering;
	}

	public void setOffering(
			CDOffering offering
	)
	{
		this.offering = offering;
	}

	public void transactionStringAdd(
			String s
	)
	{
		transactionStrings.add( s );
	}

	public BankAccount()
	{

	}

	public BankAccount(
			long accountNumber
	)
	{
		this.accountNumber = accountNumber;
	}

	public BankAccount(
			double balance
	)
	{
		this.balance = balance;
		this.accountNumber = MeritBank.getNextAccountNumber();
	}

	public BankAccount(
			CDOffering offering, double balance
	)
	{
		this.offering = offering;
		this.balance = balance;
		this.accountNumber = MeritBank.getNextAccountNumber();
	}

	public BankAccount(
			double balance, double interestRate
	)
	{
		this.balance = balance;
		this.interestRate = interestRate;
		this.accountNumber = MeritBank.getNextAccountNumber();
	}

	public BankAccount(
			long accountNumber, double balance, double interestRate
	)
	{
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.interestRate = interestRate;
		this.accountNumber = accountNumber;
	}

	public BankAccount(
			double balance, double interestRate, Date accountOpenedOn
	)
	{
		this.accountNumber = MeritBank.getNextAccountNumber();
		this.balance = balance;
		this.interestRate = interestRate;
		this.openingDate = accountOpenedOn;
	}

	public long getAccountNumber()
	{
		return accountNumber;
	}

	public double getBalance()
	{
		return balance;
	}

	public double getInterestRate()
	{
		return interestRate;
	}

	public boolean withdraw(
			double amount
	)
	{
		double balanceAfterWithdrawal = balance - amount;
		if( amount > 0 && balanceAfterWithdrawal > 0 )
		{
			balance = balanceAfterWithdrawal;
			return true;
		}
		else
		{
			System.out.println( "Incorrect amount or exceeding balance." );
			return false;
		}
	}

	public boolean deposit(
			double amount
	)
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

	public double futureValue(
			int years
	)
	{
		double futureBalance = MeritBank.recursiveFutureValue( getBalance(), years, getInterestRate() );
//		double futureBalance = getBalance() * Math.pow( 1 + getInterestRate(), years );
		return futureBalance;
	}

	public void addTransaction(
			Transaction transaction
	)
	{
	}

	public List< Transaction > getTransactions()
	{
		return null;
	}
}// class
