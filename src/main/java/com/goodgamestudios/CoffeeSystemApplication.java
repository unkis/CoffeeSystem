package com.goodgamestudios;

import com.goodgamestudios.model.SoldCoffee;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.ExecutionException;

@SpringBootApplication
public class CoffeeSystemApplication {

    public static void main(String[] args) throws InterruptedException, ExecutionException
    {
//        SpringApplication.run(CoffeeSystemApplication.class, args);




        Controller c = new Controller();
        c.start();

        System.out.println(SoldCoffee.getSoldInfo());







    }
}
