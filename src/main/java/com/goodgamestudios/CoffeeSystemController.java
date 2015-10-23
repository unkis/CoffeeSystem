package com.goodgamestudios;

import com.goodgamestudios.model.CoffeeSystemResult;
import com.goodgamestudios.model.Programmer;
import com.goodgamestudios.process.ChoseTypeOfCoffeeProcess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.*;

/**
 * Created by ybarvenko on 22.10.2015.
 */
public class CoffeeSystemController {

    private static final Logger LOG = LoggerFactory.getLogger(CoffeeSystemController.class);

    //init executors
    public static ExecutorService pickTheFavoriteTypeOfCoffeeExecutor;
    public static ExecutorService paymentProcessExecutor;
    private static  ExecutorService findAndPickupExecutor;
    public static CompletionService<Programmer> findAndPickupExecutorCompletionService;

    public CoffeeSystemController()
    {
        pickTheFavoriteTypeOfCoffeeExecutor = Executors.newFixedThreadPool(10);
        paymentProcessExecutor = Executors.newFixedThreadPool(5);
        findAndPickupExecutor = Executors.newFixedThreadPool(2);
        findAndPickupExecutorCompletionService = new ExecutorCompletionService<>(findAndPickupExecutor);
    }

    public void start(int numberOfProgrammers) throws InterruptedException, ExecutionException
    {

        LOG.info(String.format("The coffee system with %s programmers is running... . Please wait.... .\nFor mor Information you can change the logging-level to DEBUG in logback.xml file.\n",numberOfProgrammers));


        // Generate programmer
        Collection<Programmer> programmers = Programmer.createRandomProgrammers(numberOfProgrammers, 24335L);


        //Start the process
        programmers.forEach(programmer -> {
            ChoseTypeOfCoffeeProcess pickTheFavoriteTypeOfCoffee = new ChoseTypeOfCoffeeProcess(programmer);
            programmer.setExecutionStartTime(System.currentTimeMillis());
            pickTheFavoriteTypeOfCoffeeExecutor.submit(pickTheFavoriteTypeOfCoffee);
        });


        //wait for finish the process
        for (int i=0; i<programmers.size(); i++) {
            Programmer programmer = findAndPickupExecutorCompletionService.take().get();
            LOG.debug(String.format("Complete = %s \texecution time = %s ms.", programmer, programmer.getExecutionTime()));
        }

        //Shutdown executors
        pickTheFavoriteTypeOfCoffeeExecutor.shutdown();
        paymentProcessExecutor.shutdown();
        findAndPickupExecutor.shutdown();


        //print results
        printMeasure(programmers);



    }

    private void printMeasure(Collection<Programmer> programmers) {

        LongSummaryStatistics stats = programmers.stream().mapToLong((programmer) -> programmer.getExecutionTime()).summaryStatistics();
        LOG.info(String.format("Time to getting a coffee of average programmer: %s ms.",stats.getAverage()));
        LOG.info(String.format("Fastest time to getting a coffee: %s ms.",stats.getMin()));
        LOG.info(String.format("Slowest time to getting a coffee: %s ms.",stats.getMax()));
        LOG.info(CoffeeSystemResult.getResultInfo());


    }

}
