import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BankAccountAssumptionsTest {

    @Test
    @DisplayName("Test activation account after creation")
    public void testActive0(){
        BankAccount bankAccount = new BankAccount(500, 0);
        assumeTrue(bankAccount != null);
        assertTrue(bankAccount.isActive());
    }

    @Test
    @DisplayName("Test activation account after creation")
    public void testActive1(){
        BankAccount bankAccount = new BankAccount(500, 0);
        assumeFalse(bankAccount == null);
        assertTrue(bankAccount.isActive());
    }

    @Test
    @DisplayName("Test activation account after creation")
    public void testActive(){
        BankAccount bankAccount = new BankAccount(500, 0);
        //assumeFalse(bankAccount == null);
        assumingThat(bankAccount != null, () -> assertTrue(bankAccount.isActive()));
    }
}
