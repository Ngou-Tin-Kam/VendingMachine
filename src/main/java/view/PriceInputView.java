package view;

public class PriceInputView {
    public void printPriceInput() {
        System.out.println("---Insert your money---");
    }

    public void printMoneyInput(int amount) {
        System.out.printf("You have entered this much: %d %n" ,amount);
    }

    public void printChange(int change) {
        System.out.printf("Item purchased and your change is: %d %n", change);
    }

    public void printInsufficientFunds(int insufficientFunds, int insertedMoney) {
        System.out.printf("You are missing %d amount %n", insufficientFunds);
        printInvalidChoiceToReturnMoney(insertedMoney);
    }

    public void printInvalidChoiceToReturnMoney(int insertedMoney) {
        System.out.printf("You get %d back %n", insertedMoney);
    }
}
