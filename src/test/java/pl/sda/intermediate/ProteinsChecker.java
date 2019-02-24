package pl.sda.intermediate;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ProteinsChecker {

    private String data = "BDDFPQPPRRAGGHPOPDKJKPEQAAER\n" +
            "BDDFPQPFRRAGGHPOPDKJKPEQAAER\n" +
            "BDDFPQPFRRAGCHPOPDKJKPEQAAER\n" +
            "BDDFPQPFRRAGGHPOPDKJKPEQAAER\n" +
            "BDDFPOPFRRAGCHPOPDKJKPEQAAER\n" +
            "BDDFPOPFRRAGCHPOPDKJKPEQAAER\n" +
            "AABBCC\n" +
            "ACBBCA\n" +
            "BCBACA\n" +
            "ACBBCA\n" +
            "AABBCC\n" +
            "BCBACA\n" +
            "BCBACA\n" +
            "AABBCCC\n" +
            "AABBCC\n" +
            "AABBCC\n" +
            "ABBBCC\n" +
            "AABCCC\n" +
            "ABCAC\n" +
            "CACBA\n" +
            "AAAAAAAAAAAAAAAAAAAAB\n" +
            "AAAAAAAAAAAAAAAAAAAAA\n" +
            "QOOOOOOOOOOOOOOOOOOOO\n" +
            "OOOOOOOOOOOOOOOOOOOOQ";


    @Test
    void parseAndCheck() {
        File file = new File("C:\\projects\\lancuchy_bialkowe_dane.txt");
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()){
                String firstLine = scanner.nextLine();
                String secondLine = scanner.nextLine();
//                char[] chars = firstLine.toCharArray();
//                Arrays.sort(chars);
//                char[] chars2 = secondLine.toCharArray();
//                Arrays.sort(chars2);
//                System.out.println(Arrays.equals(chars, chars2)); 1

                List<Integer> list1 = firstLine.chars().sorted().mapToObj(e -> Integer.valueOf(e)).collect(Collectors.toList());
                List<Integer> list2 = secondLine.chars().sorted().mapToObj(e -> Integer.valueOf(e)).collect(Collectors.toList());
                System.out.println(list1.equals(list2));
            }



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Test
    void name() {
        List<char[]> collect = Arrays.stream(
                data.split("\n"))
                .parallel()
                .map(String::toCharArray)
                .peek(Arrays::parallelSort)
                .collect(Collectors.toList());
        IntStream.iterate(1, x -> x + 2)
                .parallel()
                .limit(collect.size() / 2)
                .forEachOrdered(i ->
                        System.out.println(
                                Arrays.equals(
                                        collect.get(i), collect.get(i - 1))));
    }
}
