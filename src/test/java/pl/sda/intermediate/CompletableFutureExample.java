package pl.sda.intermediate;

import lombok.AllArgsConstructor;
import lombok.ToString;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CompletableFutureExample {

    Function<BigDecimal, String> bigDecimalToString = s -> s.toPlainString();
    Function<Long, String> longToString = s -> s.toString();
    Function<Long, String> longToStringExtra = s -> s.toString() + "!";
    Function<String, String> stringToString = s -> s;

    @Test
    public void oneByOne() {
        transform(downloadDescription(), stringToString);
        transform(downloadPrice(), bigDecimalToString);
        transform(downloadPhotos(), stringToString);
        transform(downloadAdditionalData(), longToString);
    }

    @Test
    void threads() {

        Thread t1 = new Thread(() -> transform(downloadDescription(), stringToString));
        Thread t2 = new Thread(() -> transform(downloadPrice(), bigDecimalToString));
        Thread t3 = new Thread(() -> transform(downloadPhotos(), stringToString));
        Thread t4 = new Thread(() -> transform(downloadAdditionalData(), longToString));
        List<Thread> threadList = Stream.of(t1, t2, t3, t4).collect(Collectors.toList());

        for (Thread thread : threadList) {
            thread.start(); //używamy start, aby uruchomic operacje w nowych watkach a nie w main (tak zadziala run))
        }

        for (Thread thread : threadList) {
            try {
                thread.join(); //czekamy na wykonanie każdego watku
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    void completableFutures() {

        CompletableFuture<String> descr1CF = CompletableFuture
                .supplyAsync(() -> downloadDescription());

        CompletableFuture<String> descr2CF = CompletableFuture
                                .supplyAsync(() -> downloadDescription2());

        CompletableFuture<String> descrCF =
                descr1CF.applyToEitherAsync(descr2CF,s->s) //1
                .thenApplyAsync(s -> transform(s, stringToString)); //5
        CompletableFuture<String> photosCF = CompletableFuture
                .supplyAsync(() -> downloadPhotos()) //2
                .thenApplyAsync(s -> transform(s, stringToString)); //6
        CompletableFuture<String> priceCF = CompletableFuture
                .supplyAsync(() -> downloadPrice()) //3
                .thenApplyAsync(s -> transform(s, bigDecimalToString)); //7
        CompletableFuture<String> dataCF = CompletableFuture
                .supplyAsync(() -> downloadAdditionalData()) //4
                .thenApplyAsync(s -> transform(s, longToString)); //8
        List<CompletableFuture<String>> cFS =
                Stream.of(descrCF,photosCF,priceCF,dataCF).collect(Collectors.toList());
        for (CompletableFuture<String> cF : cFS) {
            cF.join();//czekamy na wykonanie każdego z zadań
        }

    }

    private <T> String transform(T value, Function<T, String> transformer) {
        simulateDelay(2000);
        return transformer.apply(value);
    }

    private String downloadDescription() {
        simulateDelay(2000);
        return "opis";
    }

    private String downloadDescription2() {
        simulateDelay(2100);
        return "opis2";
    }

    private BigDecimal downloadPrice() {
        simulateDelay(2500);
        return BigDecimal.valueOf(300.2);
    }

    private String downloadPhotos() {
        simulateDelay(3000);
        return "zdjęcia";
    }

    private Long downloadAdditionalData() {
        simulateDelay(3300);
        return 12L;
    }

    private void simulateDelay(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @ToString
    @AllArgsConstructor
    private class ProductForTest {
        private String description;
        private String price;
        private String photos;
        private String additionalData;

    }
}
