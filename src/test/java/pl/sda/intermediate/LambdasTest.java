package pl.sda.intermediate;

import org.apache.logging.log4j.util.PropertySource;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;

public class LambdasTest {

    private List<String> animals = Arrays.asList("cat", "dog", "  ", "mouse", null, "rat ", "pig", "rabbit", "  hamster", "parrot");

    @Test
    void lambdas() {
        Comparator<String> secondLetterSort = (a, b) -> String.valueOf(a.charAt(1))
                .compareTo(String.valueOf(b.charAt(1)));
        Comparator<String> lengthSort = (a, b) -> b.length() - a.length();

        animals.stream()
                .filter(e -> StringUtils.isNotBlank(e))
                .map(e -> e.trim())
                .sorted(secondLetterSort.thenComparing(lengthSort.reversed()))
                .forEach(e -> System.out.println(e));

    }

    @FunctionalInterface
    public interface MathOperation<T extends Number> {
        T calculate(T numberFirst, T numberSecond);

        default String ok() {
            return "OK!";
        }
    }

    MathOperation<Double> adder = (a, b) -> a + b;
    MathOperation<Double> adderAnonymous = new MathOperation<Double>() {
        @Override
        public Double calculate(Double numberFirst, Double numberSecond) {
            return numberFirst + numberSecond;
        }
    };
    MathOperation<Double> multiply = (a, b) -> a * b;
    MathOperation<Double> divide = (a, b) -> a / b;
    MathOperation<Double> substract = (a, b) -> a - b;

    BiFunction<Integer, Long, Double> a = (a, b) -> Double.valueOf(a.longValue() + b);

    public static Number doTheMath(Double a, Double b, MathOperation operation) {
        return operation.calculate(a, b);
    }

    @Test
    void MathOperationTest () {

        Number math1 = doTheMath(34.0, 54.2, adder);
        Number math2 = doTheMath(34.0, 54.2, multiply);


    }



    @Test
    void trickyArrayList() {
        List<Integer> a = Arrays.asList(new Integer[]{1, 2, 3});
        List<int[]> b = Arrays.asList(new int[]{1, 2, 3});
        System.out.println(a.size());
        System.out.println(b.size());
    }

}
