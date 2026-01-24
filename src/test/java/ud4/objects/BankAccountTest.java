package ud4.objects;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


class BankAccountTest {


    @Nested
    @DisplayName("ConstructorsTest")
    class ConstructorsTest {
        @Test
        @DisplayName("Creación cuenta del banco sin balance comprobando el Number")
        void BankAccountConstructorTest () {
            BankAccount cuenta = new BankAccount(123456789, "Dani");
            int actual = cuenta.getNumber();
            int expected = 123456789;
            assertEquals(expected, actual);
        }

        @Test
        @DisplayName("Creación cuenta del banco sin balance comprobando el Holder")
        void BankAccountConstructorGetHolderReturnHolder () {
            BankAccount cuenta = new BankAccount(123456789, "Dani");
            String actual = cuenta.getHolder();
            String expected = "Dani";
            assertEquals(expected, actual);
        }

        @Test
        @DisplayName("Creación cuenta del banco con balance")
        void BankAccountConstructorTestWithBalance () {
            BankAccount cuenta = new BankAccount(123456789, "Dani", 1200.50);
            double actual = cuenta.getBalance();
            double expected = 1200.50;
            assertEquals(expected, actual);
        }


    }

    @Nested
    @DisplayName("MethodsTest")
    class MethodsTest {
        /*
        @Test
        @DisplayName("Test del metodo deposit() positivo")
        void depositTestPositive() {
            BankAccount cuenta = new BankAccount(123456789, "Dani", 1000);
            boolean actual = cuenta.deposit(10);
            boolean expected = true;
            assertEquals(expected, actual);
        }

        @Test
        @DisplayName("Test del metodo deposit() negativo")
        void depositTestNegative() {
            BankAccount cuenta = new BankAccount(123456789, "Dani", 1000);
            boolean actual = cuenta.deposit(-10);
            boolean expected = false;
            assertEquals(expected, actual);
        }

        @Test
        @DisplayName("Test del metodo deposit() cero")
        void depositTestZero() {
            BankAccount cuenta = new BankAccount(123456789, "Dani", 1000);
            boolean actual = cuenta.deposit(0);
            boolean expected = false;
            assertEquals(expected, actual);
        }
        */

        @ParameterizedTest(name = "Depositando {3} debe retornar {4}.")
        @DisplayName("Test parametrizado del método deposit()")
        @CsvSource({
                "123456789, Dani, 1000, 10, true",
                "123456789, Dani, 1000, -10, false",
                "123456789, Dani, 1000, 0, false"
        })
        void depositTest(int number, String holder, double balance, double amount, boolean expected) {
            BankAccount cuenta = new BankAccount(number, holder, balance);
            boolean actual = cuenta.deposit(amount);
            assertEquals(expected, actual);
        }
        /*
        @Test
        @DisplayName("Test del metodo withdraw() con valor positivo")
        void withdrawTestPositive(){
            BankAccount cuenta = new BankAccount(123456789, "Dani", 1000);
            boolean actual = cuenta.withdraw(10);
            assertEquals(true, actual);
        }

        @Test
        @DisplayName("Test del metodo withdraw() con valor negativo")
        void withdrawTestNegative(){
            BankAccount cuenta = new BankAccount(123456789, "Dani", 1000);
            boolean actual = cuenta.withdraw(-10);
            assertEquals(false, actual);
        }
        @Test
        @DisplayName("Test del metodo withdraw() con valor 0")
        void withdrawTestZero(){
            BankAccount cuenta = new BankAccount(123456789, "Dani", 1000);
            boolean actual = cuenta.withdraw(0);
            assertEquals(false, actual);
        }

        @Test
        @DisplayName("Test del metodo withdraw() con valor superior a balance")
        void withdrawTestRejected(){
            BankAccount cuenta = new BankAccount(123456789, "Dani", 1000);
            boolean actual = cuenta.withdraw(1500);
            assertEquals(false, actual);
        }
        */

        @ParameterizedTest(name = "Con el amount {3} la retirada es {4} para una cuenta con balance: {2}.")
        @DisplayName("Test parametrizado del metodo withdraw() con valores positivos, negativos, cero o superiores a balance")
        @CsvSource({
                "123456789, Dani, 1000, 10, true",
                "123456789, Dani, 1000, -10, false",
                "123456789, Dani, 1000, 0, false",
                "123456789, Dani, 1000, 1500, false"
        })
        void withdrawTest(int number, String holder, double balance, double amount, boolean expected) {
            BankAccount cuenta = new BankAccount(number, holder, balance);
            boolean actual = cuenta.withdraw(amount);
            assertEquals(expected, actual);
        }
        /*
        @Test
        @DisplayName("Test del metodo transfer()")
        void transferTestShouldBeTrue() {
            BankAccount cuenta = new BankAccount(123456789, "Dani", 1000);
            BankAccount cuenta2 = new BankAccount(987654321, "Jose", 500);
            boolean actual = cuenta2.transfer(cuenta, 10);
            assertEquals(true, actual);
        }

        @Test
        @DisplayName("Test del metodo transfer() con numero negativo")
        void transferTestWithNegativeAmountShouldBeFalse() {
            BankAccount cuenta = new BankAccount(123456789, "Dani", 1000);
            BankAccount cuenta2 = new BankAccount(987654321, "Jose", 500);
            boolean actual = cuenta2.transfer(cuenta, -10);
            assertEquals(false, actual);
        }

        @Test
        @DisplayName("Test del metodo transfer() con cero")
        void transferTestWithZeroAmountShouldBeFalse() {
            BankAccount cuenta = new BankAccount(123456789, "Dani", 1000);
            BankAccount cuenta2 = new BankAccount(987654321, "Jose", 500);
            boolean actual = cuenta2.transfer(cuenta, 0);
            assertEquals(false, actual);
        }
        */
        @ParameterizedTest(name = "La transferencia entre cuentas de un amount de {6} debe de ser {7}.")
        @DisplayName("Test del metodo transfer() con numero positivo, negativo y zero")
        @CsvSource({
                "123456789, Dani, 1000, 987654321, Jose, 500, 10, true",
                "123456789, Dani, 1000, 987654321, Jose, 500, -10, false",
                "123456789, Dani, 1000, 987654321, Jose, 500, 0, false"
        })
        void transferTest(int number1, String holder1, double balance1, int number2, String holder2,
                          double balance2, double amount, boolean expected) {
            BankAccount cuenta = new BankAccount(number1, holder1, balance1);
            BankAccount cuenta2 = new BankAccount(number2, holder2, balance2);
            boolean actual = cuenta.transfer(cuenta2, amount);
            assertEquals(expected, actual);
        }
    }

    @Nested
    @DisplayName("ControlDeTransferenciasTest")
    class BalancesTest {
        @Test
        @DisplayName("Comprobacion de balances entre cuentas despues de un transfer()")
        void transferBalancesTest(){
            BankAccount cuenta = new BankAccount(123456789, "Dani", 1000);
            BankAccount cuenta2 = new BankAccount(123456789, "Dani", 500);
            cuenta.transfer(cuenta2, 500);
            double actual = cuenta.getBalance();
            double actual2 = cuenta2.getBalance();
            assertAll(
                    () -> assertEquals(500, actual),
                    () -> assertEquals(1000, actual2)
            );
        }

        @Test
        @DisplayName("Comprobacion de balances entre cuentas despues de un transfer()")
        void depositBalancesTest(){
            BankAccount cuenta = new BankAccount(123456789, "Dani", 1000);
            cuenta.deposit(500);
            double actual = cuenta.getBalance();
            assertEquals(1500, actual);
        }

        @Test
        @DisplayName("Comprobacion de balances entre cuentas despues de un transfer()")
        void withdrawBalancesTest(){
            BankAccount cuenta = new BankAccount(123456789, "Dani", 1000);
            cuenta.withdraw(500);
            double actual = cuenta.getBalance();
            assertEquals(500, actual);
        }


    }

}