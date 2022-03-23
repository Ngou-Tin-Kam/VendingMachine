package controller;

import model.Product;
import view.VendingMachineView;

import java.util.ArrayList;

public class VendingMachineController {
    private Product model;
    private VendingMachineView view;

    public VendingMachineController(Product model, VendingMachineView view) {
        this.model = model;
        this.view = view;
    }

    public Product getModel() {
        return model;
    }

    public void setModel(Product model) {
        this.model = model;
    }

    public VendingMachineView getView() {
        return view;
    }

    public void setView(VendingMachineView view) {
        this.view = view;
    }

    public void showMainMenu() {
        view.printMainMenu();
    }

    public void showCurrentStock() {
        ArrayList<Product> allProducts = getModel().getProducts();
//        for (int i = 0; i < allProducts.size(); i++) {
//            view.printCurrentStock(
//                    allProducts.get(i).getId(),
//                    allProducts.get(i).getName(),
//                    allProducts.get(i).getPrice(),
//                    allProducts.get(i).getStock()
//            );
//        }

        allProducts.forEach( product -> view.printCurrentStock(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getStock()
        ));
    }

    public Product askUserForProductInput() {
        Product selectedProduct = null;
        view.printIdToPurchaseMessage();
        boolean validUserChoice = model.productToPurchase();
        if (validUserChoice) {
            view.printYouHaveSelectedMessage(model.getUserSelectedProduct().getName());
            selectedProduct = model.getUserSelectedProduct();
        }
        return selectedProduct;
    }

    public void removeItemFromStock(Product selectedProduct) {
        int newStock = selectedProduct.getStock() - 1;
        selectedProduct.setStock(newStock);
        model.updateTextFile();
    }

    public void printExitChoice() {
        view.printExitChoiceMessage();
    }

}


