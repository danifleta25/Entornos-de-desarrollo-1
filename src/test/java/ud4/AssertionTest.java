package ud4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AssertionTest {

    @Test
    @DisplayName("assertEquals")
    void assertMultipleEquals(){
        assertAll(
                () -> assertEquals(10, 3 + 7), // Integer
                () -> assertEquals('b', 'a' + 1), // Char
                () -> assertEquals(1.5, 3.0 / 2), // Double
                () -> assertEquals(1.5, 3.1 / 2, 0.1), // Double with tolerance (delta)
                () -> assertEquals("HELLO", "hello".toUpperCase()) // String
        );
    }

    @Test
    @DisplayName("assertTrue & assertFalse")
    void assertMultipleTrueFalse(){
        assertAll(
                () -> assertTrue(-10 < 0),
                () -> assertFalse(-10 > 0)
        );
    }

    @Test
    @DisplayName("assertNull & assertNotNull")
    void assertMultipleNull(){
        String a = null;
        String b = "Joan";
        assertAll(
                () -> assertNull(a),
                () -> assertNotNull(b)
        );
    }

    @Test
    @DisplayName("assertSame & assertNotSame")
    void assertMultipleSame(){
        // https://stackoverflow.com/questions/15805578/will-two-strings-with-same-content-be-stored-in-the-same-memory-location
        String a = new String("Joan");
        String b = new String("Joan");
        assertAll(
                () -> assertSame(a, a),
                () -> assertNotSame(a, b)
        );
    }

    @Test
    @DisplayName("assertArrayEquals")
    void assertMultipleArray(){
        double[] intExpected = {1, 2, 3};
        double[] intActual = {1, 2, 3};

        double[] doubleExpected = {1.0, 2.0, 3.0};
        double[] doubleActual = {1.0, 2.0, 3.0};

        double[] doubleToleranceExpected = {1.0, 2.0, 3.0};
        double[] doubleToleranceActual = {1.09, 2.0, 2.91};

        String[] stringExpected = {"Hello", "World!"};
        String[] stringActual = {"Hello", "World!"};

        assertAll(
                () -> assertArrayEquals(intExpected, intActual),
                () -> assertArrayEquals(doubleExpected, doubleActual ),
                () -> assertArrayEquals(doubleToleranceExpected, doubleToleranceActual, 0.1),
                () -> assertArrayEquals(stringExpected, stringActual)
        );
    }
}