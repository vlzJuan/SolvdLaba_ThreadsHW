package solvd.laba.threadinginstances;

import solvd.laba.connectionclasses.Connection;
import solvd.laba.connectionclasses.ConnectionPool;

public class ConnectionUserThread extends Thread{

    private final ConnectionPool connectionPool;

    public ConnectionUserThread(ConnectionPool connectionPool, String name) {
        super(name); // Set the thread name
        this.connectionPool = connectionPool;
    }

    @Override
    public void run() {
        try {
            // Attempt to get a connection from the pool
            Connection connection = connectionPool.getConnection();
            connection.use();
            // Release the connection back to the pool
            connectionPool.releaseConnection(connection);
            System.out.println(getName() + " released: " + connection.toString() + ", so long...");
        } catch (InterruptedException e) {
            System.out.println(getName() + " was interrupted.");
        }
    }



}
