package com.goodgamestudios;

import com.goodgamestudios.model.Programmer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;

/**
 * Created by ybarvenko on 22.10.2015.
 */
public class PickTheFavoriteTypeOfCoffeeProcess implements Callable<Programmer> {

    private static final Logger LOG = LoggerFactory.getLogger(PickTheFavoriteTypeOfCoffeeProcess.class);
    public static final long PICK_THE_FAVORITE_TYPE_OF_COFFEE = 500;

    private Programmer programmer;



    public PickTheFavoriteTypeOfCoffeeProcess(Programmer programmer) {
        this.programmer = programmer;
    }

    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     * @throws Exception if unable to compute a result
     */
    @Override
    public Programmer call() throws Exception {
        LOG.debug(String.format("Programmer %s picking the favorite type of coffee ... (duration: %s ms.) ",programmer.getId(),PICK_THE_FAVORITE_TYPE_OF_COFFEE));
        Thread.sleep(PICK_THE_FAVORITE_TYPE_OF_COFFEE);

        PayProcess p = new PayProcess(programmer);
        Controller.payExecutor.submit(p);


        return programmer;
    }
}
