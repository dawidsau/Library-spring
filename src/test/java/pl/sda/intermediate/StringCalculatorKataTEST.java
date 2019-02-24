package pl.sda.intermediate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StringCalculatorKataTEST {

    @Test
    void shouldReturnZeroWhenTextIsEmpty() {
        String text = "";

        int result = StringCalculatorKata.adding(text);

        Assertions.assertEquals(0,result);
    }

    @Test
    void shouldReturnNumberWhenTextIsOneNumber(){
        String text = "1";

        int result = StringCalculatorKata.adding(text);

        Assertions.assertEquals(1,result);
    }

    @Test
    void shouldReturnSumWhenTextHasTwoNumbersWithDelimiter(){
        String text = " 1 , 2";

        int result = StringCalculatorKata.adding(text);

        Assertions.assertEquals(3,result);
    }

    @Test
    void shouldReturnSumWhenTextHasManyNumbersWithDelimiter(){
        String text = " 1 , 2   , 8";

        int result = StringCalculatorKata.adding(text);

        Assertions.assertEquals(11,result);
    }

    @Test
    void shouldReturnSumWhenTextHasManyNumbersWithDelimiterOrNewLine(){
        String text = " 1 , 2  \n  8";

        int result = StringCalculatorKata.adding(text);

        Assertions.assertEquals(11,result);
    }

    @Test
    void shouldReturnSumWhenTextHasManyNumbersWithCustomDelimiter(){
        String text = "//;\n 1 ; 2  ;  8";

        int result = StringCalculatorKata.adding(text);

        Assertions.assertEquals(11,result);
    }

    @Test
    void shouldReturnSumWhenTextHasManyNumbersWithLongerDelimiter(){
        String text = "//***\n 1 *** 2  ***  8";

        int result = StringCalculatorKata.adding(text);

        Assertions.assertEquals(11,result);
    }
}
