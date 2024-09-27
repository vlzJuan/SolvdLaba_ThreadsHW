package solvd.laba.threadinginstances;

import static java.lang.Thread.sleep;

public class RunnableWaiter implements Runnable {

    @Override
    public void run(){
        String threadName = Thread.currentThread().getName();
        System.out.println("RunnableWaiter has begun, by: " + threadName);
        try {
            sleep(5000);
            System.out.println("RunnableWaiter " + threadName + " ending... So long...");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

}
