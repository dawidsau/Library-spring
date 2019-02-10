package pl.sda.intermediate;

public class MyRunnable implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread
                .currentThread()
                .getName()
        +" MyRunnable");
    }
}
