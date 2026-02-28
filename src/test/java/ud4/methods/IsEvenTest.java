package ud4.methods;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IsEvenTest {

    @Test
    @DisplayName("isEven(1) -> false")
    void oneShouldReturnFalse() {

        boolean n = IsEven.isEven(1);

        assertFalse(n);

    }

    @Test
    @DisplayName("isEven(2) -> false")

    void twoShouldReturnTrue() {

        boolean n = IsEven.isEven(2);

        assertTrue(n);
    }

    @Test
    @DisplayName("isEven(30) -> false")
    void thirtyShouldReturnTrue() {
        boolean n = IsEven.isEven(30);
        assertTrue(n);
    }

}

