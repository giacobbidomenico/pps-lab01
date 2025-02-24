import example.model.AccountHolder;
import example.model.BankAccount;
import example.model.SimpleBankAccount;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The test suite for testing the SimpleBankAccount implementation
 */
class SimpleBankAccountTest {

    private static final String ACCOUNT_TEST_NAME = "Mario";
    private static final String ACCOUNT_TEST_LASTNAME = "Rossi";
    private static final int    ACCOUNT_TEST_USERID = 2;
    private static final double TEST_FIRST_AMOUNT  = 100;
    private static final double TEST_SECOND_AMOUNT = 50;
    private static final double TEST_THIRD_AMOUNT = 70;
    private static final double TEST_FOURTH_AMOUNT = 30;
    private static final double TEST_AMOUNT_WITH_FEE = 29;


    private AccountHolder accountHolder;
    private BankAccount bankAccount;

    @BeforeEach
    void beforeEach(){
        accountHolder = new AccountHolder(ACCOUNT_TEST_NAME, ACCOUNT_TEST_LASTNAME, 1);
        bankAccount = new SimpleBankAccount(accountHolder, 0);
    }

    @Test
    void testInitialBalance() {
        assertEquals(0, bankAccount.getBalance());
    }

    @Test
    void testDeposit() {
        bankAccount.deposit(accountHolder.getId(), TEST_FIRST_AMOUNT);
        assertEquals(TEST_FIRST_AMOUNT, bankAccount.getBalance());
    }

    @Test
    void testWrongDeposit() {
        bankAccount.deposit(accountHolder.getId(), TEST_FIRST_AMOUNT);
        bankAccount.deposit(ACCOUNT_TEST_USERID, TEST_SECOND_AMOUNT);
        assertEquals(TEST_FIRST_AMOUNT, bankAccount.getBalance());
    }

    @Test
    void testWithdraw() {
        bankAccount.deposit(accountHolder.getId(), TEST_FIRST_AMOUNT);
        bankAccount.withdraw(accountHolder.getId(), TEST_THIRD_AMOUNT);
        assertEquals(TEST_FOURTH_AMOUNT, bankAccount.getBalance());
    }

    @Test
    void testWrongWithdraw() {
        bankAccount.deposit(accountHolder.getId(), 100);
        bankAccount.withdraw(2, TEST_THIRD_AMOUNT);
        assertEquals(TEST_FIRST_AMOUNT, bankAccount.getBalance());
    }

    @Test
    void testWithdrawWithFee() {
        bankAccount.deposit(accountHolder.getId(), TEST_FIRST_AMOUNT);
        bankAccount.withdrawWithFee(accountHolder.getId(), TEST_THIRD_AMOUNT);
        assertEquals(TEST_AMOUNT_WITH_FEE, bankAccount.getBalance());
    }

    @Test
    void testWrongWithdrawWithFee() {
        bankAccount.deposit(accountHolder.getId(), TEST_FIRST_AMOUNT);
        bankAccount.withdrawWithFee(ACCOUNT_TEST_USERID, TEST_THIRD_AMOUNT);
    }

    @Test
    void TestWithdrawAllowed() {
        bankAccount.deposit(accountHolder.getId(), TEST_FIRST_AMOUNT);
        assertTrue(bankAccount.withdraw(accountHolder.getId(), TEST_SECOND_AMOUNT));
    }

    @Test
    void TestWithdrawNotAllowed() {
        bankAccount.deposit(accountHolder.getId(), TEST_SECOND_AMOUNT);
        assertFalse(bankAccount.withdraw(accountHolder.getId(), TEST_FIRST_AMOUNT));
    }


    @Test
    void TestWithdrawWithFeeAllowed() {
        bankAccount.deposit(accountHolder.getId(), TEST_FIRST_AMOUNT);
        assertTrue(bankAccount.withdrawWithFee(accountHolder.getId(), TEST_SECOND_AMOUNT));
    }

    @Test
    void TestWithdrawWithFeeNotAllowed() {
        bankAccount.deposit(accountHolder.getId(), TEST_SECOND_AMOUNT);
        assertFalse(bankAccount.withdrawWithFee(accountHolder.getId(), TEST_FIRST_AMOUNT));
    }
}
