package c.g.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by ybarvenko on 22.10.2015.
 */
public class Programmer {


    private long startTrime;

    private long id;

    private CoffeeType coffeeType;

    private PaymentType paymentType;


    private Programmer(long id, CoffeeType coffeeType, PaymentType paymentType) {
        this.id = id;
        this.coffeeType = coffeeType;
        this.paymentType = paymentType;
    }

    public static Programmer createProgrammer(long id, CoffeeType coffeeType, PaymentType paymentType) {
        return new Programmer(id, coffeeType, paymentType);
    }

    public static List<Programmer> createRandomProgrammers(long numberOfProgrammers, long seed){
        List<Programmer> programmers = new ArrayList<>();
        Random r = new Random(seed);
        for (int i = 0; i <numberOfProgrammers ; i++) {
            CoffeeType coffeeType = CoffeeType.generateRandomCoffeeType(r);
            PaymentType paymentType = PaymentType.generateRandomPaymentType(r);
            long id = generateRandomProgrammerId(r);

            Programmer p = createProgrammer(id, coffeeType, paymentType);
            programmers.add(p);
        }

        return programmers;
    }

    public static long generateRandomProgrammerId(Random r){
        long lowerLimit = 0L;
        long upperLimit = 100000000L;
        long id = lowerLimit+((long)(r.nextDouble()*(upperLimit-lowerLimit)));

        return id;
    }


    public long getId() {
        return id;
    }

    public CoffeeType getCoffeeType() {
        return coffeeType;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public long getStartTrime() {
        return startTrime;
    }

    public void setStartTrime(long startTrime) {
        this.startTrime = startTrime;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Programmer{");
        sb.append("startTrime=").append(startTrime);
        sb.append(", id=").append(id);
        sb.append(", coffeeType=").append(coffeeType);
        sb.append(", paymentType=").append(paymentType);
        sb.append('}');
        return sb.toString();
    }
}
