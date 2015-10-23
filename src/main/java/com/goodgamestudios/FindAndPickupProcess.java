package com.goodgamestudios;

import com.goodgamestudios.model.Programmer;
import com.goodgamestudios.model.SoldCoffee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * Created by ybarvenko on 22.10.2015.
 */
public class FindAndPickupProcess implements Callable<Programmer> {

    private static final Logger LOG = LoggerFactory.getLogger(FindAndPickupProcess.class);
    private static final long FIND_CUP = 250L;
    private static final long PUT_IT = 250L;
    private static final long PICK_THE_TYPE_OF_COFFEE = 250L;
    private static final long TAKE_A_CUP = 250L;



    private Programmer programmer;

    public FindAndPickupProcess(Programmer programmer) {
        this.programmer = programmer;
    }

    @Override
    public Programmer call() throws InterruptedException {
        return findAndPickup();
    }

    private Programmer findAndPickup() throws InterruptedException {

        long coffeeTypeDuration = programmer.getCoffeeType().getDuration();
        long totalDuration = FIND_CUP + PUT_IT + PICK_THE_TYPE_OF_COFFEE + coffeeTypeDuration + TAKE_A_CUP;

        LOG.debug(String.format("Programmer %s picking up the coffee of type %s ... (duration: %s ms.) ",programmer.getId(),programmer.getCoffeeType().name(), totalDuration));

        Thread.sleep(totalDuration);
        programmer.setExecutionEndTime(System.currentTimeMillis());

        countDispensedCoffeeByMachine(Thread.currentThread(), programmer.getCoffeeType().name());


        return programmer;


    }


    private void countDispensedCoffeeByMachine(Thread thread, String coffeeType){
        Map<String, Integer> stringIntegerMap = SoldCoffee.dispensedCoffeeByMachineMap.get(thread);
        if(stringIntegerMap==null)
        {
            stringIntegerMap = new HashMap<>();
            SoldCoffee.dispensedCoffeeByMachineMap.put(thread,stringIntegerMap);
        }

        Integer count = stringIntegerMap.get(coffeeType);
        if(count==null){
            stringIntegerMap.put(coffeeType,1);
        }else{
            stringIntegerMap.put(coffeeType,count+1);
        }

    }
}