import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BankAccountTest {
    private BankAccount account;

    @BeforeEach
    void setUp() {
        account = new BankAccount(1000); // Initialise with a balance of 1000
    }

    @Test
    void testDepositValidAmount() {
        String result = account.deposit(500);
        assertEquals("Deposited: 500.0 | Current Balance: 1500.0", result);
    }

    @Test
    void testDepositNegativeAmount() {
        String result = account.deposit(-100);
        assertEquals("Deposit amount must be positive.", result);
    }

    @Test
    void testDepositZeroAmount() {
        String result = account.deposit(0);
        assertEquals("Deposit amount must be positive.", result);
    }

    @Test
    void testWithdrawValidAmount() {
        String result = account.withdraw(200);
        assertEquals("Withdrawn: 200.0 | Current Balance: 800.0", result);
    }

    @Test
    void testWithdrawMoreThanBalance() {
        String result = account.withdraw(1200);
        assertEquals("Insufficient funds.", result);
    }

    @Test
    void testWithdrawNegativeAmount() {
        String result = account.withdraw(-50);
        assertEquals("Withdrawal amount must be positive.", result);
    }

    @Test
    void testWithdrawZeroAmount() {
        String result = account.withdraw(0);
        assertEquals("Withdrawal amount must be positive.", result);
    }

    @Test
    void testInitialBalance() {
        assertEquals(1000, account.getBalance());
    }
}