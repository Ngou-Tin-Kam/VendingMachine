package view;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PriceInputView {
    public void printPriceInput() {
        System.out.println("---Insert your money in pennies---");
    }

    public void printMoneyInput(BigDecimal amount) {
        System.out.printf("You have entered this much: £%s %n" , amount.setScale(2, RoundingMode.UNNECESSARY));
    }

    public void printChange(BigDecimal change) {
        System.out.printf("Item purchased and your change is: £%s %n", change.setScale(2, RoundingMode.UNNECESSARY));
    }

    public void printInsufficientFunds(BigDecimal insufficientFunds, BigDecimal insertedMoney) {
        System.out.printf("You are missing £%s amount %n", insufficientFunds.setScale(2, RoundingMode.UNNECESSARY));
        printInvalidChoiceToReturnMoney(insertedMoney);
    }

    public void printInvalidChoiceToReturnMoney(BigDecimal insertedMoney) {
        System.out.printf("You get £%s back %n", insertedMoney.setScale(2, RoundingMode.UNNECESSARY));
    }
}
