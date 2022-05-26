import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BankAccountBeforeAndAfterTests {
    static BankAccount bankAccount;

    @BeforeAll
    public void prepTest(){
        System.out.println("Hi!");
        bankAccount = new BankAccount(500, 0);
    }

    @Test
    public void testWithdraw(){
        bankAccount.withDraw(300);
        assertEquals(200, bankAccount.getBalance());
    }

    @Test
    public void testDeposit(){
        bankAccount.deposit(500);
        assertEquals(700, bankAccount.getBalance());
    }

    @AfterAll
    public void endTest(){
        System.out.println("Bye!");
    }
}
