package controller;

import model.Product;
import view.VendingMachineView;

import java.math.BigDecimal;
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

    public void showMainMenuAndCurrentStock() {
        view.printMainMenu();
        showCurrentStock();
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

        allProducts.forEach(product -> {
                    if (product.getStock() != 0) {
                        view.printCurrentStock(
                                product.getId(),
                                product.getName(),
                                BigDecimal.valueOf(product.getPrice()).divide(BigDecimal.valueOf(100)),
                                product.getStock()
                        );
                    }
                }
        );
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

    public void reduceSelectedStock(Product selectedProduct) {
        int currentStock = selectedProduct.getStock();
        selectedProduct.setStock(currentStock - 1);
        updateTextFile();
    }

    private void updateTextFile() {
        model.updateTextFile();
    }

    public boolean isInStock(Product selectedProduct) {
        int currentStock = selectedProduct.getStock();
        boolean inStock;
        if (currentStock > 0) {
            inStock = true;
        } else {
            inStock = false;
        }
        return inStock;
    }

    public void printExitChoice() {
        view.printExitChoiceMessage();
    }

    public void printOutOfStockChoice() {
        view.printOutOfStockChoiceMessage();
        updateTextFile();
    }
}


