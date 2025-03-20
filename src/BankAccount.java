import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {
    private double balance;
    private final Lock lock = new ReentrantLock();

    // Default constructor with optional initial balance
    public BankAccount() {
        this(0.0);
    }

    public BankAccount(double initialBalance) {
        if (initialBalance < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative.");
        }
        this.balance = initialBalance;
    }

    // Thread-safe deposit method with validation
    // Throwing exception replaced with returning 
    public String  deposit(double amount) {
        if (amount <= 0) {
            return ("Deposit amount must be positive.");
        }
        lock.lock();
        try {
            balance += amount;
            return ("Deposited: " + amount + " | Current Balance: " + balance);
        } finally {
            lock.unlock();
        }
    }

    // Thread-safe withdrawal method with validation
    public boolean withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive.");
        }
        lock.lock();
        try {
            if (balance >= amount) {
                balance -= amount;
                System.out.println("Withdrawn: " + amount + " | Current Balance: " + balance);
                return true;
            } else {
                System.out.println("Insufficient funds.");
                return false;
            }
        } finally {
            lock.unlock();
        }
    }

    // Thread-safe balance retrieval
    public double getBalance() {
        lock.lock();
        try {
            return balance;
        } finally {
            lock.unlock();
        }
    }
} 
//