package solvd.laba;


import solvd.laba.threadinginstances.WaiterThread;

public class WaiterThreadMain {

    public static void main(String[] args) {

        WaiterThread thread1 = new WaiterThread();
        WaiterThread thread2 = new WaiterThread();
        WaiterThread thread3 = new WaiterThread();
        thread1.setName("Thread 1");
        thread2.setName("Thread 2");
        thread3.setName("Thread 3");

        thread1.start();
        thread2.start();
        thread3.start();

    }

}
