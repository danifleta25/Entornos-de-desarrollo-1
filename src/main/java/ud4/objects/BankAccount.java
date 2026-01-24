package ud4.objects;

public class BankAccount {

    private final int NUMBER;
    private final String HOLDER;
    private double balance;

    // constructors

    public BankAccount(int number, String holder) {
        this.NUMBER = number;
        this.HOLDER = holder;
    }

    public BankAccount(int number, String holder, double balance) {
        this.NUMBER = number;
        this.HOLDER = holder;
        this.balance = balance;
    }




    // getters

    public int getNumber() {
        return NUMBER;
    }

    public String getHolder() {
        return HOLDER;
    }

    public double getBalance() {
        return balance;
    }


    // methods

    public boolean deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
            return true;
        }
        return false;
    }

    public boolean withdraw(double amount) {
        if (amount <= 0 || amount > balance) {
            return false;
        } else {
            this.balance -= amount;
            return true;
        }
    }

    public boolean transfer (BankAccount account, double amount) {
        if (amount <= 0 || amount > balance) {
            return false;
        } else {
            this.balance -= amount;
            account.deposit(amount);
            return true;
        }
    }



}
