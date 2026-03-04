package ud4.exam2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class LampTest {

    @Nested
    @DisplayName("Test de los métodos constructores")
    class constructorsTests{
        @Test
        @DisplayName("1er constructor debe iniciar apagada")
        void shouldBeOff(){
            Lamp lampara = new Lamp(1);
            boolean actual = lampara.isOn();
            boolean expected = false;
            assertEquals(expected, actual);
        }
        @Test
        @DisplayName("1er constructor concuerda consumption")
        void consumptionTest(){
            Lamp lampara = new Lamp(1);
            double actual = lampara.getConsumption();
            double expected = 1;
            assertEquals(expected, actual);
        }

        @Test
        @DisplayName("2o constructor coincide ON")
        void shouldBeOn(){
            Lamp lampara = new Lamp(1, true);
            boolean actual = lampara.isOn();
            boolean expected = true;
            assertEquals(expected, actual);
        }

        @Test
        @DisplayName("2o constructor coincide OFF")
        void secondShouldBeOff(){
            Lamp lampara = new Lamp(1, false);
            boolean actual = lampara.isOn();
            boolean expected = false;
            assertEquals(expected, actual);
        }

        @Test
        @DisplayName("2o constructor coincide Consumption")
        void secondShouldBeSameConsumption(){
            Lamp lampara = new Lamp(5, true);
            double actual = lampara.getConsumption();
            double expected = 5;
            assertEquals(expected, actual);
        }
    }


    @Nested
    @DisplayName("Test de el método turnOn()")
    class turnOnTests{
        @Test
        @DisplayName("Comprobar true sobre false")
        void turnOnTest(){
            Lamp lampara = new Lamp(5);
            lampara.turnOn();
            boolean actual = lampara.isOn();
            boolean expected = true;

            assertEquals(expected, actual);
        }
        @Test
        @DisplayName("Comprobar true sobre true")
        void turnOnDebeSeguirOn(){
            Lamp lampara = new Lamp(5, true);
            lampara.turnOn();
            boolean actual = lampara.isOn();
            boolean expected = true;

            assertEquals(expected, actual);
        }
    }


    @Nested
    @DisplayName("Test de el método turnOff()")
    class turnOffTests{
        @Test
        @DisplayName("Comprobar false sobre false")
        void turnOffDebeSeguirOff(){
            Lamp lampara = new Lamp(5);
            lampara.turnOff();
            boolean actual = lampara.isOn();
            boolean expected = false;

            assertEquals(expected, actual);
        }

        @Test
        @DisplayName("Comprobar false sobre true")
        void turnOffTest(){
            Lamp lampara = new Lamp(5, true);
            lampara.turnOff();
            boolean actual = lampara.isOn();
            boolean expected = false;

            assertEquals(expected, actual);
        }
    }


    @Nested
    @DisplayName("Test de el método toggle()")
    class toggleTests{
        @Test
        @DisplayName("False deberia pasar a True")
        public void toggleOnTest(){
            Lamp lampara = new Lamp(5, false);
            lampara.toggle();
            boolean actual = lampara.isOn();
            boolean expected = true;

            assertEquals(expected, actual);
        }
    }


    @Nested
    @DisplayName("Test de el método consume()")
    class consumeTests{
        @ParameterizedTest
        @DisplayName("Test parametrizado del método consume()")
        @CsvSource({
                "30, true, 15, 0.12500",
                "70, true, 5, 0.09722",
                "150, true, 20, 0.83333",
                "30, false, 15, 0",

        })
        void consumeTest(double consumption, boolean on, double seconds, double expected){
            Lamp lampara = new Lamp(consumption, on);
            double segundos = seconds;
            double consumoReal = lampara.consume(seconds);
            assertEquals(expected, consumoReal, 0.01);
        }
    }




}