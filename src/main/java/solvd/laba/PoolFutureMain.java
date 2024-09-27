package solvd.laba;

import solvd.laba.connectionclasses.Connection;
import solvd.laba.connectionclasses.ConnectionPool;

import java.util.LinkedList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class PoolFutureMain {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        //  Initialize the connection pool with a size of 5
        ConnectionPool connectionPool = new ConnectionPool(5);
        //  Initialize a linked list to store all Future instances.
        LinkedList<Future<String>> futureList = new LinkedList<>();

        //  Receive Future objects from an executor, that uses seven threads.
        ExecutorService executor = Executors.newFixedThreadPool(7);
        System.out.println("Use of the connection pool verified, using Future instances:\n");
        for (int i = 1; i <= 7; i++) {
            // Submit the task using Connection, which implements Callable
            Future<String> future = executor.submit(() -> {
                Connection connection = connectionPool.getConnection();
                String result = connection.call();  // Execute the callable
                connectionPool.releaseConnection(connection);  // Return connection to the pool
                return result;
            });
            futureList.add(future);
        }

        //  When the tasks complete, just then will the future.get() work.
        //  In this case, I will print the tasks in the order they were added.
        //  This line's syntax will seem sequential, but the tasks didn't necessarily finish
        //  in a sequential order (check this with the previous prints).
        for (Future<String> future : futureList) {
            System.out.println(future.get());
        }

        // Shutdown the executor after all tasks are done
        executor.shutdown();

        System.out.println("\nAll future objects have been called, finished execution");
    }


}
