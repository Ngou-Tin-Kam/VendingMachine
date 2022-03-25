import controller.AuditController;
import controller.MoneyController;
import controller.VendingMachineController;
import model.Audit;
import model.Money;
import model.Product;
import view.MoneyView;
import view.VendingMachineView;

import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {
        // Declaring the model view controller (MVC) in main
        Audit auditModel = new Audit();
        AuditController auditController = new AuditController(auditModel);

        Product productModel = getProductFromFile(auditModel);
        VendingMachineView vmView = new VendingMachineView();
        VendingMachineController vmController = new VendingMachineController(productModel, vmView);

        Money moneyModel = new Money(auditModel);
        MoneyView moneyView = new MoneyView();
        MoneyController moneyController = new MoneyController(moneyModel, moneyView);

        vmController.showMainMenuAndCurrentStock(); // Calling the controller which calls the model to get the information and calls the view to display the main menu and current stock
        moneyController.askUserForMoneyInput();  // Calling the controller to shows the view for user input and then calls the model to set the data for userMoneyInput

        try {
            Product selectedProduct = vmController.askUserForProductInput();
            boolean isEnoughMoney = moneyController.calculatePriceDifference(
                    BigDecimal.valueOf(selectedProduct.getPrice()).divide(BigDecimal.valueOf(100)));
            boolean isInStock = vmController.isInStock(selectedProduct);

            // if else (and nested if else) to check if the vending machine have item/product in stock and checks if user provided enough money
            if (isInStock) {
                if (isEnoughMoney) {
                    moneyController.subtractMoneyFromProductPrice();
                    vmController.reduceSelectedStock(selectedProduct);
                } else {
                    moneyController.returnMoneyAndShowInsufficientFunds();
                }
            } else {
                moneyController.printInvalidChoiceToReturnMoney();
                vmController.printOutOfStockChoice();
            }
            vmController.showCurrentStock();
        } catch (NullPointerException e) { // Catching NullPointerException where a user selects an invalid choice (this could have been done differently but done it this way for showcasing try-catch)
            vmController.printExitChoice();
            moneyController.printInvalidChoiceToReturnMoney();
        }
        auditModel.logAction("File saved");
        auditController.uploadAuditList();
    }

    private static Product getProductFromFile(Audit auditModel) {
        Product product = new Product(auditModel);
        product.readProductFromFile();
        return product;
    }
}
