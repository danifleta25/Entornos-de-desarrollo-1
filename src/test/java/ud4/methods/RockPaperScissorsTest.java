package ud4.methods;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RockPaperScissorsTest {
    @Nested
    class Empats {
        @Test
        @DisplayName("Tisores VS Tisores")
        void tisoresVsTisores_shouldReturn0(){
            // Arrange
            String j1 = "tisores";
            String j2 = "tisores";
            int expected = 0;

            // Act
            int actual = RockPaperScissors.guanya(j1, j2);

            // Assert
            assertEquals(expected, actual);
        }

        @Test
        @DisplayName("Pedra VS Pedra")
        void pedraVsPedra_shouldReturn0(){
            // Arrange
            String j1 = "pedra";
            String j2 = "pedra";
            int expected = 0;

            // Act
            int actual = RockPaperScissors.guanya(j1, j2);

            // Assert
            assertEquals(expected, actual);
        }

        @Test
        @DisplayName("Paper VS Paper")
        void paperVsPaper_shouldReturn0(){
            // Arrange
            String j1 = "paper";
            String j2 = "paper";
            int expected = 0;

            // Act
            int actual = RockPaperScissors.guanya(j1, j2);

            // Assert
            assertEquals(expected, actual);
        }
    }

    @Nested
    class Jugador1 {
        @Test
        @DisplayName("Pedra VS Tisores")
        void pedraVsTisores_shouldReturn1(){
            // Arrange
            String j1 = "pedra";
            String j2 = "tisores";
            int expected = 1;

            // Act
            int actual = RockPaperScissors.guanya(j1, j2);

            // Assert
            assertEquals(expected, actual);
        }

        @Test
        @DisplayName("Paper VS Pedra")
        void paperVsPedra_shouldReturn1(){
            // Arrange
            String j1 = "paper";
            String j2 = "pedra";
            int expected = 1;

            // Act
            int actual = RockPaperScissors.guanya(j1, j2);

            // Assert
            assertEquals(expected, actual);
        }

        @Test
        @DisplayName("Tisores VS Paper")
        void tisoresVsPaper_shouldReturn1(){
            // Arrange
            String j1 = "tisores";
            String j2 = "paper";
            int expected = 1;

            // Act
            int actual = RockPaperScissors.guanya(j1, j2);

            // Assert
            assertEquals(expected, actual);
        }
    }

    @Nested
    class Jugador2 {
        @Test
        @DisplayName("Pedra VS Paper")
        void pedraVsPaper_shouldReturn2(){
            // Arrange
            String j1 = "pedra";
            String j2 = "paper";
            int expected = 2;

            // Act
            int actual = RockPaperScissors.guanya(j1, j2);

            // Assert
            assertEquals(expected, actual);
        }

        @Test
        @DisplayName("Paper VS Tisores")
        void paperVsTisores_shouldReturn2(){
            // Arrange
            String j1 = "paper";
            String j2 = "tisores";
            int expected = 2;

            // Act
            int actual = RockPaperScissors.guanya(j1, j2);

            // Assert
            assertEquals(expected, actual);
        }

        @Test
        @DisplayName("Tisores VS Pedra")
        void tisoresVsPedra_shouldReturn2(){
            // Arrange
            String j1 = "tisores";
            String j2 = "pedra";
            int expected = 2;

            // Act
            int actual = RockPaperScissors.guanya(j1, j2);

            // Assert
            assertEquals(expected, actual);
        }
    }
}