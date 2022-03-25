import model.Audit;
import model.Money;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class MoneyTest {
    Money money;
    Audit audit = new Audit();

    @BeforeEach
    void setUp() {
        money = new Money(audit);
    }
    
    @Test
    void testUserInputMoneyAddedToInsertedMoney() {
        money.userInputMoney(1000);

        Assertions.assertEquals(money.getInsertedMoney(), BigDecimal.valueOf(10));
    }
}
