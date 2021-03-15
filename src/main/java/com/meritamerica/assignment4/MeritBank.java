package com.meritamerica.assignment4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class MeritBank
{
	

	public static long accountNumber = 1;
	public static AccountHolder[] accountHolders = new AccountHolder[ 1 ];
	public static CDOffering[] cdOfferings = new CDOffering[ 0 ];
	public static CDOffering offering;
	public static FraudQueue fq;
	
	

//i. If transaction does not violate any constraints, deposit/withdraw values
//from the relevant BankAccounts and add the transaction to the relevant
//BankAccounts

//ii. If the transaction violates any of the basic constraints (negative amount,
//exceeds available balance) the relevant exception should be thrown and
//the processing should terminate

//iii. If the transaction violates the $1,000 suspicion limit, it should simply be
//added to the FraudQueue for future processing
	public static boolean processTransaction(
			Transaction transaction
	) throws NegativeAmountException, ExceedsAvailableBalanceException, ExceedsFraudSuspicionLimitException
	{
		try {
		transaction.process();
		
		} catch (Exception e) {
			throw e;
		}
		return true;

	}

	public static FraudQueue getFraudQueue()
	{
		return null;

	}

//i. Return null if account not found
	public static BankAccount getBankAccount(
			long accountId
	)
	{	
		for (int i = 0; i < MeritBank.getAccountHolders().length; i++) {
			if (MeritBank.getAccountHolders()[i] != null) {
				
				for (int j = 0; j < MeritBank.getAccountHolders()[i].getNumberOfCheckingAccounts(); j++) {
					if(accountId == MeritBank.getAccountHolders()[i].getCheckingAccounts()[j].getAccountNumber()) {
						return MeritBank.getAccountHolders()[i].getCheckingAccounts()[j];
						}
					}
				
			
				for (int j = 0; j < MeritBank.getAccountHolders()[i].getNumberOfSavingsAccounts(); j++) {
					if(accountId == MeritBank.getAccountHolders()[i].getSavingsAccounts()[j].getAccountNumber()) {
						return MeritBank.getAccountHolders()[i].getSavingsAccounts()[j];
					}
				}
				
				for (int j = 0; j < MeritBank.getAccountHolders()[i].getNumberOfCDAccounts(); j++) {
					if(accountId == MeritBank.getAccountHolders()[i].getCDAccounts()[j].getAccountNumber()) {
						return MeritBank.getAccountHolders()[i].getCDAccounts()[j];
					}
				}

				
			}		
		}
		return null;

	}

//AMEND


//a. static boolean readFromFile(String fileName)
//i. Should also read BankAccount transactions and the FraudQueue
// Read DATABASE from FILE
	
	
	
	static boolean readFromFile(
			String fileName
	)
	{

		accountHolders = new AccountHolder[ 1 ];
		cdOfferings = new CDOffering[ 0 ];

		try
		{
			BufferedReader rd = new BufferedReader( new FileReader( fileName ) );

			MeritBank.setNextAccountNumber( Long.parseLong( rd.readLine() ) );

// Read CDOffers
			int numOfCDOfferings = Integer.parseInt( rd.readLine() );
			int n = 0;
			cdOfferings = new CDOffering[ numOfCDOfferings ];

			while( numOfCDOfferings > 0 )
			{
				cdOfferings[ n ] = CDOffering.readFromString( rd.readLine() );
				n++ ;
				numOfCDOfferings-- ;
			}

//Read Account Holders
			int numOfAccountHolders = Integer.parseInt( rd.readLine() );

			while( numOfAccountHolders > 0 )
			{
				AccountHolder ac = AccountHolder.readFromString( rd.readLine() );
				addAccountHolder( ac );

//read checking accounts
				int numOfCheckAcc = Integer.parseInt( rd.readLine() );
				for (int i = 0; i < numOfCheckAcc; i++)
				{
					ac.addCheckingAccount( CheckingAccount.readFromString( rd.readLine() ) );
//read checking account transactions:
					int numOfCheckTrx = Integer.parseInt( rd.readLine() );
					for( int t = 0; t < numOfCheckTrx; t++ )
					{
//						ac.getCheckingAccounts()[i].addTransaction( Transaction.readFromString( rd.readLine() ));
						ac.getCheckingAccounts()[i].getTransStr().add(rd.readLine()); // stored in ArrayList
					}

					
				}

//read savings accounts	
				int numOfSaveAcc = Integer.parseInt( rd.readLine() );
				for (int i = 0; i < numOfSaveAcc; i++)
				{
					ac.addSavingsAccount( SavingsAccount.readFromString( rd.readLine() ) );
//read savings account transactions:
					int numOfSaveTrx = Integer.parseInt( rd.readLine() );
					for( int t = 0; t < numOfSaveTrx; t++ )
					{
//						ac.getSavingsAccounts()[i].addTransaction( Transaction.readFromString( rd.readLine() ));
						ac.getSavingsAccounts()[i].getTransStr().add(rd.readLine()); //stored in ArrayList
					}					
					
					
				}
//read CD accounts						
				int numOfCDAcc = Integer.parseInt( rd.readLine() );
				for (int i = 0; i < numOfCDAcc; i++)
				{
					ac.addCDAccount( CDAccount.readFromString( rd.readLine() ) );
//read CDaccount transactions:
					int numOfCDTrx = Integer.parseInt( rd.readLine() );
					for( int t = 0; t < numOfCDTrx; t++ )
					{
//						ac.getCDAccounts()[i].addTransaction( Transaction.readFromString( rd.readLine() ));
						ac.getCDAccounts()[i].getTransStr().add(rd.readLine()); //stored in ArrayList
					}
					
				}

				numOfAccountHolders-- ;
			} // Read Account Holders
			
//read fraud queue:		
			fq = new FraudQueue();
			int numOfFQ = Integer.parseInt( rd.readLine() );
			while( numOfFQ > 0 ) {
				
//				System.out.println(rd.readLine());
				fq.addTransStr(rd.readLine());//Could be stored in ArrayList
				
				numOfFQ--;
			}
			

			rd.close();

			sortAccountHolders();

			System.out.println( AccountHolder.writeToString() );

			return true;

		}
		catch( Exception e )
		{
			e.printStackTrace();
			return false;
		} // catch

	}// readFromFile

//b. static boolean writeToFile(String fileName)
//i. Should also write BankAccount transactions and the FraudQueue
// Write DATABASE to FILE
	static boolean writeToFile(
			String fileName
	)
	{

		String outp = getNextAccountNumber() + "\n";

		for( int i = 0; i < cdOfferings.length; i++ )
		{
			if( cdOfferings[ i ] != null )
			{
				outp += cdOfferings[ i ].toString();
			}
		}

		outp += accountHolders.length + "\n";
		for( int i = 0; i < accountHolders.length; i++ )
		{
			if( accountHolders[ i ] != null )
			{
				outp += accountHolders[ i ].toStringForFile();
			}
		}
		
		int s = fq.getTransStr().size();
		outp += s + "\n";
		for(int i = 0; i < s; i++)
		outp += fq.getTransStr().get(i)+ "\n";
		
		System.out.println( outp );
		PrintWriter out;
		try
		{
			out = new PrintWriter( fileName );
			out.println( outp );
			out.close();
			return true;
		}
		catch( FileNotFoundException e )
		{
			e.printStackTrace();
			return false;
		}

	}

	static AccountHolder[] sortAccountHolders()
	{
		int l = 0;
		while( accountHolders[ l ] != null )
		{
			l++ ;
		}
		Arrays.sort( accountHolders, 0, l );

		return accountHolders;
	}

	static void setNextAccountNumber(
			long nextAccountNumber
	)
	{
		accountNumber = nextAccountNumber;
	}

	public static long getNextAccountNumber()
	{
		return accountNumber++ ;
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////
	public static void addAccountHolder(
			AccountHolder accountHolder
	)
	{

		for( int i = 0; i < accountHolders.length; i++ )
		{
			if( accountHolders[ i ] == null )
			{
				accountHolders[ i ] = accountHolder;

				// Extending Array if full
				if( i == accountHolders.length - 1 )
				{
					AccountHolder[] temp = new AccountHolder[ accountHolders.length * 2 ];
					for( int j = 0; j < accountHolders.length; j++ )
					{
						temp[ j ] = accountHolders[ j ];
					}
					accountHolders = temp;
				}

				break;
			}
		}

	}

	public static AccountHolder[] getAccountHolders()
	{
		return accountHolders;
	}

	public static CDOffering[] getCDOfferings()
	{
		return cdOfferings;
	}

	public static CDOffering getBestCDOffering(
			double depositAmount
	)
	{
		if( cdOfferings != null )
		{
			double val = 0.0;
			int j = 0;
			for( int i = 0; i < cdOfferings.length; i++ )
			{
				if( (double)recursiveFutureValue( depositAmount, cdOfferings[ i ].getTerm(), cdOfferings[ i ].getInterestRate() ) > val )
				{
					val = (double)recursiveFutureValue( depositAmount, cdOfferings[ i ].getTerm(), cdOfferings[ i ].getInterestRate() );
					j = i;

				}

			}
			return cdOfferings[ j ];
		}
		else
			return null;
	}

	public static CDOffering getSecondBestCDOffering(
			double depositAmount
	)
	{
		if( cdOfferings != null )
		{
			double val = 0.0;
			int j = 0;
			int k = 0;
			for( int i = 0; i < cdOfferings.length; i++ )
			{
				if( (double)recursiveFutureValue( depositAmount, cdOfferings[ i ].getTerm(), cdOfferings[ i ].getInterestRate() ) > val )
				{
					val = (double)recursiveFutureValue( depositAmount, cdOfferings[ i ].getTerm(), cdOfferings[ i ].getInterestRate() );
					j = i;
					k = j;
				}

			}

			return cdOfferings[ k ];
		}
		else
			return null;
	}

	public static void clearCDOfferings()
	{

		cdOfferings = null;
	}

	public static void setCDOfferings(
			CDOffering[] offerings
	)
	{

		cdOfferings = offerings;
	}

	public static double totalBalances()
	{
		double total = 0.0;
		for( int i = 0; i < accountHolders.length; i++ )
		{
			if( accountHolders[ i ] != null )
				total += accountHolders[ i ].getCombinedBalance();
			else
				break;
		}

		return total;
	}
	
	public static double power(
			double base,
			int exponent
	)
	{
		if( exponent == 0 )
			return 1;
		else
			return base * power( base, ( exponent - 1 ) );
	}

	public static double recursiveFutureValue(
			double amount,
			int years,
			double interestRate
	)
	{
		return amount * power( ( 1 + interestRate ), years );
	}


}
