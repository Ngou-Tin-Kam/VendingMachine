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

    public void showMainMenuAndCurrentStock() {
        view.printMainMenu();
        showCurrentStock();
    }

    public void showCurrentStock() {
        ArrayList<Product> allProducts = model.getProducts();
// Original for loop to return each line of the product, keeping commenting code here for show case purpose
//        for (int i = 0; i < allProducts.size(); i++) {
//            view.printCurrentStock(
//                    allProducts.get(i).getId(),
//                    allProducts.get(i).getName(),
//                    allProducts.get(i).getPrice(),
//                    allProducts.get(i).getStock()
//            );
//        }

//Lambda example of the for loop above
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

    // A method that returns the Product type to
    public Product askUserForProductInput() {
        Product selectedProduct = null; // Sets selectedProduct to null
        view.printIdToPurchaseMessage();
        boolean validUserChoice = model.productToPurchase();
        if (validUserChoice) { // If choice is valid, then return the selectedProduct
            view.printYouHaveSelectedMessage(model.getUserSelectedProduct().getName());
            selectedProduct = model.getUserSelectedProduct(); // Calling the model to get selectedProduct from user
        }
        return selectedProduct;
    } // Not the best way to write this, but done purposely to show-case the use of try-catch
    // If choice is not valid then selectedProduct will return as null which will be handled in the main try-catch

    // A method to reduce the stock after product/item is purchased
    public void reduceSelectedStock(Product selectedProduct) {
        int currentStock = selectedProduct.getStock(); // Setting currentStock to selectedProduct's stock
        selectedProduct.setStock(currentStock - 1); // Reducing the stock by one after each purchase
        model.logItemPurchase(); // Calling the model to log audit
        updateTextFile(); // Calling the method to update items text file
    }

    // Method to update items text file
    private void updateTextFile() {
        model.updateTextFile(); // Calling the model to update text file
    }

    // Method to check if selectedProduct is in stock by returning boolean
    public boolean isInStock(Product selectedProduct) {
        int currentStock = selectedProduct.getStock();
        boolean inStock; // Setting the inStock variable to boolean
        // If else statement to check if current stock is more than 0 then return true or false
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


