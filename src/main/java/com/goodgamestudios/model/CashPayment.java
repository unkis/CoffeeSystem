package com.goodgamestudios.model;

/**
 * Created by unkiss on 22.10.15.
 */
public class CashPayment extends PaymentType
{

	@Override
	public String getName()
	{
		return "Cash";
	}

	@Override
	protected void calcSoldPerPayment()
	{
		SoldCoffee.soldCoffeeCash.incrementAndGet();

	}

	public long getDuration()
	{
		return 500;
	}



}
