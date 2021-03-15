package com.meritamerica.assignment4;

public class CDOffering
{
	private double interestRate;
	private int term;

	public CDOffering(
			int term, double interestRate
	)
	{
		this.term = term;
		this.interestRate = interestRate;
	}

	public static CDOffering readFromString(
			String cdOfferingDataString
	)
	{
		int firstCh = 0;
		int lastCh = cdOfferingDataString.indexOf( "," );
		byte term = Byte.parseByte( cdOfferingDataString.substring( firstCh, lastCh ) );
		firstCh = lastCh + 1;
		double interestRate = Double.parseDouble( cdOfferingDataString.substring( firstCh ) );
		CDOffering offering = new CDOffering( term, interestRate );
		return offering;
	}

	public int getTerm()
	{
		return term;
	}

	public double getInterestRate()
	{
		return interestRate;
	}

//	// Outputs account info
	public String toString()
	{
		String info = getTerm() + "," + getInterestRate() + "\n";

		return info;
	}

}
