package ud8.bank.domain.service;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ud8.bank.domain.entity.BankAccount;
import ud8.bank.persistance.repository.BankAccountRepository;
import ud8.common.exception.ResourceNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BankAccountServiceImplRepasoTest {

    @Mock
    BankAccountRepository repository;

    @Mock
    NotificationService notificationService;

    @InjectMocks
    BankAccountServiceImpl bankAccountService;


    BankAccount cuenta = new BankAccount("ES1234567891", 1000);


    @Nested
    class FindByIBANTest {


        @Test
        void givenExistingIBAN_shouldReturnAccount() throws ResourceNotFoundException {
            when(repository.findByIBAN(cuenta.getIban())).thenReturn(cuenta);

            BankAccount cuenta2 = bankAccountService.findByIBAN(cuenta.getIban());

            assertEquals(cuenta, cuenta2);

            verify(repository).findByIBAN(cuenta.getIban());
        }

        @Test
        void givenNonExistingIBAN_shouldThrowException() throws ResourceNotFoundException {
            when(repository.findByIBAN("ES98789798797")).thenReturn(null);

            assertThrows(ResourceNotFoundException.class,
                    () -> bankAccountService.findByIBAN("ES98789798797"));

            verify(repository).findByIBAN("ES98789798797");
        }
    }

    @Nested
    class CreateTest {

        @Test
        void createAccount_shouldSaveNewAccount() {

            BankAccount cuentaNueva = bankAccountService.create();

            assertNotNull(cuentaNueva);

            verify(repository).save(cuentaNueva);
        }

        @Test
        void createAccount_shouldSendNotification() {

            BankAccount cuentaNueva = bankAccountService.create();

            verify(notificationService).sendNotification(cuentaNueva, "Your new bank account has been created!");
        }

    }


    @Nested
    class GenerateIBANTest {
        @Test
        void generateIBANMethod_shouldReturnIBAN() {
            // Cuando existe una cuenta
            when(repository.latest()).thenReturn(cuenta);

            String cuenta2IBAN = bankAccountService.generateIBAN();

            assertTrue(cuenta2IBAN.endsWith("2"));

            verify(repository).latest();

        }

        @Test
        void generateIBANMethod_shouldReturnIBANThatEndsWith1() {
            // Cuando NO existe una cuenta
            when(repository.latest()).thenReturn(null);

            String cuenta2IBAN = bankAccountService.generateIBAN();

            assertTrue(cuenta2IBAN.endsWith("1"));

            verify(repository).latest();

        }
    }


    @Nested
    class UpdateTest {
        @Test
        void givenExistingIBAN_shouldUpdateAccount() {
            when(repository.existsByIBAN(cuenta.getIban())).thenReturn(true);

            boolean resultadoUpdate = bankAccountService.update(cuenta);

            assertTrue(resultadoUpdate);

            verify(repository).save(cuenta);
            verify(repository).existsByIBAN(cuenta.getIban());
        }

        @Test
        void givenNonExistingIBAN_shouldNotDeleteAccount() {
            BankAccount cuentaFake = new BankAccount(null);
            when(repository.existsByIBAN(cuentaFake.getIban())).thenReturn(false);

            boolean resultadoUpdate = bankAccountService.update(cuentaFake);

            assertFalse(resultadoUpdate);
            verify(repository).existsByIBAN(cuentaFake.getIban());

            verify(repository, never()).save(cuentaFake);
        }

    }


    @Nested
    class DeleteTest {
        @Test
        void givenExistingIBAN_shouldDeleteAccount() {
            when(repository.existsByIBAN(cuenta.getIban())).thenReturn(true);

            boolean resultadoDelete = bankAccountService.delete(cuenta.getIban());

            assertTrue(resultadoDelete);

            verify(repository).delete(cuenta.getIban());
            verify(repository).existsByIBAN(cuenta.getIban());
        }

        @Test
        void givenNonExistingIBAN_shouldNotDeleteAccount() {
            String nonExistingIBAN = "ES99999999";
            when(repository.existsByIBAN(nonExistingIBAN)).thenReturn(false);

            boolean resultadoDelete = bankAccountService.delete(nonExistingIBAN);

            assertFalse(resultadoDelete);
            verify(repository).existsByIBAN(nonExistingIBAN);

            verify(repository, never()).delete(nonExistingIBAN);
        }


    }


    @Nested
    class DepositTest {
        @Test
        void givenPositiveAmmount_shouldReturnTrue() {

            boolean resultadoDeposit = bankAccountService.deposit(cuenta, 100);

            assertTrue(resultadoDeposit);

            verify(repository).save(cuenta);
        }

        @Test
        void givenNegativeAmmount_shouldReturnFalse() {

            boolean resultadoDeposit = bankAccountService.deposit(cuenta, -100);

            assertFalse(resultadoDeposit);

            verify(repository, never()).save(cuenta);
        }

        @Test
        void givenPositiveAmmount_shouldReturnUpdateBalance() {

            BankAccount cuentaNueva = new BankAccount("ES12345798", 1000);

            boolean resultadoDeposit = bankAccountService.deposit(cuentaNueva, 100);

            assertTrue(resultadoDeposit);

            assertEquals(1100, cuentaNueva.getBalance());


            verify(repository).save(cuentaNueva);
        }


    }


    @Nested
    class WithdrawTest {
        @Test
        void givenPositiveAmount_shouldReturnTrue() {
            BankAccount cuentaNueva = new BankAccount("ES12345798", 1000);

            boolean resultadoWithdraw = bankAccountService.withdraw(cuentaNueva, 100);

            assertTrue(resultadoWithdraw);

            verify(repository).save(cuentaNueva);

        }

        @Test
        void givenNegativeAmount_shouldReturnFalse() {
            BankAccount cuentaNueva = new BankAccount("ES12345798", 1000);

            boolean resultadoWithdraw = bankAccountService.withdraw(cuentaNueva, -100);

            assertFalse(resultadoWithdraw);

            verify(repository, never()).save(cuentaNueva);
        }

        @Test
        void givenZeroAmount_shouldReturnFalse() {
            BankAccount cuentaNueva = new BankAccount("ES12345798", 1000);

            boolean resultadoWithdraw = bankAccountService.withdraw(cuentaNueva, 0);

            assertFalse(resultadoWithdraw);

            verify(repository, never()).save(cuentaNueva);
        }

        @Test
        void givenPositiveAmount_shouldWithdrawAndUpdateBalance() {
            BankAccount cuentaNueva = new BankAccount("ES12345798", 1000);

            boolean resultadoWithdraw = bankAccountService.withdraw(cuentaNueva, 100);

            assertEquals(900, cuentaNueva.getBalance());

            verify(repository).save(cuentaNueva);
        }

    }


    @Nested
    class TransferTest {
        @Test
        void givenPositiveAmount_shouldReturnTrue() {
            BankAccount cuentaFrom = new BankAccount("ES123456", 1000);
            BankAccount cuentaTo = new BankAccount("ES1234599996", 2000);

            boolean resultTransfer = bankAccountService.transfer(cuentaFrom, cuentaTo, 100);

            assertTrue(resultTransfer);

            verify(repository).save(cuentaFrom);
            verify(repository).save(cuentaTo);
        }

        @Test
        void givenPositiveAmount_shouldUpdateBalance() {
            BankAccount cuentaFrom = new BankAccount("ES123456", 1000);
            BankAccount cuentaTo = new BankAccount("ES1234599996", 2000);

            boolean resultTransfer = bankAccountService.transfer(cuentaFrom, cuentaTo, 100);

            assertEquals(900, cuentaFrom.getBalance());
            assertEquals(2100, cuentaTo.getBalance());

            verify(repository).save(cuentaFrom);
            verify(repository).save(cuentaTo);
        }

        @Test
        void givenPositiveAmount_shouldSendNotification() {
            BankAccount cuentaFrom = new BankAccount("ES123456", 1000);
            BankAccount cuentaTo = new BankAccount("ES1234599996", 2000);

            double amount = 100;

            boolean resultTransfer = bankAccountService.transfer(cuentaFrom, cuentaTo, amount);

            assertTrue(resultTransfer);

            verify(notificationService).sendNotification(cuentaFrom, "Transfer of " + amount + " to " + cuentaTo.getIban());
            verify(notificationService).sendNotification(cuentaTo, "Transfer of " + amount + " from " + cuentaFrom.getIban());

        }


        @Test
        void givenNegativeAmount_shouldReturnFalseAndDontSendNotification() {
            BankAccount cuentaFrom = new BankAccount("ES123456", 1000);
            BankAccount cuentaTo = new BankAccount("ES1234599996", 2000);

            double amount = -100;
            boolean resultTransfer = bankAccountService.transfer(cuentaFrom, cuentaTo, amount);

            assertFalse(resultTransfer);

            verify(repository, never()).save(cuentaFrom);
            verify(repository, never()).save(cuentaTo);

            verify(notificationService, never()).sendNotification(cuentaFrom, "Transfer of " + amount + " to " + cuentaTo.getIban());
            verify(notificationService, never()).sendNotification(cuentaTo, "Transfer of " + amount + " from " + cuentaFrom.getIban());

        }

        @Test
        void givenZeroAmount_shouldReturnFalse() {
            BankAccount cuentaFrom = new BankAccount("ES123456", 1000);
            BankAccount cuentaTo = new BankAccount("ES1234599996", 2000);

            boolean resultTransfer = bankAccountService.transfer(cuentaFrom, cuentaTo, 0);

            assertFalse(resultTransfer);

            verify(repository, never()).save(cuentaFrom);
            verify(repository, never()).save(cuentaTo);
        }

        @Test
        void givenBiggerAmountThanBalance_shouldReturnFalse() {
            BankAccount cuentaFrom = new BankAccount("ES123456", 1000);
            BankAccount cuentaTo = new BankAccount("ES1234599996", 2000);

            boolean resultTransfer = bankAccountService.transfer(cuentaFrom, cuentaTo, 10000);

            assertFalse(resultTransfer);

            verify(repository, never()).save(cuentaFrom);
            verify(repository, never()).save(cuentaTo);
        }


    }



}