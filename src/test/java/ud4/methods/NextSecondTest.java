package ud4.methods;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NextSecondTest {

    @Test
    void segundoSiguienteTest(){

        String time = NextSecond.nextSecond(23, 59, 59);

        assertEquals("00:00:00", time);
    }

    @Test
    void shouldAddSecond(){
        int hour = 0;
        int minute = 0;
        int second = 0;
        String expected = "00:00:01";
        String time = NextSecond.nextSecond(hour, minute, second);

        assertEquals(expected, time);
    }

    @Test
    void shouldAddMinute(){
        int hour = 0;
        int minute = 0;
        int second = 59;
        String expected = "00:01:00";
        String time = NextSecond.nextSecond(hour, minute, second);

        assertEquals(expected, time);
    }

    @Test
    void shouldAddHour(){
        int hour = 0;
        int minute = 59;
        int second = 59;
        String expected = "01:00:00";
        String time = NextSecond.nextSecond(hour, minute, second);

        assertEquals(expected, time);
    }

    @Test
    void shouldResetDay(){
        int hour = 23;
        int minute = 59;
        int second = 59;
        String expected = "00:00:00";
        String time = NextSecond.nextSecond(hour, minute, second);
    }
}