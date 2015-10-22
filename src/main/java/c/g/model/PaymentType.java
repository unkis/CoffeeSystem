package c.g.model;

import java.util.Random;

/**
 * Created by ybarvenko on 22.10.2015.
 */
public enum PaymentType {

    CREDIT(250), CASH(500);

    private long duration;

    /**
     *
     * @param duration in ms
     */
    private PaymentType(long duration){
        this.duration = duration;
    }

    static PaymentType generateRandomPaymentType(Random r) {
        return PaymentType.values()[r.nextInt(2)];
    }

    public long getDuration() {
        return duration;
    }

}
