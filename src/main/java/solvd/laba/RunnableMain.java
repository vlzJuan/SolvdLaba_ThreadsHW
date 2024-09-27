package solvd.laba;

import solvd.laba.threadinginstances.RunnableWaiter;

public class RunnableMain {


    public static void main(String[] args) {

        RunnableWaiter waiter = new RunnableWaiter();

        // Creating Thread and passing the Runnable task to it
        Thread thread1 = new Thread(waiter, "Thread 1");
        Thread thread2 = new Thread(waiter, "Thread 2");
        Thread thread3 = new Thread(waiter, "Thread 3");

        // Starting the threads
        thread1.start();
        thread2.start();
        thread3.start();

    }
}