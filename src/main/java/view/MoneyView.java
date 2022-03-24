package view;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;

public class MoneyView {
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

    public void printChangeListMessage(ArrayList<BigInteger> coinList) {
        System.out.println("You get " + coinList.get(0) + " £2 coin");
        System.out.println("You get " + coinList.get(1) + " £1 coin");
        System.out.println("You get " + coinList.get(2) + " 50p coin");
        System.out.println("You get " + coinList.get(3) + " 20p coin");
        System.out.println("You get " + coinList.get(4) + " 10p coin");
        System.out.println("You get " + coinList.get(5) + " 1p coin");
    }
}
