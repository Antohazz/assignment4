package com.meritamerica.assignment4;

import java.util.ArrayList;
import java.util.List;

public class FraudQueue
{
	protected static List<String> listOfTransStr = new ArrayList<>();
	
	public void addTransStr(String transaction) {
		listOfTransStr.add(transaction);
	}

	public List<String> getTransStr() {
		return listOfTransStr;
	}
		
	
	FraudQueue()
	{
	}

	public void addTransaction(
			Transaction transaction
	)
	{
	}

	public Transaction getTransaction()
	{
		return null;
	}
}
