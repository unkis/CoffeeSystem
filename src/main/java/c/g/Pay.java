package c.g;

import c.g.model.Programmer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by ybarvenko on 22.10.2015.
 */
public class Pay implements Runnable {

    private static final Logger LOG = LoggerFactory.getLogger(Pay.class);

    private static final ExecutorService findAndPickupExecutor = Executors.newFixedThreadPool(2);

    Programmer programmer;

    public Pay(Programmer programmer) {
        this.programmer = programmer;
    }

    @Override
    public void run() {
        pay();
    }

    private void pay() {

        try {
            Thread.sleep(programmer.getPaymentType().getDuration());

            FindAndPickup f = new FindAndPickup(programmer);
            findAndPickupExecutor.submit(f);

        } catch (InterruptedException e) {
            LOG.error("Error in pay-Method: "+programmer,e);
        }

    }
}
