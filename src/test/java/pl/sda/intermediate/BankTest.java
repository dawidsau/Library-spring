package pl.sda.intermediate;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class BankTest {

    @Test
    void synchronizedBankTest() {
        for (int i=0;i<100;i++){
            ClientAction clientAction = new ClientAction();
            clientAction.run();
        }
        System.out.println(Bank.cash);
        System.out.println(Bank.counter);
    }

    @Test
    void threadBankTest() {

        List<Thread> threadList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            ClientAction clientAction =new ClientAction();
            threadList.add(new Thread(clientAction));

        }

        for (Thread thread : threadList) {
            thread.start();
        }
        for (Thread thread : threadList) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Bank.cash);
        System.out.println(Bank.counter);

    }
}
