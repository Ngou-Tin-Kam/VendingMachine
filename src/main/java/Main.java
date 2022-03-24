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

        vmController.showMainMenu();
        vmController.showCurrentStock();

        moneyController.showPriceInput();
        moneyController.askUserForMoneyInput();
        moneyController.displayUserMoneyInput();

        Product selectedProduct = vmController.askUserForProductInput();
        try{
            boolean isEnoughMoney = moneyController.calculatePriceDifference(BigDecimal.valueOf(selectedProduct.getPrice()).divide(BigDecimal.valueOf(100)));
            if (isEnoughMoney) {
                vmController.removeItemFromStock(selectedProduct);
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
