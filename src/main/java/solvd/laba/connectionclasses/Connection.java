package solvd.laba.connectionclasses;

import java.util.concurrent.Callable;

public class Connection implements Callable<String> {

    private final int id;

    public Connection(int id) {
        this.id = id;
    }

    public void use() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " is using connection " + id);
        Thread.sleep(2000);
        System.out.println(this + " has been released by thread "
                + Thread.currentThread().getName());
    }


    @Override
    public String call() throws InterruptedException {
        System.out.println("Accessed callable's method");
        use();  // The callable task calls the use method
        return this + " completed by " + Thread.currentThread().getName();
    }


    @Override
    public String toString(){
        return "[Connection " + this.id + "]";
    }

}
