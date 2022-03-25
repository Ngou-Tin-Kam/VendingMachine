import model.Audit;
import model.Money;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class MoneyTest {
    Money money;
    Audit audit = new Audit();

    // Before each test, create a new instance of money
    @BeforeEach
    void setUp() {
        money = new Money(audit);
    }

    // One Unit Test to verify when calling userInputMoney with 1000 pennies is setting the insertedMoney to 10 pounds
    @Test
    void testUserInputMoneyAddedToInsertedMoney() {
        money.userInputMoney(1000);

        Assertions.assertEquals(money.getInsertedMoney(), BigDecimal.valueOf(10));
    }
}
