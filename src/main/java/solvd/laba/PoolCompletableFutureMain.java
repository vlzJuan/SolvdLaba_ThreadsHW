package solvd.laba;

import solvd.laba.connectionclasses.Connection;
import solvd.laba.connectionclasses.ConnectionPool;

import java.util.LinkedList;
import java.util.concurrent.CompletableFuture;

public class PoolCompletableFutureMain {

    public static void main(String[] args) {
        //  Initialize the connection pool with a size of 5
        ConnectionPool connectionPool = new ConnectionPool(5);
        //  Initialize a linked list to store all CompletableFuture instances.
        LinkedList<CompletableFuture<Void>> futureList = new LinkedList<>();


        System.out.println("Use of the connection pool verified, using Future instances:\n");
        // Submit tasks using CompletableFuture
        for (int i = 1; i <= 7; i++) {
            final String threadName = "{THREAD-CF" + i + "}";
            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                try {
                    // Get a connection from the pool
                    Connection connection = connectionPool.getConnection();
                    System.out.println(threadName + " has acquired " + connection);
                    connection.use();
                    connectionPool.releaseConnection(connection);
                    System.out.println(threadName + " has released " + connection);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            // Add the CompletableFuture to the list
            futureList.add(future);
        }

        //  'Mesh' all the CompletableFuture's execution check in one completableFuture that verifies
        //  whether they all finished or not.
        //  Then, join main thread to it, to wait for all threads to finish
        CompletableFuture<Void> allOf = CompletableFuture.allOf(futureList.toArray(new CompletableFuture[0]));
        allOf.join();  // This waits for all futures to complete


        System.out.println("\nAfter all threads have stopped, the program has finished execution.");
    }



}
