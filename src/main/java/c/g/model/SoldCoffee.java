package c.g.model;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by unkiss on 22.10.15.
 */
public class SoldCoffee
{

	public static final AtomicInteger soldCoffeeTotal = new AtomicInteger();
	public static final AtomicInteger soldCoffeeCash = new AtomicInteger();
	public static final AtomicInteger soldCoffeeCredit = new AtomicInteger();



	public static String getSoldInfo()
	{
		final StringBuilder sb = new StringBuilder("SoldCoffee{");
		sb.append("Sold coffee total = ").append(soldCoffeeTotal.get()).
		append(", Sold coffee payed with cash = ").append(soldCoffeeCash.get()).
		append(", Sold coffee payed with credit = ").append(soldCoffeeCredit.get())
		;

		sb.append('}');
		return sb.toString();
	}
}
