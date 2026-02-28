package ud4.methods;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class IsLeapYearTest {

    @Test
    void shouldBeLeap(){
        int year = 2020;
        boolean bisiesto = IsLeapYear.isLeapYear(year);

        assertTrue(bisiesto);
    }

    @Test
    void shouldNotBeLeap(){

        boolean noBisiesto = IsLeapYear.isLeapYear(1900);

        assertFalse(noBisiesto);
    }


    @Test
    void shouldBeLeapYear100(){
        boolean bisiesto = IsLeapYear.isLeapYear(2000);
        assertTrue(bisiesto);
    }


    @ParameterizedTest(name = "{1} should be {0}")
    @DisplayName("IsLeapParameterized")
    @CsvSource({
        "true, 2000",
        "false, 1900",
        "true, 2020"
    })
    void isLeapYear(boolean expected, int year){
        assertEquals(expected, IsLeapYear.isLeapYear(year));
    }

}