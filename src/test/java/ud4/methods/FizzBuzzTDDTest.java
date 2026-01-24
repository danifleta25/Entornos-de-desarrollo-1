package ud4.methods;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


class FizzBuzzTDDTest {

    @Test
    @DisplayName("Fizzbuzz class should exist")
    void FizzBuzzClassShouldExist () {
    FizzBuzzTDD f1 = new FizzBuzzTDD();
    }
    /*
    @Test
    @DisplayName("transform() should exist")
    void transformShouldExist() {
        FizzBuzzTDD f1 = new FizzBuzzTDD();
        f1.transform();
    }
    */

    @Test
    @DisplayName("transform() should get Int")
    void transformShouldGetInt() {
        FizzBuzzTDD f1 = new FizzBuzzTDD();
        f1.transform(1);
    }


    @Test
    @DisplayName("return 1")
    void oneShouldReturnOne() {
        FizzBuzzTDD f1 = new FizzBuzzTDD();
        String actual = f1.transform(1);
        assertEquals("1", actual);
    }

    @Test
    @DisplayName("return 2")
    void twoShouldReturnTwo() {
        FizzBuzzTDD f1 = new FizzBuzzTDD();
        String actual = f1.transform(2);
        assertEquals("2", actual);
    }

    @Test
    @DisplayName("3 return Fizz")
    void threeShouldReturnFizz() {
        FizzBuzzTDD f1 = new FizzBuzzTDD();
        String actual = f1.transform(3);
        assertEquals("Fizz", actual);

    }

    @Test
    @DisplayName("5 Return Buzz")
    void fiveShouldReturnBuzz() {
        FizzBuzzTDD f1 = new FizzBuzzTDD();
        String actual = f1.transform(5);
        assertEquals("Buzz", actual);
    }

    @Test
    @DisplayName("6 return Fizz")
    void sixShouldReturnFizz() {
        FizzBuzzTDD f1 = new FizzBuzzTDD();
        String actual = f1.transform(6);
        assertEquals("Fizz", actual);
    }

    @Test
    @DisplayName("10 Return Buzz")
    void tenShouldReturnBuzz() {
        FizzBuzzTDD f1 = new FizzBuzzTDD();
        String actual = f1.transform(10);
        assertEquals("Buzz", actual);
    }

    @Test
    @DisplayName("15 Return FizzBuzz")
    void fiveTeenShouldReturnFizzBuzz() {
        FizzBuzzTDD f1 = new FizzBuzzTDD();
        String actual = f1.transform(15);
        assertEquals("FizzBuzz", actual);
    }

    @Test
    @DisplayName("30 Return FizzBuzz")
    void thirtyShouldReturnFizzBuzz() {
        FizzBuzzTDD f1 = new FizzBuzzTDD();
        String actual = f1.transform(30);
        assertEquals("FizzBuzz", actual);
    }
/*
    @ParameterizedTest(name = "FizzBuzz::transform({0}) should return {1}")
    @DisplayName("FizzBuzz::transform()")
    @CsvSource({
            "1, 1",
            "2, 2",
            "3, Fizz",
            "5, Buzz",
            "6, Fizz",
            "10, Buzz",
            "15, FizzBuzz",
            "30, FizzBuzz"
    })
    void fizzBuzzTransform(int input, String expected){
        FizzBuzzTDD fizzBuzz = new FizzBuzzTDD();
        String result = fizzBuzz.transform(input);
        assertEquals(expected, result);
    }
*/


    @ParameterizedTest(name = "FizzBuzz ({0} should return {1}")
    @DisplayName("FIzzBuzz")
    @CsvSource({
            "1, 1",
            "2, 2",
            "3, Fizz",
            "5, Buzz",
            "6, Fizz",
            "10, Buzz",
            "15, FizzBuzz",
            "30, FizzBuzz"
    })
    void FizzTransform(int number, String expected) {
        FizzBuzzTDD f1 = new FizzBuzzTDD();
        String actual = f1.transform(number);
        assertEquals(expected, actual);
    }
}