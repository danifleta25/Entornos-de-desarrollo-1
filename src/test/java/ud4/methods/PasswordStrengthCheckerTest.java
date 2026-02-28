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

    @Test
    void weakMinusculas(){
        String password = "password";
        PasswordStrengthChecker.PasswordStrength fortalezaActual = PasswordStrengthChecker.isPasswordStrong(password);
        PasswordStrengthChecker.PasswordStrength fortalezaEsperada = PasswordStrengthChecker.PasswordStrength.WEAK;

        assertEquals(fortalezaEsperada, fortalezaActual);
    }

    @Test
    void weakMayusculas(){
        String password = "PASSWORD";
        PasswordStrengthChecker.PasswordStrength fortalezaActual = PasswordStrengthChecker.isPasswordStrong(password);
        PasswordStrengthChecker.PasswordStrength fortalezaEsperada = PasswordStrengthChecker.PasswordStrength.WEAK;

        assertEquals(fortalezaEsperada, fortalezaActual);
    }

    @Test
    void weakNumeros(){
        String password = "12345678";
        PasswordStrengthChecker.PasswordStrength fortalezaActual = PasswordStrengthChecker.isPasswordStrong(password);
        PasswordStrengthChecker.PasswordStrength fortalezaEsperada = PasswordStrengthChecker.PasswordStrength.WEAK;

        assertEquals(fortalezaEsperada, fortalezaActual);
    }

    @Test
    void testNotWeak(){
        String password = "password123";
        PasswordStrengthChecker.PasswordStrength fortalezaActual = PasswordStrengthChecker.isPasswordStrong(password);
        PasswordStrengthChecker.PasswordStrength fortalezaEsperada = PasswordStrengthChecker.PasswordStrength.MEDIUM;

        assertEquals(fortalezaEsperada, fortalezaActual);
    }



    @Test
    void passwordMayusMinus(){
        String password = "PasswordkweP";
        PasswordStrengthChecker.PasswordStrength fortalezaActual = PasswordStrengthChecker.isPasswordStrong(password);
        PasswordStrengthChecker.PasswordStrength fortalezaEsperada = PasswordStrengthChecker.PasswordStrength.MEDIUM;

        assertEquals(fortalezaEsperada, fortalezaActual);
    }

    @Test
    void passwordMayusNumber(){
        String password = "PASSWORD123456";
        PasswordStrengthChecker.PasswordStrength fortalezaActual = PasswordStrengthChecker.isPasswordStrong(password);
        PasswordStrengthChecker.PasswordStrength fortalezaEsperada = PasswordStrengthChecker.PasswordStrength.MEDIUM;

        assertEquals(fortalezaEsperada, fortalezaActual);
    }

    @Test
    void passwordMinusNumber(){
        String password = "password123456789";
        PasswordStrengthChecker.PasswordStrength fortalezaActual = PasswordStrengthChecker.isPasswordStrong(password);
        PasswordStrengthChecker.PasswordStrength fortalezaEsperada = PasswordStrengthChecker.PasswordStrength.MEDIUM;

        assertEquals(fortalezaEsperada, fortalezaActual);
    }



    @Test
    void strongPasswordTest() {
        String password = "12345aaAA";
        PasswordStrengthChecker.PasswordStrength fortalezaActual = PasswordStrengthChecker.isPasswordStrong(password);
        PasswordStrengthChecker.PasswordStrength fortalezaEsperada = PasswordStrengthChecker.PasswordStrength.STRONG;
        assertEquals(fortalezaEsperada, fortalezaActual);
    }


    @Test
    void notStrongPasswordTest() {
        String password = "12345aaaa";
        PasswordStrengthChecker.PasswordStrength fortalezaActual = PasswordStrengthChecker.isPasswordStrong(password);
        PasswordStrengthChecker.PasswordStrength fortalezaEsperada = PasswordStrengthChecker.PasswordStrength.MEDIUM;
        assertEquals(fortalezaEsperada, fortalezaActual);
    }



}