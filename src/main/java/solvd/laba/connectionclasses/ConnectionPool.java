package solvd.laba.connectionclasses;

import java.util.concurrent.LinkedBlockingQueue;

public class ConnectionPool {

    // Concurrent-safe queue
    private final LinkedBlockingQueue<Connection> availableConnections;
    private boolean hasBeenInitialized;


    public ConnectionPool(int size) {
        this.availableConnections = new LinkedBlockingQueue<>(size);
        this.hasBeenInitialized=false;
    }


    // Method to borrow a connection, blocking thread if none available. Supports lazy init
    public Connection getConnection() throws InterruptedException {
        synchronized (this) {
            // Lazy initialization: Check whether I've initialized the connections once before.
            if(!this.hasBeenInitialized){
                System.out.println("Lazy initialization called by thread "
                        + Thread.currentThread().getName());

                int numberOfConnections = this.availableConnections.remainingCapacity();
                for (int i = 0; i < numberOfConnections; i++) {
                    availableConnections.offer(new Connection(i + 1));  // Create and add connection to the queue
                    System.out.println("Initialized connection: " + (i + 1));
                }
                System.out.print("\n");
                this.hasBeenInitialized=true;
            }
        }

        return availableConnections.take();  // Blocks if no connections are available
    }

    // Method to return a connection to the ConnectionPool queue
    public void releaseConnection(Connection conn) {
        try {
            availableConnections.put(conn);  // Adds the connection back to the pool
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }





}
