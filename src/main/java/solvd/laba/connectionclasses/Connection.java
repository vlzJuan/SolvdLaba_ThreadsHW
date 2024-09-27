package solvd.laba.connectionclasses;

public class Connection {

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
    public String toString(){
        return "[Connection " + this.id + "]";
    }

}
