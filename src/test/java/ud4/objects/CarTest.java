package ud4.objects;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarTest {

    final String PLATE = "5013CFJ";

    @Nested
    @DisplayName("Constructor tests")
    class ConstructorTests {
        @Test
        @DisplayName("Crear coche con matricula")
        void testPlate(){
            Car car = new Car(PLATE);
            assertAll(
                    () -> assertEquals(PLATE, car.getPlate()),
            () -> assertEquals(0, car.getKilometers())
            );
        }
        @Test
        @DisplayName("Crea un coche con matricula y kilometros")
        void testPlateAndKilometers(){
            Car car = new Car(PLATE, 100);
            assertAll(
                    () -> assertEquals(PLATE, car.getPlate()),
                    () -> assertEquals(100, car.getKilometers())
            );
        }

    }


    @Nested
    @DisplayName("Metodos acelerar")
    class AcelerarMetodos {
        @Test
        void accelerate() {
            Car coche = new Car(PLATE);
            coche.accelerate();

            int expected = 5;
            int actual = coche.getSpeed();

            assertEquals(expected, actual);
        }

        @Test
        void testAccelerate15() {
            Car coche = new Car(PLATE);
            coche.accelerate();
            coche.accelerate();
            coche.accelerate();

            int expected = 15;
            int actual = coche.getSpeed();
            assertEquals(expected, actual);
        }

        void accelerateManual() {
            Car coche = new Car(PLATE);
            coche.accelerate(20);

            int expected = 20;
            int actual = coche.getSpeed();

            assertEquals(expected, actual);
        }
    }


    @Nested
    @DisplayName("Drive metodos")
    class DriveMetodos {
        @Test
        void driveEnParado() {
            Car coche = new Car(PLATE, 100);
            coche.drive();

            double expected = 100;
            double actual = coche.getKilometers();
            assertEquals(expected, actual);
        }

        @Test
        void driveEnMovimiento() {
            Car coche = new Car(PLATE, 100);
            coche.drive();

            coche.accelerate();
            coche.accelerate();
            coche.accelerate();
            coche.accelerate();
            coche.accelerate();

            double expected = 100;
            double actual = coche.getKilometers();
            assertEquals(expected, actual);
        }
        @Test
        void testDrive() {
        }


        @Test
        void tesdDecelerate(){
            Car coche = new Car(PLATE, 100);
            coche.accelerate();
            coche.accelerate();
            coche.accelerate();

            coche.decelerate();
            int expected = 10;
            int actual = coche.getSpeed();
            assertEquals(expected, actual);
        }

    }


}