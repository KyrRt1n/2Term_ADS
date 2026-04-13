package ua.voloschenko.theme13;

public class Account {
    private final long id;
    private long balance;

    public Account(long id, long initialBalance) {
        this.id = id;
        this.balance = initialBalance;
    }

    public long getId() {
        return id;
    }

    public long getBalance() {
        return balance;
    }

    public void withdraw(long amount) {
        this.balance -= amount;
    }

    public void deposit(long amount) {
        this.balance += amount;
    }
}