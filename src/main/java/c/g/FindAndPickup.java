package c.g;

import c.g.model.Programmer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;

/**
 * Created by ybarvenko on 22.10.2015.
 */
public class FindAndPickup implements Callable<Programmer> {

    private static final Logger LOG = LoggerFactory.getLogger(FindAndPickup.class);
    private static final long FIND_CUP = 250L;
    private static final long PUT_IT = 250L;
    private static final long PICK_THE_TYPE_OF_COFFEE = 250L;
    private static final long TAKE_A_CUP = 250L;

    private Programmer programmer;


    @Override
    public Programmer call() throws InterruptedException {
        return findAndPickup();
    }

    private Programmer findAndPickup() throws InterruptedException {

        long coffeeTypeDuration = programmer.getCoffeeType().getDuration();


        Thread.sleep(FIND_CUP + PUT_IT + PICK_THE_TYPE_OF_COFFEE + coffeeTypeDuration + TAKE_A_CUP);
        return programmer;


    }
}
