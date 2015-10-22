package c.g;

import c.g.model.Programmer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by ybarvenko on 22.10.2015.
 */
public class PickTheFavoriteTypeOfCoffeeCallable implements Callable<Programmer> {

    private static final Logger LOG = LoggerFactory.getLogger(PickTheFavoriteTypeOfCoffeeCallable.class);
    public static final long PICK_THE_FAVORITE_TYPE_OF_COFFEE = 500;

    private static final ExecutorService payExecutor = Executors.newFixedThreadPool(5);

    private Programmer programmer;

    public PickTheFavoriteTypeOfCoffeeCallable(Programmer programmer) {
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
        Thread.sleep(PICK_THE_FAVORITE_TYPE_OF_COFFEE);

        Pay p = new Pay(programmer);
        payExecutor.submit(p);

        return programmer;
    }
}
