package ud4.objects;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

import static org.junit.jupiter.api.Assertions.*;

class RobotTest {

    @Nested
    class accelerarDecelerar {
        @Test
        @DisplayName("Acelerar una vez")
        public void acelerarUnaVez() {
            Robot robot = new Robot(0, 0, 0);
            robot.accelerate();
            double speedActual = robot.getSpeed();
            double speedExpected = 0.5;
            assertEquals(speedExpected, speedActual);
        }

        @Test
        public void acelerarDosVezes() {
            Robot robot = new Robot(0, 0, 0);
            robot.accelerate();
            robot.accelerate();
            double speedActual = robot.getSpeed();
            double speedExpected = 1.0;
            assertEquals(speedExpected, speedActual);
        }

        @Test
        public void acelerarTopSpeed() {
            Robot robot = new Robot(0, 0, 10);
            robot.accelerate();
            double speedActual = robot.getSpeed();
            double speedExpected = 10;
            assertEquals(speedExpected, speedActual);
        }

        @Test
        public void decelerateUnaVez() {
            Robot robot = new Robot(0, 0, 10);
            robot.decelerate();
            double speedActual = robot.getSpeed();
            double speedExpected = 9.5;
            assertEquals(speedExpected, speedActual);
        }

        @Test
        public void decelerateDosVezes() {
            Robot robot = new Robot(0, 0, 10);
            robot.decelerate();
            robot.decelerate();
            double speedActual = robot.getSpeed();
            double speedExpected = 9.0;
            assertEquals(speedExpected, speedActual);
        }

        @Test
        public void decelerateStopSpeed() {
            Robot robot = new Robot(0, 0, 0);
            robot.decelerate();
            double speedActual = robot.getSpeed();
            double speedExpected = 0;
            assertEquals(speedExpected, speedActual);
        }
    }

    @Nested
    class movimientoRobot{
        @Test
        public void movimientoUp(){
            Robot robot = new Robot(0, 0, 1);
            robot.up();
            double robotYActual = robot.getY();
            double robotYExpected = 1;
            assertEquals(robotYExpected, robotYActual);
        }

        @Test
        public void movimientoDown(){
            Robot robot = new Robot(0, 5, 1);
            robot.down();
            double robotYActual = robot.getY();
            double robotYExpected = 4;
            assertEquals(robotYExpected, robotYActual);
        }

        @Test
        public void movimientoLeft(){
            Robot robot = new Robot(5, 0, 1);
            robot.left();
            double robotXActual = robot.getX();
            double robotXExpected = 4;
            assertEquals(robotXExpected, robotXActual);
        }

        @Test
        public void movimientoRight(){
            Robot robot = new Robot(5, 0, 1);
            robot.right();
            double robotXActual = robot.getX();
            double robotXExpected = 6;
            assertEquals(robotXExpected, robotXActual);
        }
    }


}