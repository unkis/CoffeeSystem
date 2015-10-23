package com.goodgamestudios;

import com.goodgamestudios.model.CoffeeSystemProcess;
import com.goodgamestudios.model.Programmer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by ybarvenko on 22.10.2015.
 */
public class PayProcess extends CoffeeSystemProcess
{

    private static final Logger LOG = LoggerFactory.getLogger(PayProcess.class);

    public PayProcess(final Programmer programmer)
    {
        super(programmer);
    }


    @Override
    public Programmer call() throws Exception
    {
        pay();

        return programmer;

    }


    protected void pay() {

        try {

            long duration = programmer.getPaymentType().getDuration();


            LOG.debug(String.format("Programmer %s paying with %s ... (duration: %s ms.) ",programmer.getId(),programmer.getPaymentType().getName(), duration));
            Thread.sleep(duration);

            FindAndPickupProcess f = new FindAndPickupProcess(programmer);
            Controller.findAndPickupExecutorCompletionService.submit(f);
            programmer.getPaymentType().paymentFinish();

        } catch (InterruptedException e) {
            LOG.error("Error in pay process: "+programmer,e);
        }

    }



}
