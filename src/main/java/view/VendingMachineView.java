package view;

public class VendingMachineView {
    public void printMainMenu() {
        System.out.println("---Main Menu---");
    }

    public void printCurrentStock(int id, String name, int price, int stock) {
        System.out.printf("id: %d Name: %s Price: %d Stock: %d %n",id, name, price, stock);
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
}
