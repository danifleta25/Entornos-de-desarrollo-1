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
class BankAccountServiceImplTest {

    @Mock
    BankAccountRepository repository;

    @Mock
    NotificationService notificationService;

    @InjectMocks
    BankAccountServiceImpl bankAccountService;

    private final BankAccount cuenta = new BankAccount("ES123456789", 1000);


    @Nested
    class FindByIBANTest {

        @Test
        void givenExistingIBAN_shouldReturnBankAccount() throws ResourceNotFoundException {
            when(repository.findByIBAN(cuenta.getIban())).thenReturn(cuenta);

            BankAccount cuenta1 = bankAccountService.findByIBAN(cuenta.getIban());

            assertEquals(cuenta, cuenta1);

            verify(repository).findByIBAN(cuenta.getIban());

        }

        @Test
        void givenNonExistingIBAN_shouldThrowException() {
            String nonExistingIban = "ES987654321654";

            when(repository.findByIBAN(nonExistingIban)).thenReturn(null);

            assertThrows(ResourceNotFoundException.class,
                    () -> bankAccountService.findByIBAN(nonExistingIban));

            verify(repository).findByIBAN(nonExistingIban);
        }
    }

    @Nested
    class CreateBankAccountTest {
        @Test
        void createShouldCreateBankAccount() {
            BankAccount cuentaResultante = bankAccountService.create();

            assertAll(
                    () -> assertNotNull(cuentaResultante),
                    () -> assertNotNull(cuentaResultante.getIban())
            );
            verify(repository).save(cuentaResultante);
        }

        @Test
        void createShouldSendNotification() {
            BankAccount cuentaResultante = bankAccountService.create();

            verify(notificationService).sendNotification(
                    cuentaResultante, "Your new bank account has been created!");
        }

    }

    @Nested
    class UpdateTest {
        @Test
        void givenExistingAccount_shouldUpdate() {
            when(repository.existsByIBAN(cuenta.getIban())).thenReturn(true);

            boolean result = bankAccountService.update(cuenta);

            assertTrue(result);

            verify(repository).save(cuenta);
        }

        @Test
        void givenNonExistingAccount_shouldNotUpdate() {
            when(repository.existsByIBAN(cuenta.getIban())).thenReturn(false);

            boolean result = bankAccountService.update(cuenta);

            assertFalse(result);

            verify(repository, never()).save(cuenta);
        }
    }

    @Nested
    class DeleteTest {

        @Test
        void givenExistingAccount_shouldDelete() {
            when(repository.existsByIBAN(cuenta.getIban())).thenReturn(true);

            boolean result = bankAccountService.delete(cuenta.getIban());

            assertTrue(result);

            verify(repository).delete(cuenta.getIban());
        }

        @Test
        void givenNonExistingAccount_shouldNotDelete() {
            when(repository.existsByIBAN(cuenta.getIban())).thenReturn(false);

            boolean result = bankAccountService.delete(cuenta.getIban());

            assertFalse(result);

            verify(repository, never()).delete(cuenta.getIban());
        }
    }

    @Nested
    class GenerateIBANTest {

        @Test
        void whenTheresNoAccounts_IbanShouldEndWithOne() {
            //Si repository.latest() retorna null es que no ha encontrado cuentas
            when(repository.latest()).thenReturn(null);

            String ibanGenerado = bankAccountService.generateIBAN();
            // Por lo que el ibanGenerado terminará si o si en "1"
            assertTrue(ibanGenerado.endsWith("1"));

            verify(repository).latest();
        }

        @Test
        void whenTheresAccounts_IbanShouldEndWithPlusOne() {
            BankAccount lastAccount = new BankAccount("ES123456778", 1000);
            // Si cuando el repositorio llama a .latest() retorna una cuenta
            // significa que hay cuentas existentes
            when(repository.latest()).thenReturn(lastAccount);

            String ibanGenerado = bankAccountService.generateIBAN();
            // Por lo que si esa cuenta termina en "...78" debe pasar a terminar en 79, ya que se le suma 1
            assertTrue(ibanGenerado.endsWith("79"));

            verify(repository).latest();
        }
    }

    @Nested
    class DepositTest {

        @Test
        void givenNegativeAmount_shouldReturnFlase() {
            BankAccount newAccount = new BankAccount("ES123456778", 1000);
            // Un .deposit() con un ammount negativo debe devolver False
            boolean result = bankAccountService.deposit(newAccount, -100);
            assertFalse(result);
            // Verifica que nunca se llama a repository.save
            verify(repository, never()).save(newAccount);
        }

        @Test
        void givenZeroAmount_shouldReturnFlase() {
            BankAccount newAccount = new BankAccount("ES123456778", 1000);
            // Un .deposit() con un ammount 0 debe devolver False
            boolean result = bankAccountService.deposit(newAccount, 0);
            assertFalse(result);
            // Verifica que nunca se llama a repository.save
            verify(repository, never()).save(newAccount);
        }


        @Test
        void givenPositiveAmount_shouldReturnTrue() {
            BankAccount newAccount = new BankAccount("ES123456778", 1000);
            // Un .deposit() de una cantidad positiva debe devolver tRUE
            boolean result = bankAccountService.deposit(newAccount, 100);

            assertTrue(result);
            // Se llama a repository.save
            verify(repository).save(newAccount);
        }

        @Test
        void givenPositiveAmount_shouldUpdateBalance() {
            BankAccount newAccount = new BankAccount("ES123456778", 1000);
            // Un .deposit() de una cantidad positiva debe actualizar el balance de la cuenta
            boolean result = bankAccountService.deposit(newAccount, 100);

            double balance = newAccount.getBalance();
            // Debe retornar True
            assertTrue(result);
            assertEquals(1100, balance);

            verify(repository).save(newAccount);
        }
    }

    @Nested
    class WithdrawTest {

        @Test
        void givenNegativeAmount_shouldReturnFlase() {
            BankAccount newAccount = new BankAccount("ES123456778", 1000);
            // Un .withdraw() con un ammount negativo debe devolver False
            boolean result = bankAccountService.withdraw(newAccount, -100);
            assertFalse(result);
            // Verifica que nunca se llama a repository.save
            verify(repository, never()).save(newAccount);
        }

        @Test
        void givenZeroAmount_shouldReturnFlase() {
            BankAccount newAccount = new BankAccount("ES123456778", 1000);
            // Un .withdraw() con un ammount 0 debe devolver False
            boolean result = bankAccountService.withdraw(newAccount, 0);
            assertFalse(result);
            // Verifica que nunca se llama a repository.save
            verify(repository, never()).save(newAccount);
        }

        @Test
        void givenAmmountIsBiggerThanBalance_shouldReturnFalse() {
            BankAccount newAccount = new BankAccount("ES123456778", 1000);

            boolean result = bankAccountService.withdraw(newAccount, 1100);
            // Si el amount es mayor que el balance debe devolver False y no llamar a .save()
            assertFalse(result);
            verify(repository, never()).save(newAccount);
        }

        @Test
        void givenAmmountIsEqualThanBalance_shouldWithdraw() {
            BankAccount newAccount = new BankAccount("ES123456778", 1000);

            boolean result = bankAccountService.withdraw(newAccount, 1000);
            // Si el ammount es igual que el balance se efectua la retirada
            assertTrue(result);
            assertEquals(0, newAccount.getBalance());
            verify(repository).save(newAccount);
        }

        @Test
        void givenValidAmount_shouldWithdraw() {
            BankAccount newAccount = new BankAccount("ES123456778", 1000);
            // Un .withdraw() de una cantidad positiva debe realizar la retirada
            boolean result = bankAccountService.withdraw(newAccount, 100);

            double balance = newAccount.getBalance();

            assertTrue(result);
            assertEquals(900, balance);

            verify(repository).save(newAccount);
        }


    }

    @Nested
    class TransferTest {
        @Test
        void givenNegativeAmount_shouldReturnFlase() {
            BankAccount fromAccount = new BankAccount("ES123456778", 1000);
            BankAccount toAccount = new BankAccount("ES987654321", 2000);
            double amount = -100;
            // Un .transfer() con un ammount negativo debe devolver False
            boolean result = bankAccountService.transfer(fromAccount, toAccount, amount);
            assertFalse(result);
            // Verifica que nunca se llama a repository.save
            verify(repository, never()).save(fromAccount);
            verify(repository, never()).save(toAccount);
            // Si retorna False nunca debe llamar a .sendNotification() ni enviar ningún String
            verify(notificationService, never()).sendNotification(any(), anyString());

        }

        @Test
        void givenZeroAmount_shouldReturnFlase() {
            BankAccount fromAccount = new BankAccount("ES123456778", 1000);
            BankAccount toAccount = new BankAccount("ES987654321", 2000);
            // Un .transfer() con un ammount 0 debe devolver False
            boolean result = bankAccountService.transfer(fromAccount, toAccount, 0);
            assertFalse(result);
            // Verifica que nunca se llama a repository.save
            verify(repository, never()).save(fromAccount);
            verify(repository, never()).save(toAccount);
            // Si retorna False nunca debe llamar a .sendNotification() ni enviar ningún String
            verify(notificationService, never()).sendNotification(any(), anyString());
        }

        @Test
        void givenAmmountIsBiggerThanBalance_shouldReturnFalse() {
            BankAccount fromAccount = new BankAccount("ES123456778", 1000);
            BankAccount toAccount = new BankAccount("ES987654321", 2000);

            boolean result = bankAccountService.transfer(fromAccount, toAccount, 1100);
            // Si el amount es mayor que el balance debe devolver False y no llamar a .save()
            assertFalse(result);
            verify(repository, never()).save(fromAccount);
            verify(repository, never()).save(toAccount);
            // Si retorna False nunca debe llamar a .sendNotification() ni enviar ningún String
            verify(notificationService, never()).sendNotification(any(), anyString());
        }

        @Test
        void givenAmmountIsEqualThanFromBalance_shouldTransfer() {
            BankAccount fromAccount = new BankAccount("ES123456778", 1000);
            BankAccount toAccount = new BankAccount("ES987654321", 2000);

            boolean result = bankAccountService.transfer(fromAccount, toAccount, 1000);
            // Si el ammount es igual que el balance se efectua la retirada
            assertTrue(result);
            assertEquals(0, fromAccount.getBalance());
            assertEquals(3000, toAccount.getBalance());
            verify(repository).save(fromAccount);
            verify(repository).save(toAccount);
        }

        @Test
        void givenValidAmmount_shouldTransfer() {
            BankAccount fromAccount = new BankAccount("ES123456778", 1000);
            BankAccount toAccount = new BankAccount("ES987654321", 2000);

            boolean result = bankAccountService.transfer(fromAccount, toAccount, 200);
            // Si el ammount es un número válido se efectua la retirada
            assertTrue(result);
            assertEquals(800, fromAccount.getBalance());
            assertEquals(2200, toAccount.getBalance());
            verify(repository).save(fromAccount);
            verify(repository).save(toAccount);
        }

        @Test
        void successTransfer_shouldSendNotification() {
            BankAccount fromAccount = new BankAccount("ES123456778", 1000);
            BankAccount toAccount = new BankAccount("ES987654321", 2000);
            double amount = 200;


            boolean result = bankAccountService.transfer(fromAccount, toAccount, amount);
            // Si la transacció se realiza con éxito se debe enviar notificación
            // tanto a la cuenta desde la que se realiza la transaccion como a la que la recibe
            assertTrue(result);

            verify(notificationService).sendNotification(
                    fromAccount,
                    "Transfer of " + amount + " to " + toAccount.getIban());
            verify(notificationService).sendNotification(
                    toAccount,
                    "Transfer of " + amount + " from " + fromAccount.getIban());
        }


    }

}