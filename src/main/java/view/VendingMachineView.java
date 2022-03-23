package view;

public class VendingMachineView {
    public void printMainMenu() {
        System.out.println("---Main Menu---");
    }

    public void printCurrentStock(int id, String name, int price, int stock) {
        System.out.printf("id: %d Name: %s Price: %d Stock: %d %n",id, name, price, stock);
    }

    public void printIdToPurchaseMessage(){
        System.out.println("Enter the id of item you would like to purchase");
    }

    public void printYouHaveSelectedMessage(String name){
        System.out.printf("You have selected %s %n", name);
    }

    //Method to fail gracefully
    public void printInvalidChoiceMessage(){
        System.out.println("You have selected an invalid choice and the program will now exit");
    }
}
