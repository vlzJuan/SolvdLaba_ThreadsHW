package solvd.laba;

import solvd.laba.connectionclasses.ConnectionPool;
import solvd.laba.threadinginstances.ConnectionUserThread;

import java.util.LinkedList;


public class PoolMain {

    public static void main(String[] args) throws InterruptedException {
        //  Initialize the connection pool with a size of 5
        ConnectionPool connectionPool = new ConnectionPool(5);
        //  Initialize a linked list to store all running declared threads.
        LinkedList<ConnectionUserThread> initializedThreads = new LinkedList<>();

        //  Load the connection pool using single threads
        System.out.println("Process begin: Use the custom threads with the connectionPool\n");
        for (int i = 1; i <= 7; i++) {
            ConnectionUserThread thread = new ConnectionUserThread(connectionPool,
                    "{THREAD-" + i + "}");
            initializedThreads.add(thread);
            thread.start();
        }

        //  Stop main thread until all threads have finished
        for(ConnectionUserThread th:initializedThreads){
            th.join();
        }

        System.out.println("\nAfter all threads have stopped, program has finised execution.");


    }
}
