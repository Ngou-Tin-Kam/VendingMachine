package view;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class VendingMachineView {
    public void printMainMenu() {
        System.out.println("---Main Menu---");
    }

    public void printCurrentStock(int id, String name, BigDecimal price, int stock) {
        System.out.printf("id: %d Name: %s Price: £%s Stock: %d %n",id, name, price.setScale(2, RoundingMode.UNNECESSARY), stock);
    }

    public void printIdToPurchaseMessage() {
        System.out.println("Enter the ID of item you would like to purchase or enter any other number to exit and get your money back");
    }

    public void printYouHaveSelectedMessage(String name) {
        System.out.printf("You have selected %s %n", name);
    }

    //Method to fail gracefully
    public void printExitChoiceMessage() {
        System.out.println("You have selected to exit the program");
    }

    public void printOutOfStockChoiceMessage() {
        System.out.println("You have selected an item that is currently out of stock");
    }
}
