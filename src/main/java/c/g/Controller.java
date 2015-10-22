package c.g;

import c.g.model.Programmer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by ybarvenko on 22.10.2015.
 */
public class Controller {

    ExecutorService pickTheFavoriteTypeOfCoffeeExecutor = Executors.newFixedThreadPool(10);

    ExecutorService findAndPickupExecutor = Executors.newFixedThreadPool(2);


    public Controller(){

    }


    public void start(Collection<Programmer> programmers) throws InterruptedException {


        List<Callable<Programmer>> pickTheFavoriteTypeOfCoffeeCallableList = new ArrayList<>();

        programmers.forEach(programmer -> {
            PickTheFavoriteTypeOfCoffeeCallable pickTheFavoriteTypeOfCoffee = new PickTheFavoriteTypeOfCoffeeCallable(programmer);
            programmer.setStartTrime(System.currentTimeMillis());
            pickTheFavoriteTypeOfCoffeeExecutor.submit(pickTheFavoriteTypeOfCoffee);
            //pickTheFavoriteTypeOfCoffeeCallableList.add(pickTheFavoriteTypeOfCoffee);
        });

        int count = programmers.size();
        while (count!=0){

        }

        pickTheFavoriteTypeOfCoffeeExecutor.invokeAll(pickTheFavoriteTypeOfCoffeeCallableList).stream()
                .map(future -> {
                    try {
                        return future.get();
                    } catch (Exception e) {
                        throw new IllegalStateException(e);
                    }
                })
                .forEach(programmer -> {
                    System.out.println("programmer = " + programmer+" \texecution time = "+(System.currentTimeMillis()-programmer.getStartTrime()));
                });

    }

}
