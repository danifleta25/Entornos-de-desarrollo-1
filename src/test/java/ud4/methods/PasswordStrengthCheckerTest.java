package ud4.methods;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordStrengthCheckerTest {

    @Test
    @DisplayName("Prueba contraseña vacia")
    void nullPassword(){
        String password = "";
        PasswordStrengthChecker.PasswordStrength fortalezaActual = PasswordStrengthChecker.isPasswordStrong(password);

        PasswordStrengthChecker.PasswordStrength fortalezaEsperada = PasswordStrengthChecker.PasswordStrength.INVALID;

        Assertions.assertEquals(fortalezaEsperada, fortalezaActual);
    }

    @Test
    @DisplayName("Prueba contraseña 6 digitos")
    void sixDigitPassword(){
        String password = "asdfwe";
        PasswordStrengthChecker.PasswordStrength fortalezaActual = PasswordStrengthChecker.isPasswordStrong(password);

        PasswordStrengthChecker.PasswordStrength fortalezaEsperada = PasswordStrengthChecker.PasswordStrength.INVALID;

        Assertions.assertEquals(fortalezaEsperada, fortalezaActual);
    }

    @Test
    @DisplayName("Prueba contraseña válida")
    void validPassword(){
        String password = "12345678";
        PasswordStrengthChecker.PasswordStrength fortalezaActual = PasswordStrengthChecker.isPasswordStrong(password);

        PasswordStrengthChecker.PasswordStrength fortalezaEsperada = PasswordStrengthChecker.PasswordStrength.WEAK;

        Assertions.assertEquals(fortalezaEsperada, fortalezaActual);
    }

}