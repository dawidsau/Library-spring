package pl.sda.intermediate;

import org.junit.jupiter.api.Test;
import pl.sda.intermediate.training.MyRunnable;

public class ThreadsExampl {
    @Test
    void runnableBasics() {
        Runnable runnable=()-> System.out.println(Thread //praca do wykonania
                .currentThread()
                .getName()
                +" LambdaRunnable");
        Runnable runnable1Anonymous = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread
                        .currentThread()
                        .getName()
                        +" LambdaRunnable");
            }
        };

        Runnable myRunnable = new MyRunnable();

        runnable.run();
        runnable1Anonymous.run();
        myRunnable.run();

        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable1Anonymous);
        Thread thread3 = new Thread(myRunnable);

        thread1.start();
        thread2.start();
        thread3.start();



    }
}
