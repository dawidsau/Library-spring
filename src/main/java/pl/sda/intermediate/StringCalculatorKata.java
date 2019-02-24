package pl.sda.intermediate;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculatorKata {

    public static int adding(String text) {
        if (text.isEmpty()) {
            return 0;
        }
        String[] numbers;
        if (text.startsWith("//")) {
            Pattern pattern = Pattern.compile("//(.*)\\n(.*)");
            Matcher matcher = pattern.matcher(text);
            matcher.matches();

            String delimiter = matcher.group(1);
            numbers = matcher.group(2).split(Pattern.quote(delimiter)); //Pattern.quote -> uzyty w celu wyescapowania znakow specjalnych z regexa
        } else {
            numbers = text.split("[,\\n]");
        }
        return Arrays.stream(numbers)
                .map(s -> Integer.valueOf(s.trim()))
                .reduce((a, b) -> a + b)
                .orElseGet(() -> getZero());
    }

    private static int getZero() {
        return 0; //zalozmy ze to jest skomplikowane
    }

}
