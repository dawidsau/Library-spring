package pl.sda.intermediate.training;

public class MyRunnable implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread
                .currentThread()
                .getName()
        +" MyRunnable");
    }
}
