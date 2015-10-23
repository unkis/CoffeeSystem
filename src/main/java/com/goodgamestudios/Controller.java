package com.goodgamestudios;

import com.goodgamestudios.model.Programmer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.*;

/**
 * Created by ybarvenko on 22.10.2015.
 */
public class Controller {

    private static final Logger LOG = LoggerFactory.getLogger(Controller.class);

    public static final ExecutorService pickTheFavoriteTypeOfCoffeeExecutor = Executors.newFixedThreadPool(10);
    public static final ExecutorService payExecutor = Executors.newFixedThreadPool(5);
    private static final ExecutorService findAndPickupExecutor = Executors.newFixedThreadPool(2);
    public static final CompletionService<Programmer> findAndPickupExecutorCompletionService= new ExecutorCompletionService<>(findAndPickupExecutor );


    public Controller(){

    }


    public void start() throws InterruptedException, ExecutionException
    {

        Collection<Programmer> programmers = Programmer.createRandomProgrammers(10, 24335L);


        List<Callable<Programmer>> pickTheFavoriteTypeOfCoffeeCallableList = new ArrayList<>();

        programmers.forEach(programmer -> {
            PickTheFavoriteTypeOfCoffeeProcess pickTheFavoriteTypeOfCoffee = new PickTheFavoriteTypeOfCoffeeProcess(programmer);
            programmer.setExecutionStartTime(System.currentTimeMillis());
            pickTheFavoriteTypeOfCoffeeExecutor.submit(pickTheFavoriteTypeOfCoffee);
        });


        for (int i=0; i<programmers.size(); i++) {

            Programmer programmer = findAndPickupExecutorCompletionService.take().get();
            LOG.debug(String.format("Complete = %s \texecution time = %s ms.", programmer, programmer.getExecutionTime()));

        }


        pickTheFavoriteTypeOfCoffeeExecutor.shutdown();
        payExecutor.shutdown();
        findAndPickupExecutor.shutdown();


        prtintMeasure(programmers);



    }

    private void prtintMeasure(Collection<Programmer> programmers) {

        LongSummaryStatistics stats = programmers.stream().mapToLong((programmer) -> programmer.getExecutionTime()).summaryStatistics();
        LOG.info(String.format("Time to getting a coffee of average programmer: %s ms.",stats.getAverage()));
        LOG.info(String.format("Fastest time to getting a coffee: %s ms.",stats.getMin()));
        LOG.info(String.format("Slowest time to getting a coffee: %s ms.",stats.getMax()));

    }

}
