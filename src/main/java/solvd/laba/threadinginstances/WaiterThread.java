package solvd.laba.threadinginstances;

public class WaiterThread extends Thread{

    @Override
    public void run(){

        String threadName = Thread.currentThread().getName();
        System.out.println("WaiterThread has begun, by: " + threadName);
        try {
            sleep(5000);
            System.out.println("WaiterThread " + threadName + " ending... So long...");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }



}
