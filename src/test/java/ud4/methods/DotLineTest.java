package ud4.methods;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DotLineTest {


    @Test
    void twoLineDots(){
        int number = 2;
        String expected = "..";

        String dotted = DotLine.dotLine(number);
        assertEquals(expected,dotted);
    }
    @Test
    void fiveLineDots(){
        int number = 5;
        String expected = ".....";

        String dotted = DotLine.dotLine(number);
        assertEquals(expected,dotted);
    }

    @Test
    void zeroLineDots(){
        int number = 0;
        String expected = "";
        String dotted = DotLine.dotLine(number);
    }

}