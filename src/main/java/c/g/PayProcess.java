package c.g;

import c.g.model.Programmer;
import c.g.model.SoldCoffee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by ybarvenko on 22.10.2015.
 */
public class PayProcess implements Runnable {

    private static final Logger LOG = LoggerFactory.getLogger(PayProcess.class);

    Programmer programmer;

    public PayProcess(Programmer programmer) {
        this.programmer = programmer;
    }

    @Override
    public void run() {
        pay();
    }

    private void pay() {

        try {

            long duration = programmer.getPaymentType().getDuration();

            LOG.debug(String.format("Programmer %s paying with %s ... (duration: %s ms.) ",programmer.getId(),programmer.getPaymentType().name(), duration));
            Thread.sleep(duration);

            FindAndPickupProcess f = new FindAndPickupProcess(programmer);
            Controller.findAndPickupExecutorCompletionService.submit(f);
            SoldCoffee.soldCoffeeTotal.incrementAndGet();

        } catch (InterruptedException e) {
            LOG.error("Error in pay-Method: "+programmer,e);
        }

    }
}
