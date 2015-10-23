package com.goodgamestudios.model;

/**
 * Created by unkiss on 22.10.15.
 */
public class CreditPayment extends PaymentType
{

	@Override
	public String getName()
	{
		return "Credit";
	}

	@Override
	protected void calcSoldPerPayment()
	{
		CoffeeSystemResult.soldCoffeeCredit.incrementAndGet();
	}

	@Override
	public long getDuration()
	{
		return 250;
	}



}
