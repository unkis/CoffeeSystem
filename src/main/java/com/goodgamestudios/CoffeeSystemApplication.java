package com.goodgamestudios;

import java.util.concurrent.ExecutionException;

public class CoffeeSystemApplication {

    public static void main(String[] args) throws InterruptedException, ExecutionException
    {
        {
            CoffeeSystemController coffeeSystemController = new CoffeeSystemController();
            coffeeSystemController.start(10);
        }
        {
            CoffeeSystemController coffeeSystemController = new CoffeeSystemController();
            coffeeSystemController.start(20);
        }

    }
}
