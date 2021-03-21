package com.meritamerica.assignment4;

import java.util.ArrayList;

public class FraudQueue
{
	private ArrayList< Transaction > q = new ArrayList<>();

	FraudQueue()
	{
	}

	public void addTransaction(
			Transaction transaction
	)
	{
		this.q.add( transaction );
	}

	public Transaction getTransaction()
	{
		return (Transaction)this.q.iterator();
	}
}
