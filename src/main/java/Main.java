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
        Audit auditModel = new Audit();
        AuditController auditController = new AuditController(auditModel);

        Product productModel = getProductFromFile(auditModel);
        VendingMachineView vmView = new VendingMachineView();
        VendingMachineController vmController = new VendingMachineController(productModel, vmView);

        Money moneyModel = new Money(auditModel);
        MoneyView moneyView = new MoneyView();
        MoneyController moneyController = new MoneyController(moneyModel, moneyView);

        vmController.showMainMenuAndCurrentStock();
        moneyController.askUserForMoneyInput();

        try {
            Product selectedProduct = vmController.askUserForProductInput();
            boolean isEnoughMoney = moneyController.calculatePriceDifference(
                    BigDecimal.valueOf(selectedProduct.getPrice()).divide(BigDecimal.valueOf(100)));
            boolean isInStock = vmController.isInStock(selectedProduct);

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
        } catch (NullPointerException e) {
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
