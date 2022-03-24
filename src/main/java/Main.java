import controller.MoneyController;
import controller.VendingMachineController;
import model.Money;
import model.Product;
import view.PriceInputView;
import view.VendingMachineView;

import java.math.BigDecimal;


public class Main {

    public static void main(String[] args) {
        Product productModel = getProductFromFile();
        VendingMachineView vmView = new VendingMachineView();
        VendingMachineController vmController = new VendingMachineController(productModel, vmView);

        Money moneyModel = new Money();
        PriceInputView priceInputView = new PriceInputView();
        MoneyController moneyController = new MoneyController(moneyModel, priceInputView);

        vmController.showMainMenuAndCurrentStock();
        moneyController.askUserForMoneyInput();

        Product selectedProduct = vmController.askUserForProductInput();
        try{
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
    }

    private static Product getProductFromFile(){
        Product product = new Product();
        product.readProductFromFile();
        return product;
    }
}
