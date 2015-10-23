package com.goodgamestudios.model;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by unkiss on 22.10.15.
 */
public class CoffeeSystemResult
{

	public static final AtomicInteger soldCoffeeTotal = new AtomicInteger();
	public static final AtomicInteger soldCoffeeCash = new AtomicInteger();
	public static final AtomicInteger soldCoffeeCredit = new AtomicInteger();
	public static final ConcurrentMap<Thread,Map<String,Integer>> dispensedCoffeeByMachineMap = new ConcurrentHashMap<>();



	public static String getResultInfo()
	{
		final StringBuilder sb = new StringBuilder("CoffeeSystemResult{");
		sb.append("Sold coffee total = ").append(soldCoffeeTotal.get()).
		append(", Sold coffee payed with cash = ").append(soldCoffeeCash.get()).
		append(", Sold coffee payed with credit = ").append(soldCoffeeCredit.get());

		sb.append(getResultInfoForDispendedCoffee());

		sb.append("\nDispensed by each coffee machine ").append(dispensedCoffeeByMachineMap);

		sb.append('}');
		return sb.toString();
	}

	private static String getResultInfoForDispendedCoffee() {
		StringBuffer sb = new StringBuffer("\nDispensed by each coffee machine ");
		Collection<Map<String, Integer>> machineResults = dispensedCoffeeByMachineMap.values();
		for (int i = 0; i < machineResults.size(); i++) {
			sb.append("\n\tCoffee machine ").append((i+1));

			sb.append("\n\t\t");
		}




		return sb.toString();
	}


}
